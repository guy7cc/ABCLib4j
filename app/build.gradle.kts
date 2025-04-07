plugins {
    java
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.github.javaparser:javaparser-core:3.26.4")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

application {
    mainClass = "com.guy7cc.abclib4j.tools.Bundler"
}

tasks.register<JavaExec>("generateSingleJava") {
    group = "submission"
    description = "Generate Main.java for submission"

    classpath = sourceSets["main"].runtimeClasspath
    mainClass.set("com.guy7cc.abclib4j.tools.Bundler")

    dependsOn("classes")
}

tasks.register("printSubmissionFile") {
    group = "submission"
    description = "Print the path of the generated Main.java"

    dependsOn("generateSingleJava")

    doLast {
        println("✅ Generated Main.java at: build/generated/Main.java")
    }
}
