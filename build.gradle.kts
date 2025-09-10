plugins {
	kotlin("jvm") version "1.9.0" // or latest version
	`maven-publish`
	signing
	id("com.gradleup.nmcp.aggregation").version("1.0.0")
}

group = "io.github.nicepay-dev"
version = "1.0.0"
val artifactId = "nicepay-kotlin-client"

java {
	withSourcesJar()
	withJavadocJar()
	toolchain {
		languageVersion.set(JavaLanguageVersion.of(17))
	}
}

tasks.withType<Javadoc>().configureEach {
	enabled = false // Disable standard Javadoc as we'll use Dokka
}

nmcpAggregation {
	centralPortal {
		username = project.findProperty("mavenCentralUsername").toString()
		password = project.findProperty("mavenCentralPassword").toString()// publish manually from the portal
		publishingType = "USER_MANAGED"
		// or if you want to publish automatically
	}

	// Publish all projects that apply the 'maven-publish' plugin
	publishAllProjectsProbablyBreakingProjectIsolation()
}


repositories {
	mavenCentral()
}

dependencies {
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("commons-codec:commons-codec:1.15")
	implementation("com.google.code.gson:gson:2.10.1")
	implementation("com.squareup.retrofit2:retrofit:2.9.0")
	implementation("com.squareup.retrofit2:converter-jackson:2.9.0")
	implementation("com.squareup.retrofit2:converter-gson:2.9.0")
	implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.17.2")
	implementation("org.slf4j:slf4j-api:2.0.13")
	implementation("org.apache.logging.log4j:log4j-api:2.20.0")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	implementation("ch.qos.logback:logback-classic:1.4.14")
}

tasks.test {
	useJUnitPlatform()
}

publishing {
	publications {
		create<MavenPublication>("mavenJava") {
			groupId = project.group.toString()
			artifactId = artifactId
			version = project.version.toString()

			from(components["java"])
			pom {
				name.set("nicepay-kotlin-client")
				description.set("Official Kotlin client for NICEPAY Payment APIs (SNAP and V2)")
				url.set("https://github.com/nicepay-dev/kotlin-nicepay-lib")

				licenses {
					license {
						name.set("MIT License")
						url.set("https://opensource.org/licenses/MIT")
					}
				}

				developers {
					developer {
						id.set("faridilhamuddin")
						name.set("Farid Ilhamuddin")
						email.set("parid@nicepay.co.id")
					}
					developer {
						id.set("alfredcsimanjuntak")
						name.set("Alfred Chrisdianto Simanjuntak")
						email.set("alfred.chrisdianto@nicepay.co.id")
					}
					developer {
						id.set("ariobimo")
						name.set("Ario Bimo")
						email.set("ario.bimo@nicepay.co.id")
					}
					developer {
						id.set("inkafazarillah")
						name.set("Inka Fazarillah")
						email.set("inka.fazarillah@nicepay.co.id")
					}
				}

				scm {
					connection.set("scm:git:https://github.com/nicepay-dev/kotlin-nicepay-lib.git")
					developerConnection.set("scm:git:ssh://github.com:nicepay-dev/kotlin-nicepay-lib.git")
					url.set("https://github.com/nicepay-dev/kotlin-nicepay-lib")
				}
			}
		}

		repositories {
			maven {
				name = "MavenCentral"
				url = uri("https://s01.oss.sonatype.org/content/repositories/releases/")
				credentials {
					username = project.findProperty("mavenCentralUsername").toString()
					password = project.findProperty("mavenCentralPassword").toString()
				}
			}
		}

	}


}


gradle.taskGraph.whenReady {
	logger.lifecycle("\n=== Build Configuration ===")
	logger.lifecycle("Group: $group")
	logger.lifecycle("Version: $version")

	fun Any?.toDebugString() = this?.toString()?.takeIf { it.isNotBlank() } ?: "NOT FOUND"
	fun Any?.isPresent() = !this?.toString().isNullOrBlank()

	logger.lifecycle("Signing Key ID: ${findProperty("signing.keyId").toDebugString()}")
	logger.lifecycle("Signing Key Present: ${findProperty("signing.key").isPresent()}")
	logger.lifecycle("Signing Password Present: ${findProperty("signing.password").isPresent()}")
	logger.lifecycle("Maven Username Present: ${findProperty("mavenCentralUsername").isPresent()}")
	logger.lifecycle("=========================\n")
}

tasks.register("printSigningConfig") {
	doLast {
		fun Any?.toDebugString() = this?.toString()?.takeIf { it.isNotBlank() } ?: "NOT FOUND"
		fun Any?.isPresent() = !this?.toString().isNullOrBlank()

		println("\n=== Signing Configuration ===")
		println("Key ID: ${findProperty("signing.keyId").toDebugString()}")
		println("Key Present: ${findProperty("signing.key").isPresent()}")
		println("Password Present: ${findProperty("signing.password").isPresent()}")
		println("==========================\n")
	}
}

signing {
	useGpgCmd()
	sign(publishing.publications["mavenJava"])

}