plugins {
    java
    antlr
    id("com.gradleup.shadow") version "9.6.1"
}

group = "cz.zcu"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:6.0.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    antlr("org.antlr:antlr4:4.13.1")
}

tasks.test {
    useJUnitPlatform()
}

tasks.jar {
    manifest {
        attributes (
            "Main-class" to "cz.zcu.Main",
        )
    }
}

tasks.generateGrammarSource {
    // my chceme Visitor pro lepsi kontrolu, listener je pak jen zbytecne navic
    arguments = arguments + listOf("-visitor", "-no-listener")
}
