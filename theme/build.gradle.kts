plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    compileSdk = Configs.compileSdkVersion

    defaultConfig {
        minSdk = Configs.minSdkVersion
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.kotlinCompilerExtensionVersion
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(Dependencies.composeMaterial)

    testImplementation(Dependencies.junit4)
    testImplementation(Dependencies.junitExtensionsKtx)
    testImplementation(Dependencies.truth)
}