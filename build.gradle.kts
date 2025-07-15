plugins {
	kotlin("jvm") version "1.9.25"
	`maven-publish`
	signing
	id("org.jetbrains.dokka") version "1.9.10"
}

group = "io.github.nicepay-dev"
version = "1.0.0"

java {
	toolchain {
		languageVersion.set(JavaLanguageVersion.of(17))
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("com.google.code.gson:gson:2.10.1")
	implementation("commons-codec:commons-codec:1.15")
	implementation("com.squareup.retrofit2:retrofit:2.9.0")
	implementation("com.squareup.retrofit2:converter-jackson:2.9.0")
	implementation("com.squareup.retrofit2:converter-gson:2.9.0")
	implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.17.2")

	testImplementation(kotlin("test"))
}

tasks.test {
	useJUnitPlatform()
}

// ✅ Register sourcesJar and javadocJar BEFORE using them in publishing
val sourcesJar by tasks.registering(Jar::class) {
	archiveClassifier.set("sources")
	from(sourceSets["main"].allSource)
}

val javadocJar by tasks.registering(Jar::class) {
	dependsOn("dokkaJavadoc")
	archiveClassifier.set("javadoc")
	from(tasks["dokkaJavadoc"])
}

publishing {
	publications {
		create<MavenPublication>("maven") {
			from(components["java"])
			artifact(sourcesJar.get())
			artifact(javadocJar.get())

			groupId = "io.github.nicepay-dev"
			artifactId = "nicepay-kotlin-client"
			version = project.version.toString()

			pom {
				name.set("kotlin-nicepay-lib")
				description.set("NICEPay Kotlin Client Library")
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
					developerConnection.set("scm:git:ssh://git@github.com:nicepay-dev/kotlin-nicepay-lib.git")
					url.set("https://github.com/nicepay-dev/kotlin-nicepay-lib")
				}
			}
		}
	}

	repositories {
		maven {
			name = "sonatype"
			url = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
			credentials {
				username = findProperty("ossrhUsername") as String? ?: ""
				password = findProperty("ossrhPassword") as String? ?: ""
			}
		}
	}
}

signing {
	useInMemoryPgpKeys(
		findProperty("signing.key") as String?,
		findProperty("signing.password") as String?
	)
	sign(publishing.publications["maven"])
}
