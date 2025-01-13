plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.StepTracker"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.StepTracker"
        minSdk = 34
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.play.services.fitness)
    implementation(libs.preference)
    implementation(libs.mockito.core)
    implementation(libs.junit)
    testImplementation(libs.junit)
    implementation("com.github.PhilJay:MPAndroidChart:v3.1.0")
    testImplementation(libs.core)
    testImplementation(libs.espresso.core)
    testImplementation(libs.ext.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

}