@file:Suppress("PackageDirectoryMismatch")

object ClassPaths {

    const val gradle = "com.android.tools.build:gradle:${Versions.gradleVersion}"
    const val kotlinGradlePlugin =
        "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersion}"
    const val daggerHiltGradlePlugin =
        "com.google.dagger:hilt-android-gradle-plugin:${Versions.daggerHiltVersion}"
    const val navigationSafeArgs =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigationVersion}"
}
