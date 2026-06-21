# Fungal Insight 🍄

Fungal Insight is an Android application designed to help nature enthusiasts identify mushrooms using the power of Gemini AI. By simply taking a photo, users can receive instant information about mushroom species, characteristics, and critical safety warnings.

## Features ✨

- **Instant Identification**: Use the camera to capture a mushroom and get an AI-powered identification.
- **Expert Knowledge**: Detailed information on Finnish mushroom species, including scientific names, characteristics, and habitats.
- **Safety First**: Prioritizes user safety with prominent warnings and look-alike information.
- **Modern UI**: Built with Jetpack Compose for a smooth and responsive experience.
- **Localized**: Full support for the Finnish language.

## Technology Stack 🛠️

- **Language**: [Kotlin](https://kotlinlang.org/)
- **UI Framework**: [Jetpack Compose](https://developer.android.com/jetpack/compose)
- **AI Integration**: [Gemini AI](https://deepmind.google/technologies/gemini/) via Firebase Vertex AI (using `gemini-3.5-flash`)
- **Navigation**: [Navigation 3](https://developer.android.com/guide/navigation/navigation-3)
- **Concurrency**: Kotlin Coroutines & Flow
- **Dependency Management**: Gradle Version Catalog (libs.versions.toml)

## Getting Started 🚀

### Prerequisites

- Android Studio Ladybug (or newer)
- Android SDK 30+
- A Firebase project with Vertex AI enabled

### Setup

1.  Clone the repository:
    ```bash
    git clone https://github.com/kypeli/Fungal-Insight.git
    ```
2.  Open the project in Android Studio.
3.  Add your `google-services.json` file to the `app/` directory.
4.  Build and run the application on your device or emulator.

## Project Structure 📂

- `app/src/main/java/com/kypeli/mushrooms/data`: Contains `GeminiService` for AI interaction and `MushroomRepository`.
- `app/src/main/java/com/kypeli/mushrooms/ui`: UI components and screens built with Compose.
- `app/src/main/java/com/kypeli/mushrooms/viewmodel`: `IdentificationViewModel` for state management.
- `app/src/main/res/xml/file_paths.xml`: FileProvider configuration for camera photo access.

## Disclaimer ⚠️

Identifying mushrooms from a photo is never 100% reliable. **NEVER** consume any wild mushroom unless you are absolutely 100% certain of its identity from hands-on expert verification. This app is for educational and informational purposes only.

## License 📄

This project is licensed under the Apache License 2.0.
