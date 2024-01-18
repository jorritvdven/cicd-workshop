import com.github.gradle.node.npm.task.NpmTask

plugins {
    java
    idea
    checkstyle
    id("org.springframework.boot") version "3.2.1"
    id("io.spring.dependency-management") version "1.1.4"
    id("com.github.node-gradle.node") version "7.0.1"
    id("com.github.spotbugs") version "6.0.6"
}

version = "1.0"

repositories {
    mavenCentral()
}

val lombokVersion = "1.18.30"
val junitVersion = "5.10.1"
val frontendDir = "$projectDir/src/main/webapp"

dependencies {
    // Application dependencies
    implementation("org.springframework.boot:spring-boot-starter-web")
    compileOnly("org.projectlombok:lombok:$lombokVersion")
    annotationProcessor("org.projectlombok:lombok:$lombokVersion")

    // Test dependencies
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage")
    }
    testCompileOnly("org.projectlombok:lombok:$lombokVersion")
    testAnnotationProcessor("org.projectlombok:lombok:$lombokVersion")
}

tasks {
    // Configure task  'processResources'
    test {
        useJUnitPlatform()
    }

    // Configure task  'processResources'
    processResources {
        dependsOn("frontendCopy")
    }

    // Configure task  'bootJar'
    bootJar {
        mainClass = "com.jcore.cicd.helloworld.HelloworldApplication"
    }

    // Configure task  'checkstyle'
    checkstyle {
        configFile = File("checkstyle.xml")
        toolVersion = "8.32"
    }

    node {
        download = true
        nodeProjectDir = file(frontendDir)
    }

    // Create a new task named `buildAngular`
    val buildAngular = register<NpmTask>("buildAngular") {
        dependsOn("npmInstall")

        // Set input and output so Gradle can
        // determine if task is up-to-date.
        inputs.dir("${frontendDir}/src")
        inputs.dir("${frontendDir}/node_modules")

        outputs.dir("${frontendDir}/dist")

        args = listOf("run-script", "build")
    }

    // Create a new task names `frontendCopy`
    register<Copy>("frontendCopy") {
        from(buildAngular)
        into("${sourceSets.main.get().output.resourcesDir}/static")
    }
}
