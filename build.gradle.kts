import com.google.protobuf.gradle.id

plugins {
	java
	id("org.springframework.boot") version "3.4.1"
	id("io.spring.dependency-management") version "1.1.7"
	//gRPC
	id("com.google.protobuf") version "0.9.4"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	// gRPC
	implementation("io.grpc:grpc-netty-shaded:1.66.0")
	implementation("io.grpc:grpc-protobuf:1.66.0")
	implementation("io.grpc:grpc-stub:1.66.0")
	implementation("com.google.protobuf:protobuf-java:4.28.2")
	implementation("com.google.protobuf:protobuf-java-util:4.27.4")
	implementation("net.devh:grpc-spring-boot-starter:2.15.0.RELEASE")
	compileOnly("javax.annotation:javax.annotation-api:1.3.2")

	compileOnly("org.projectlombok:lombok")
	runtimeOnly("com.h2database:h2")
	runtimeOnly("com.mysql:mysql-connector-j")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

protobuf {
	protoc {
		artifact = "com.google.protobuf:protoc:4.28.2"
	}
	plugins {
		id("grpc") {
			artifact = "io.grpc:protoc-gen-grpc-java:1.66.0"

		}
	}
	generateProtoTasks {
		all().forEach { task ->
			task.plugins {
				id("grpc")
			}
		}
	}
}
sourceSets {
	main {
		proto {
			srcDir("src/main/proto")
		}
	}
}


tasks.processResources {
	duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}


tasks.withType<Test> {
	useJUnitPlatform()
}
