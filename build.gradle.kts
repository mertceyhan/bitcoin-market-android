import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import org.gradle.api.tasks.testing.TestResult.ResultType

plugins {
    id("com.github.ben-manes.versions") version Versions.gradleVersionsPluginVersion
}

buildscript {

    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(ClassPaths.gradle)
        classpath(ClassPaths.kotlinGradlePlugin)
        classpath(ClassPaths.daggerHiltGradlePlugin)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
    }
}

subprojects {
    tasks.withType(Test::class.java) {
        testLogging {
            showCauses = false
            showExceptions = false
            showStackTraces = false
            showStandardStreams = false

            val ansiReset = "\u001B[0m"
            val ansiGreen = "\u001B[32m"
            val ansiRed = "\u001B[31m"
            val ansiYellow = "\u001B[33m"

            fun getColoredResultType(resultType: ResultType): String {
                return when (resultType) {
                    ResultType.SUCCESS -> "$ansiGreen $resultType $ansiReset"
                    ResultType.FAILURE -> "$ansiRed $resultType $ansiReset"
                    ResultType.SKIPPED -> "$ansiYellow $resultType $ansiReset"
                }
            }

            beforeSuite(
                KotlinClosure1<TestDescriptor, Unit>({
                    if (this.className != null) {
                        println()
                        println(this.className?.substringAfterLast(".").orEmpty())
                    }
                })
            )

            afterTest(
                KotlinClosure2({ desc: TestDescriptor, result: TestResult ->
                    println("${desc.displayName} = ${getColoredResultType(result.resultType)}")
                })
            )

            afterSuite(
                KotlinClosure2({ desc: TestDescriptor, result: TestResult ->
                    if (desc.parent == null) {
                        println("Result: ${result.resultType} (${result.testCount} tests, ${result.successfulTestCount} passed, ${result.failedTestCount} failed, ${result.skippedTestCount} skipped)")
                    }
                })
            )
        }
    }
}

tasks.register("clean", Delete::class.java) {
    delete(rootProject.buildDir)
}

fun isNonStable(version: String): Boolean {
    val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { version.toUpperCase().contains(it) }
    val regex = "^[0-9,.v-]+(-r)?$".toRegex()
    val isStable = stableKeyword || regex.matches(version)
    return isStable.not()
}

tasks.named<DependencyUpdatesTask>("dependencyUpdates").configure {
    rejectVersionIf {
        isNonStable(candidate.version)
    }
}