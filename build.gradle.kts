plugins {
    id("java")
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("junit:junit:4.12")
    implementation("org.springframework:spring-context:5.2.0.RELEASE")
    implementation("org.springframework:spring-jdbc:5.2.0.RELEASE")
    implementation("org.springframework:spring-test:5.2.0.RELEASE")
    implementation("org.springframework:spring-context-support:5.2.0.RELEASE")
    implementation("org.mariadb.jdbc:mariadb-java-client:2.5.1")
}