    plugins {
        id("com.android.application")
        id("com.google.gms.google-services")
    }

    android {
        namespace = "com.michelle.guardachuva"
        compileSdk = 34

        defaultConfig {
            applicationId = "com.michelle.guardachuva"
            minSdk = 21
            targetSdk = 34
            versionCode = 1
            versionName = "1.0"

            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }

        buildTypes {
            release {
                isMinifyEnabled = false
                proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            }
        }
        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }
    }

    dependencies {
        implementation("androidx.cardview:cardview:1.0.0")
        implementation ("androidx.core:core-ktx:1.12.0")
        implementation ("androidx.appcompat:appcompat:1.5.1")
        implementation ("com.google.android.material:material:1.6.1")
        implementation ("androidx.constraintlayout:constraintlayout:2.1.4")
        implementation ("androidx.recyclerview:recyclerview:1.3.2")
        implementation ("com.google.android.gms:play-services-auth:20.3.0")
        implementation ("androidx.navigation:navigation-fragment-ktx:2.5.2")
        implementation ("androidx.navigation:navigation-ui-ktx:2.5.2")
        implementation ("com.google.gms:google-services:4.3.14")
        implementation ("androidx.preference:preference:1.2.0")
        implementation ("androidx.core:core-ktx:+")
        implementation ("androidx.core:core-ktx:+")
        implementation ("androidx.core:core-ktx:+")
        implementation ("androidx.core:core-ktx:+")

        //Firebase
        implementation(platform("com.google.firebase:firebase-bom:32.5.0"))
        implementation ("com.google.firebase:firebase-analytics-ktx")
        implementation ("com.google.firebase:firebase-auth-ktx:22.2.0")
        implementation ("com.google.firebase:firebase-database-ktx:20.3.0")
        implementation("com.google.firebase:firebase-firestore:24.9.1")
    }




