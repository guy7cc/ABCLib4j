plugins {
    java
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

tasks.register<JavaExec>("generateSingleJava") {
    group = "submission"
    description = "Generate Main.java for submission"

    classpath = sourceSets["main"].runtimeClasspath
    mainClass.set("com.guy7cc.abclib4j.tools.Bundler")

    dependsOn("classes")
}