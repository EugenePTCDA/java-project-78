plugins {
    application
    checkstyle
    jacoco
}

checkstyle {
    toolVersion = "8.36.2"
}

jacoco {
    toolVersion = "0.8.11"
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("info.picocli:picocli:4.7.6")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.12.3")
    implementation("org.jacoco:org.jacoco.core:0.8.11")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.14.2")
    implementation("org.apache.commons:commons-lang3:3.12.0")
}

application {
    mainClass.set("hexlet.code.App")
}

tasks.test {
    useJUnitPlatform()
}

tasks.getByName("run", JavaExec::class) {
    standardInput = System.`in`
}

tasks.jacocoTestReport {
    reports {
        xml.required = true
        html.required = true
    }
}