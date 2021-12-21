plugins {
    id("com.android.application")
    id("dagger.hilt.android.plugin")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdk = Configs.compileSdkVersion

    buildToolsVersion = Configs.buildToolsVersion

    defaultConfig {
        applicationId = Configs.applicationId
        minSdk = Configs.minSdkVersion
        targetSdk = Configs.targetSdkVersion
        versionCode = Configs.versionCode
        versionName = Configs.versionName

        testInstrumentationRunner = Configs.testInstrumentationRunner
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
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
        dataBinding = true
        compose = true
    }

    testOptions {
        unitTests.isIncludeAndroidResources = true
    }
}

kapt {
    correctErrorTypes = true
}

dependencies {

    implementation(Dependencies.kotlin)
    implementation(Dependencies.coreKtx)
    implementation(Dependencies.appCompat)
    implementation(Dependencies.material)
    implementation(Dependencies.daggerHilt)
    implementation(Dependencies.retrofit)
    implementation(Dependencies.retrofitMoshiConverter)
    implementation(Dependencies.okHttp)
    implementation(Dependencies.okHttpLoggingInterceptor)
    implementation(Dependencies.moshi)
    implementation(Dependencies.mpAndroidChart)
    implementation(Dependencies.lottieCompose)
    implementation(Dependencies.coroutinesCore)
    implementation(Dependencies.composeMaterial)
    implementation(Dependencies.composeAnimation)
    implementation(Dependencies.composeTooling)
    implementation(Dependencies.composeActivity)
    implementation(Dependencies.composeViewModel)
    implementation(Dependencies.composeLiveData)
    implementation(Dependencies.composeConstraintLayout)
    implementation(Dependencies.composeNavigation)
    implementation(Dependencies.hiltNavigationCompose)
    implementation(Dependencies.accompanistSwipeRefresh)
    implementation(Dependencies.roomKtx)

    kapt(Dependencies.daggerHiltCompiler)
    kapt(Dependencies.moshiCodegen)
    kapt(Dependencies.roomCompiler)

    testImplementation(Dependencies.junit4)
    testImplementation(Dependencies.junitExtensionsKtx)
    testImplementation(Dependencies.truth)
    testImplementation(Dependencies.mockk)
    testImplementation(Dependencies.robolectric)
    testImplementation(Dependencies.androidArchCoreTest)
    testImplementation(Dependencies.coroutinesTest)

    androidTestImplementation(Dependencies.junitExtensions)
    androidTestImplementation(Dependencies.espressoCore)
    androidTestImplementation(Dependencies.composeUiTest)
}
