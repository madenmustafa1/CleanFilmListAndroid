plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    id("kotlin-parcelize")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.maden.filmlist"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.maden.filmlist"
        minSdk = 27
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {

        debug {
            buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org/3/\"")
            buildConfigField("String", "ACCESS_KEY", "\"fd2b04342048fa2d5f728561866ad52a\"")
        }

        release {
            buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org/3/\"")
            buildConfigField("String", "ACCESS_KEY", "\"fd2b04342048fa2d5f728561866ad52a\"")

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
        jvmTarget = "1.8"
    }

    buildFeatures {
        buildConfig = true
        viewBinding = true
        dataBinding = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    //Dagger - Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    // ViewModel and LiveData
    implementation(libs.androidx.lifecycle.extensions)
    implementation(libs.androidx.hilt.navigation.fragment)

    //Retrofit - OkHttp
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    //Easy Bitmap - MM
    implementation(libs.easy.bitmap)

    //Local DB - Room
    implementation(libs.androidx.room.runtime)
    annotationProcessor(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)
    kapt(libs.androidx.room.compiler)

    //Test
    testImplementation (libs.mockk)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    //Test - Mockito
    testImplementation (libs.mockito.core)
    testImplementation (libs.mockito.kotlin)

    //Test - JUnit
    testImplementation (libs.junit)

    //Test - Coroutines
    testImplementation (libs.kotlinx.coroutines.test)

}