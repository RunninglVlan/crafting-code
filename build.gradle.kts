import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	kotlin("jvm") version "1.2.61"
	groovy
}

repositories.mavenCentral()

dependencies {
	compile(kotlin("stdlib-jdk8"))

	testCompile("org.codehaus.groovy:groovy-all:2.4.13")
	testCompile("org.spockframework:spock-core:1.1-groovy-2.4")
	testCompile("cglib:cglib-nodep:3.2.5")
}

tasks.withType<KotlinCompile> {
	kotlinOptions.jvmTarget = "1.8"
}
