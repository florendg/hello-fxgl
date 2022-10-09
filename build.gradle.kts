plugins {
    application
}

repositories {
    mavenCentral()
}

var currentOS = org.gradle.nativeplatform.platform.internal.DefaultNativePlatform.getCurrentOperatingSystem()
var platform = ""
if(currentOS.isMacOsX) {
    platform = "mac"
} else if(currentOS.isLinux) {
    platform = "linux"
} else if(currentOS.isWindows) {
    platform = "win"
}

val javaFXVersion = 18
val appClassName = "dev.vultureweb.fxgl.App"
val appModuleName = "dev.vultureweb.fxgl"

val compiler= javaToolchains.compilerFor {
    languageVersion.set(JavaLanguageVersion.of(JavaVersion.VERSION_17.majorVersion))
}

dependencies {
    implementation("org.openjfx:javafx-controls:${javaFXVersion}:${platform}")
    implementation("org.openjfx:javafx-base:${javaFXVersion}:${platform}")
    implementation("org.openjfx:javafx-graphics:${javaFXVersion}:${platform}")
    implementation("org.openjfx:javafx-fxml:${javaFXVersion}:${platform}")
    implementation("org.openjfx:javafx-media:${javaFXVersion}:${platform}")
    implementation("com.github.almasb:fxgl:17.2")
}

application {
    mainModule.set(appModuleName)
    mainClass.set(appClassName)
}

java {
    modularity.inferModulePath.set(true)
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

