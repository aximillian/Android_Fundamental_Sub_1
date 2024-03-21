plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id ("kotlin-parcelize")
    id ("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.example.gethub"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.gethub"
        minSdk = 34
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField ("String", "BASE_URL", "\"https://api.github.com/\"")
        buildConfigField ("String", "TOKEN", "\"token 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\"")
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //Image
    implementation("com.github.bumptech.glide:glide:4.16.0")

    //Image Circle
    implementation("de.hdodenhof:circleimageview:3.1.0")

    //networking
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")

    //kodein di
    implementation ("org.kodein.di:kodein-di-generic-jvm:6.3.2")
    implementation ("org.kodein.di:kodein-di-framework-android-x:6.3.2")

    //coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")

    //lifecycle
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")
    implementation ("androidx.lifecycle:lifecycle-common-java8:2.3.1")

    //shimmer
    implementation ("com.facebook.shimmer:shimmer:0.5.0")

    //lottie
    implementation ("com.airbnb.android:lottie:4.2.0")

    androidTestImplementation ("androidx.test:runner:1.5.2")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation ("androidx.test:rules:1.5.0")
    androidTestImplementation ("androidx.test.ext:junit:1.1.5")

    //room
    implementation("androidx.room:room-runtime:2.6.1")

    implementation ("androidx.core:core-splashscreen:1.0.1")
}