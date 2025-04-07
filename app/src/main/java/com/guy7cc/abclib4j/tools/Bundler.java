package com.guy7cc.abclib4j.tools;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.expr.FieldAccessExpr;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.nodeTypes.NodeWithName;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Bundler {
    public static void main(String[] args) throws IOException {
        Path sourceRoot = Paths.get("src/main/java");
        Path mainJavaPath = sourceRoot.resolve("com/guy7cc/abclib4j/Main.java");
        Path output = Paths.get("build/generated/Main.java");
        Files.createDirectories(output.getParent());

        Queue<Path> localLibPaths = new ArrayDeque<>();
        localLibPaths.add(mainJavaPath);
        Set<Path> resultLocalLibPaths = new HashSet<>(Set.of(mainJavaPath));

        while(!localLibPaths.isEmpty()){
            Path path = localLibPaths.poll();
            CompilationUnit cu = StaticJavaParser.parse(path);
            Set<Path> newLocalLibs = collectLocalLibraries(sourceRoot, cu);
            for(Path newLocalLib : newLocalLibs){
                if(!resultLocalLibPaths.contains(newLocalLib)){
                    localLibPaths.add(newLocalLib);
                    resultLocalLibPaths.add(newLocalLib);
                }
            }
        }

        SortedSet<CompilationUnit> localLibCUs = new TreeSet<>(Comparator.comparing(cu -> cu.getPrimaryTypeName().get()));
        SortedSet<TypeDeclaration<?>> localLibs = new TreeSet<>(Comparator.comparing(td -> td.getFullyQualifiedName().get()));
        for(Path localLib : resultLocalLibPaths){
            CompilationUnit cu = StaticJavaParser.parse(localLib);
            resolveStaticImports(sourceRoot, cu);
            localLibCUs.add(cu);
            for(TypeDeclaration<?> type : cu.getTypes()){
                if(!type.getNameAsString().equals("Main"))
                    type.removeModifier(Modifier.Keyword.PUBLIC);
                localLibs.add(type);
            }
        }

        SortedSet<ImportDeclaration> importLibs = new TreeSet<>(Comparator.comparing(NodeWithName::getNameAsString));
        for(CompilationUnit cu : localLibCUs){
            for(ImportDeclaration id : cu.getImports()){
                if(!id.getNameAsString().startsWith("com.guy7cc.abclib4j")){
                    importLibs.add(id);
                }
            }
        }

        CompilationUnit resultCU = new CompilationUnit();
        resultCU.removePackageDeclaration();
        importLibs.forEach(resultCU::addImport);
        localLibs.forEach(resultCU::addType);

        Files.writeString(output, resultCU.toString());
        System.out.println("✅ Main.java generated at: " + output);
    }

    private static Set<Path> collectLocalLibraries(Path sourceRoot, CompilationUnit cu){
        Set<String> importedClasses = cu.getImports().stream()
                .map(ImportDeclaration::getNameAsString)
                .collect(Collectors.toSet());

        Set<Path> localLibraries = new HashSet<>();
        for (String fqcn : importedClasses) {
            if (fqcn.startsWith("com.guy7cc.abclib4j")) {
                Path relPath = Paths.get(fqcn.replace('.', '/') + ".java");
                Path fullPath = sourceRoot.resolve(relPath);
                if (Files.exists(fullPath)) {
                    localLibraries.add(fullPath);
                }
            }
        }

        return localLibraries;
    }

    public static void resolveStaticImports(Path sourceRoot, CompilationUnit cu) {
        Map<String, String> staticIdentifiers = new HashMap<>();

        List<ImportDeclaration> staticImports = cu.getImports().stream()
                .filter(ImportDeclaration::isStatic)
                .toList();

        for (ImportDeclaration id : staticImports) {
            String importName = id.getNameAsString();

            if (!id.isAsterisk()) {
                // import static xxx.yyy.ZZZ.method;
                String[] parts = importName.split("\\.");

                if(importName.startsWith("com.guy7cc.abclib4j")){
                    String className = parts[parts.length - 2];
                    String memberName = parts[parts.length - 1];
                    staticIdentifiers.put(memberName, className);
                } else {
                    String memberName = parts[parts.length - 1];
                    staticIdentifiers.put(memberName, importName.substring(0, importName.lastIndexOf('.')));
                }
            } else {
                // import static xxx.yyy.ZZZ.*;
                String classFqcn = importName;
                String className = classFqcn.substring(classFqcn.lastIndexOf('.') + 1);


                if(classFqcn.startsWith("com.guy7cc.abclib4j")){
                    Path classPath = sourceRoot.resolve(classFqcn.replace('.', '/') + ".java");
                    if (Files.exists(classPath)) {
                        try {
                            CompilationUnit importedCU = StaticJavaParser.parse(classPath);

                            // static method
                            importedCU.findAll(MethodDeclaration.class).forEach(md -> {
                                if (md.isPublic() && md.isStatic()) {
                                    staticIdentifiers.put(md.getNameAsString(), className);
                                }
                            });

                            // static field
                            importedCU.findAll(FieldDeclaration.class).forEach(fd -> {
                                if (fd.isPublic() && fd.isStatic()) {
                                    fd.getVariables().forEach(var -> staticIdentifiers.put(var.getNameAsString(), className));
                                }
                            });

                        } catch (IOException e) {
                            System.err.println("⚠️ Failed to parse: " + classPath + ": " + e.getMessage());
                        }
                    } else {
                        System.err.println("⚠️ Class not found for static import: " + classPath);
                    }
                } else {
                    try {
                        Class<?> clazz = Class.forName(classFqcn);
                        for (Method m : clazz.getDeclaredMethods()) {
                            if (java.lang.reflect.Modifier.isPublic(m.getModifiers()) && java.lang.reflect.Modifier.isStatic(m.getModifiers())) {
                                staticIdentifiers.put(m.getName(), clazz.getCanonicalName());
                            }
                        }
                        for (Field f : clazz.getDeclaredFields()) {
                            if (java.lang.reflect.Modifier.isPublic(f.getModifiers()) && java.lang.reflect.Modifier.isStatic(f.getModifiers())) {
                                staticIdentifiers.put(f.getName(), clazz.getCanonicalName());
                            }
                        }
                    } catch (ClassNotFoundException e) {
                        System.err.println("⚠️ Failed to load standard class: " + classFqcn);
                    }
                }
            }
        }

        // Replace method call: max() → Math.max()
        cu.findAll(MethodCallExpr.class).forEach(mce -> {
            if (mce.getScope().isEmpty()) {
                String name = mce.getNameAsString();
                if (staticIdentifiers.containsKey(name)) {
                    mce.setScope(new NameExpr(staticIdentifiers.get(name)));
                }
            }
        });

        // Replace field access: PI → Math.PI
        cu.findAll(NameExpr.class).forEach(ne -> {
            String name = ne.getNameAsString();
            if (staticIdentifiers.containsKey(name)) {
                Optional<Node> parent = ne.getParentNode();
                if (parent.isEmpty() || !(parent.get() instanceof MethodCallExpr)) {
                    FieldAccessExpr fieldAccess = new FieldAccessExpr(
                            new NameExpr(staticIdentifiers.get(name)), name
                    );
                    ne.replace(fieldAccess);
                }
            }
        });

        // import static 文の削除
        cu.getImports().removeIf(ImportDeclaration::isStatic);
    }
}
