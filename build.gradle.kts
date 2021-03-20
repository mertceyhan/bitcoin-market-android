buildscript {

    repositories {
        google()
        jcenter()
        mavenCentral()
    }

    dependencies {
        classpath(ClassPaths.gradle)
        classpath(ClassPaths.kotlinGradlePlugin)
        classpath(ClassPaths.daggerHiltGradlePlugin)
        classpath(ClassPaths.navigationSafeArgs)
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        mavenCentral()
        maven("https://jitpack.io")
    }
}

tasks.register("clean", Delete::class.java) {
    delete(rootProject.buildDir)
}
