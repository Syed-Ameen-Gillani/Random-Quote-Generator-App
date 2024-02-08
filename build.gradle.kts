// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.2.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id("com.github.ben-manes.versions") version "0.39.0" apply false
    id("io.gitlab.arturbosch.detekt") version "1.18.1" apply false
    id("org.jlleitschuh.gradle.ktlint") version "10.2.0" apply false
    id("org.jetbrains.dokka") version "1.5.0" apply false
    id("org.jetbrains.kotlin.plugin.serialization") version "1.5.31" apply false
    id("com.google.firebase.crashlytics") version "2.8.1" apply false
    id("com.google.gms.google-services") version "4.3.10" apply false
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin") version "2.0.0" apply false
    id("org.jetbrains.kotlin.kapt") version "1.5.31" apply false
    id("com.jfrog.artifactory") version "4.28.1" apply false
    id("com.google.relay") version "0.3.00" apply false
}