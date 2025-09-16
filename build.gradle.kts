// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
}

allprojects {
    configurations.all {
        resolutionStrategy {
            force(
                "com.squareup:javapoet:1.13.0",
                "com.squareup:kotlinpoet-javapoet:1.13.0"
            )
        }
    }
}