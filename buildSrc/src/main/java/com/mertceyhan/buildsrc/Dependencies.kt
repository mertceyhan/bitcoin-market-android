@file:Suppress("PackageDirectoryMismatch")

object Dependencies {

    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlinVersion}"
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtxVersion}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompatVersion}"
    const val material = "com.google.android.material:material:${Versions.materialVersion}"
    const val junit4 = "junit:junit:${Versions.junit4Version}"
    const val junitExtensions = "androidx.test.ext:junit:${Versions.junitExtensionsVersion}"
    const val junitExtensionsKtx = "androidx.test.ext:junit-ktx:${Versions.junitExtensionsVersion}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCoreVersion}"
    const val daggerHilt = "com.google.dagger:hilt-android:${Versions.daggerHiltVersion}"
    const val daggerHiltCompiler =
        "com.google.dagger:hilt-android-compiler:${Versions.daggerHiltVersion}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"
    const val retrofitMoshiConverter =
        "com.squareup.retrofit2:converter-moshi:${Versions.retrofitVersion}"
    const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okHttpVersion}"
    const val okHttpLoggingInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.okHttpVersion}"
    const val moshi = "com.squareup.moshi:moshi:${Versions.moshiVersion}"
    const val moshiCodegen = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshiVersion}"
    const val mpAndroidChart = "com.github.PhilJay:MPAndroidChart:${Versions.mpAndroidChartVersion}"
    const val lottieCompose = "com.airbnb.android:lottie-compose:${Versions.lottieVersion}"
    const val truth = "com.google.truth:truth:${Versions.truthVersion}"
    const val mockk = "io.mockk:mockk:${Versions.mockkVersion}"
    const val robolectric = "org.robolectric:robolectric:${Versions.robolectricVersion}"
    const val androidArchCoreTest =
        "androidx.arch.core:core-testing:${Versions.androidArchCoreTest}"
    const val coroutinesCore =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesVersion}"
    const val coroutinesTest =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutinesVersion}"
    const val ktlint = "com.pinterest:ktlint:${Versions.ktlintVersion}"
    const val composeMaterial = "androidx.compose.material:material:${Versions.composeVersion}"
    const val composeAnimation = "androidx.compose.animation:animation:${Versions.composeVersion}"
    const val composeTooling = "androidx.compose.ui:ui-tooling:${Versions.composeVersion}"
    const val composeActivity =
        "androidx.activity:activity-compose:${Versions.composeActivityVersion}"
    const val composeViewModel =
        "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.composeViewModelVersion}"
    const val composeLiveData =
        "androidx.compose.runtime:runtime-livedata:${Versions.composeVersion}"
    const val composeUiTest = "androidx.compose.ui:ui-test-junit4:${Versions.composeVersion}"
    const val composeConstraintLayout =
        "androidx.constraintlayout:constraintlayout-compose:${Versions.composeConstraintLayoutVersion}"
    const val composeNavigation =
        "androidx.navigation:navigation-compose:${Versions.composeNavigationVersion}"
    const val hiltNavigationCompose =
        "androidx.hilt:hilt-navigation-compose:${Versions.hiltNavigationCompose}"
    const val accompanistSwipeRefresh =
        "com.google.accompanist:accompanist-swiperefresh:${Versions.accompanistSwipeRefresh}"
    const val glance = "androidx.glance:glance-appwidget:${Versions.glance}"

}
