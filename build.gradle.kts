import org.jetbrains.kotlin.com.intellij.openapi.vfs.StandardFileSystems.jar
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	application
	id("org.springframework.boot") version "2.6.6-SNAPSHOT"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.6.21"
	kotlin("plugin.spring") version "1.6.21"
}

group = "com.abc.bleeter"
version = "1.0.0-SNAPSHOT"
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

application {
	mainClass.set("com.abc.bleeter.bleeterbackend.BleeterBackendApplication")
}

springBoot {
	mainClass.set("com.abc.bleeter.bleeterbackend.BleeterBackendApplication")
}

dependencies {
	// implementation("org.springframework.boot:spring-boot-starter-batch")
	implementation("org.springframework.data:spring-data-mongodb:3.3.4")
	implementation("org.springframework.boot:spring-boot-starter-data-mongodb:2.6.6")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.springdoc:springdoc-openapi-ui:1.6.9")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib")
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")
	// testImplementation("org.springframework.boot:spring-boot-starter-test")
	// testImplementation("org.springframework.batch:spring-batch-test")
}

tasks {
	val fatJar = register<Jar>("fatJar") {
		dependsOn.addAll(listOf("compileJava", "compileKotlin", "processResources")) // We need this for Gradle optimization to work
		archiveClassifier.set("standalone") // Naming the jar
		duplicatesStrategy = DuplicatesStrategy.EXCLUDE
		manifest { attributes(mapOf("Main-Class" to application.mainClass)) } // Provided we set it up in the application plugin configuration
		val sourcesMain = sourceSets.main.get()
		val contents = configurations.runtimeClasspath.get()
			.map { if (it.isDirectory) it else zipTree(it) } +
				sourcesMain.output
		from(contents)
	}
	build {
		dependsOn(fatJar) // Trigger fat jar creation during build
	}
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "15"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}