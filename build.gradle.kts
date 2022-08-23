import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.6.6-SNAPSHOT"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.7.10"
	kotlin("kapt") version "1.7.10"
	kotlin("plugin.spring") version "1.6.21"
	kotlin("plugin.lombok") version "1.7.10"
}

group = "com.abc.bleeter"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_15

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
	maven { url = uri("https://repo.spring.io/milestone") }
	maven { url = uri("https://repo.spring.io/snapshot") }
}

dependencies {
	// implementation("org.springframework.boot:spring-boot-starter-batch")
	implementation("org.springframework.data:spring-data-mongodb:3.3.4")
	implementation("org.springframework.boot:spring-boot-starter-data-mongodb:2.6.6")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.google.code.gson:gson:2.8.5")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib")
	implementation("org.mapstruct:mapstruct:1.5.0.RC1")
	implementation("org.springdoc:springdoc-openapi-ui:1.6.8")
	implementation("org.hibernate:hibernate-validator:7.0.4.Final")
	implementation("org.postgresql:postgresql:42.4.2")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa:2.6.6")
	kapt("org.mapstruct:mapstruct-processor:1.5.0.RC1")
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group="org.junit.vintage", module="junit-vintage-engine")
		exclude(group="org.mockito", module="mockito-core")
	}
	testImplementation("org.springframework.batch:spring-batch-test")
	testImplementation("org.junit.jupiter:junit-jupiter-api:5.3.1")
	testImplementation("org.junit.jupiter:junit-jupiter-engine:5.3.1")
	testImplementation("org.junit.jupiter:junit-jupiter-params:5.3.1")
	testImplementation("org.mockito:mockito-core:4.5.0")
	testImplementation("org.mockito:mockito-all:1.10.19")
	testImplementation("com.ninja-squad:springmockk:3.0.1")
	testImplementation("net.bytebuddy:byte-buddy:1.12.9")
	testImplementation("net.bytebuddy:byte-buddy-agent:1.12.9")
	testImplementation("org.objenesis:objenesis:3.2")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "15"
	}
	allOpen {     annotation("javax.persistence.Entity") }
}

tasks.withType<Test> {
	useJUnitPlatform()
}

fun kapt(options: String) {
}
