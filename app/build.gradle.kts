plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.kotlin.android)
  id("com.google.devtools.ksp")

}

android {
  namespace = "com.example.nestedrecyclerview"
  compileSdk = 36

  defaultConfig {
    applicationId = "com.example.nestedrecyclerview"
    minSdk = 24
    targetSdk = 36
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
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }
  kotlinOptions {
    jvmTarget = "11"
  }
}

dependencies {

  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.appcompat)
  implementation(libs.material)
  implementation(libs.androidx.activity)
  implementation(libs.androidx.constraintlayout)
  testImplementation(libs.junit)
  androidTestImplementation(libs.androidx.junit)
  androidTestImplementation(libs.androidx.espresso.core)

  implementation("com.github.bumptech.glide:glide:4.14.2")
  ksp("com.github.bumptech.glide:ksp:4.14.2")

  implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.10.0")
  implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.10.0")
  implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.10.0")

  implementation ("androidx.compose.material3:material3")
  // or skip Material Design and build directly on top of foundational components
  implementation ("androidx.compose.foundation:foundation")
  // or only import the main APIs for the underlying toolkit systems,
  // such as input and measurement/layout
  implementation ("androidx.compose.ui:ui")

  // Android Studio Preview support
  implementation ("androidx.compose.ui:ui-tooling-preview")
  debugImplementation ("androidx.compose.ui:ui-tooling")

  // Optional - Add window size utils
  implementation ("androidx.compose.material3.adaptive:adaptive")

  // Optional - Integration with activities
  implementation ("androidx.activity:activity-compose:1.11.0")
  // Optional - Integration with ViewModels
  implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.5")
  // Optional - Integration with LiveData
  implementation ("androidx.compose.runtime:runtime-livedata")

  implementation ("androidx.compose.material3:material3:1.3.2")


}