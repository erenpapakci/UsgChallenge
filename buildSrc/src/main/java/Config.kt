/*
 * App configuration
 */
object Config {
    const val applicationId = "com.erenpapakci.usgchallenge"
    const val rawPackageName = "com.erenpapakci.usgchallenge"
    const val minSdkVersion = Versions.minSdkVersion
    const val targetSdkVersion = Versions.targetSdkVersion
    const val compileSdkVersion = Versions.compileSdkVersion
    const val buildToolVersion = Versions.buildToolVersion
    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    const val versionCode = 1
    const val versionName = "1.0.0"
}

/*
 * Auto generated buildConfig fileds
 */
object Fields {
    const val rootUrl = "ROOT_URL"
    const val pName = "PACKAGE_NAME"

}

/*
 * Flavor Dimensions
 */
object Dimensions {
    const val default = "default"
}

/*
 * Product Flavors
 */
object Prod {
    const val versionCode = Config.versionCode
    const val versionName = Config.versionName
    const val packageName = Config.rawPackageName
}

object Dev {
    const val suffix = ".dev"
    const val versionCode = Config.versionCode * 10000
    const val versionName = Config.versionName
    const val versionNameSuffix = suffix
    const val applicationIdSuffix = suffix
    const val packageName = Config.rawPackageName + applicationIdSuffix
}