/*
 * Core Libraries
 */
object CoreLibraries {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlinVersion}"
    const val kotlinKtx = "androidx.core:core-ktx:${Versions.kotlinKtxVersion}"

}

/*
 * Support Libraries
 */
object SupportLibraries {
    const val appCompat = "androidx.appcompat:appcompat:${Versions.xVersion}"
    const val design = "com.google.android.material:material:${Versions.supportDesignVersion}"
}

/*
 * Test Libraries
 */
object TestLibraries {
    const val jUnit = "junit:junit:${Versions.jUnitVersion}"
    const val runnner = "androidx.test:runner:${Versions.testRunnerVersion}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCoreVersion}"
    const val mockitoCore = "org.mockito:mockito-core:${Versions.mockitoVersion}"
    const val mockitoAndroid = "org.mockito:mockito-android:${Versions.mockitoVersion}"
}

/*
 * Common Libraries
 */
object Libraries {
    const val dagger2AndroidSupport =
        "com.google.dagger:dagger-android-support:${Versions.dagger2Version}"
    const val dagger2Compiler = "com.google.dagger:dagger-compiler:${Versions.dagger2Version}"
    const val dagger2AndroidProcessor =
        "com.google.dagger:dagger-android-processor:${Versions.dagger2Version}"
    const val javaxAnnotation = "org.glassfish:javax.annotation:${Versions.javaxAnnotationVersion}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"
    const val logInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.okHttpLoggingInterceptorVersion}"
    const val gson = "com.squareup.retrofit2:converter-gson:${Versions.gsonVersion}"
    const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okHttpVersion}"
    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel:${Versions.viewModelVersion}"
    const val lifecycleExtensions =
        "androidx.lifecycle:lifecycle-extensions:${Versions.viewModelVersion}"
    const val rxKotlin = "io.reactivex.rxjava2:rxkotlin:${Versions.rxKotlinVersion}"
    const val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroidVersion}"
    const val retrofitRxAdapter =
        "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofitVersion}"
    const val picasso = "com.squareup.picasso:picasso:${Versions.picassoVersion}"
    const val scalingLayout = "com.github.iammert:ScalingLayout:${Versions.scalingLayoutVersion}"
    const val swipeRefreshLayout = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipeRefreshLayoutVersion}"
    const val constraintlayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayoutVersion}"
    const val recyclerview = "androidx.recyclerview:recyclerview:${Versions.recyclerviewVersion}"
    const val roomRunTime = "androidx.room:room-runtime:${Versions.roomVersion}"
    const val roomRxJava = "androidx.room:room-rxjava2:${Versions.roomVersion}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.roomVersion}"
    const val cardView = "androidx.cardview:cardview:${Versions.cardViewVersion}"
    const val liveChart = "com.github.Pfuster12:LiveChart:${Versions.liveChartVersion}"
}