import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
	var kotlinVersion: String by extra
	kotlinVersion = "1.2.10"

	repositories.mavenCentral()

	dependencies {
		classpath(kotlinModule("gradle-plugin", kotlinVersion))
	}
}

apply {
	plugin("kotlin")
	plugin("groovy")
}

val kotlinVersion: String by extra

repositories.mavenCentral()

dependencies {
	compile(kotlinModule("stdlib-jdk8", kotlinVersion))
	testCompile("org.codehaus.groovy:groovy-all:2.4.13")
	testCompile("org.spockframework:spock-core:1.1-groovy-2.4")
	testCompile("cglib:cglib-nodep:3.2.5")
}

tasks.withType<KotlinCompile> {
	kotlinOptions.jvmTarget = "1.8"
}
