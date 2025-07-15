plugins {
	kotlin("jvm") version "1.9.25"
	id("maven-publish")
	id("signing")
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
		create<MavenPublication>("maven") {
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
	}

	repositories {
		maven {
			name = "centralPortal"
			url = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
			credentials {
				username = findProperty("mavenCentralUsername") as String? ?: System.getenv("MAVEN_CENTRAL_USERNAME")
				password = findProperty("mavenCentralPassword") as String? ?: System.getenv("MAVEN_CENTRAL_PASSWORD")
			}
		}
	}
}

signing {
	sign(publishing.publications["maven"])
}
