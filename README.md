# Athena: AI-Powered Learning Assistant for Teachers - Mobile Development 📱

Welcome to the mobile development section of the Athena repository! This part of the project focuses on building the Android application that empowers teachers with AI tools, streamlining administrative tasks, enhancing student engagement, and personalizing learning experiences.

## Key Features ✨

- **Document-Based Chatbot**: Interact with a chatbot trained on specific educational materials to answer student questions and provide personalized guidance.
- **Object Detection-Based Attendance**: Automate attendance tracking using object detection technology.
- **(Future Development) Document Reader (OCR)**: Extract text from documents and images for easy access and analysis.
- **(Future Development) Object Detection-Based Answer Sheet Reader**: Automatically grade answer sheets using object detection.

## Programming Languages 🖥️

- **Java**: Primary language for Android app development.
- **JavaScript**: Potential use for web components and integration with React Native for UI development.

## Libraries & Frameworks 🛠️

### Android Development
- **Android SDK**: Official software development kit for Android, providing tools and APIs for building Android apps.
- **Android Jetpack**: Suite of libraries for accelerating Android development and improving app quality.
- **CameraX**: Jetpack library simplifying camera app development and providing consistent camera experiences across devices.

### UI Development
- **React Native (Potential)**: Framework for building native mobile apps using JavaScript and React, offering code reusability across platforms.

## Project Structure 📁

```plaintext
athena/
└── mobile-development/
    ├── app/
    │   ├── src/
    │   │   ├── main/
    │   │   │   ├── manifest/AndroidManifest.xml
    │   │   │   ├── kotlin/
    │   │   │   │   └── com/haikal/athena/
    │   │   │   │       ├── adapter/
    │   │   │   │       │   ├── ChatHistoryAdapter.kt
    │   │   │   │       │   ├── ChatHistoryItem.kt
    │   │   │   │       │   ├── MessageAdapter.kt
    │   │   │   │       │   ├── MessageItem.kt
    │   │   │   │       │   └── SaveAdapter.kt
    │   │   │   │       ├── costumview/
    │   │   │   │       │   ├── InputEmailView.kt
    │   │   │   │       │   ├── InputPasswordView.kt
    │   │   │   │       │   └── InputRePasswordView.kt
    │   │   │   │       ├── data/
    │   │   │   │       │   ├── di/
    │   │   │   │       │   │   └── Injection.kt
    │   │   │   │       │   ├── local/
    │   │   │   │       │   │   ├── entity/
    │   │   │   │       │   │   │   └── Save.kt
    │   │   │   │       │   │   ├── pref/
    │   │   │   │       │   │   │   ├── SessionManager.kt
    │   │   │   │       │   │   │   └── UserModel.kt
    │   │   │   │       │   │   └── room/
    │   │   │   │       │   │       ├── SaveDao.kt
    │   │   │   │       │   │       └── SaveDatabase.kt
    │   │   │   │       │   ├── remote/
    │   │   │   │       │   │   ├── response/
    │   │   │   │       │   │   │   ├── ErrorResponse.kt
    │   │   │   │       │   │   │   ├── LoginResponse.kt
    │   │   │   │       │   │   │   └── RegisterResponse.kt
    │   │   │   │       │   │   ├── retrofit/
    │   │   │   │       │   │   │   ├── ApiConfig.kt
    │   │   │   │       │   │   │   └── ApiService.kt
    │   │   │   │       │   │   └── repository/
    │   │   │   │       │   │       ├── AuthRepository.kt
    │   │   │   │       │   │       └── SaveRepository.kt
    │   │   │   │       │   └── RequestInformation.kt
    │   │   │   │       ├── helper/
    │   │   │   │       │   └── ImageClassifierHelper.kt
    │   │   │   │       ├── ui/
    │   │   │   │       │   ├── auth/
    │   │   │   │       │   │   ├── login/
    │   │   │   │       │   │   │   ├── LoginActivity.kt
    │   │   │   │       │   │   │   └── LoginViewModel.kt
    │   │   │   │       │   │   └── register/
    │   │   │   │       │   │       ├── RegisterActivity.kt
    │   │   │   │       │   │       └── RegisterViewModel.kt
    │   │   │   │       │   ├── features/
    │   │   │   │       │   │   ├── cam/
    │   │   │   │       │   │   │   ├── CamActivity.kt
    │   │   │   │       │   │   │   ├── CameraActivity.kt
    │   │   │   │       │   │   │   ├── ResultActivity.kt
    │   │   │   │       │   │   │   └── ResultViewModel.kt
    │   │   │   │       │   │   └── ocr/
    │   │   │   │       │   │       ├── OCRActivity.kt
    │   │   │   │       │   │       └── OCRViewModel.kt
    │   │   │   │       │   ├── features/
    │   │   │   │       │   │   └── qa/
    │   │   │   │       │   │       └── QAActivity.kt
    │   │   │   │       │   ├── main/
    │   │   │   │       │   │   ├── absent/
    │   │   │   │       │   │   │   ├── AbsentFragment.kt
    │   │   │   │       │   │   │   └── AbsentViewModel.kt
    │   │   │   │       │   │   ├── aichat/
    │   │   │   │       │   │   │   ├── AiChatFragment.kt
    │   │   │   │       │   │   │   └── AiChatViewModel.kt
    │   │   │   │       │   │   ├── home/
    │   │   │   │       │   │   │   ├── DummyData.kt
    │   │   │   │       │   │   │   ├── HistoriQaActivity.kt
    │   │   │   │       │   │   │   ├── HomeFragment.kt
    │   │   │   │       │   │   └── profile/
    │   │   │   │       │   │       ├── ProfileFragment.kt
    │   │   │   │       │   │       └── ProfileViewModel.kt
    │   │   │   │       │   ├── main/
    │   │   │   │       │   │   └── MainActivity.kt
    │   │   │   │       │   ├── onboarding/
    │   │   │   │       │   │   ├── Screen1Fragment.kt
    │   │   │   │       │   │   ├── Screen2Fragment.kt
    │   │   │   │       │   │   ├── Screen3Fragment.kt
    │   │   │   │       │   │   ├── Screen4Fragment.kt
    │   │   │   │       │   │   ├── ViewPagerAdapter.kt
    │   │   │   │       │   │   ├── ViewPagerFragment.kt
    │   │   │   │       │   │   └── ViewPagerFragment2.kt
    │   │   │   │       │   └── splash/
    │   │   │   │       |   ├── SplashScreenActivity.kt
    │   │   │   │       |   └── SplashScreenFragment.kt
    │   │   │   │       └── ViewModelFactory.kt
    │   │   │   ├── res
    │   │   │   └── ml/
    │   │   │       └── model.tflite
    │   ├── build.gradle
    │   └── settings.gradle
    ├── docs/
    ├── tests/
    └── README.md
```

## Setup and Installation 🛠️

### Prerequisites
- Android Studio installed
- Java Development Kit (JDK) 8 or higher
- Git

### Steps

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/yourusername/athena.git
   cd athena/mobile-development
   ```

2. **Open the Project in Android Studio**:
   - Open Android Studio.
   - Select `Open an existing Android Studio project`.
   - Navigate to the `athena/mobile-development` directory and open it.

3. **Build the Project**:
   - Sync the project with Gradle files.
   - Build the project to ensure all dependencies are correctly downloaded and configured.

4. **Run the Application**:
   - Connect an Android device or start an emulator.
   - Click on the `Run` button in Android Studio to install and run the application on your device.

## Project Status 🚀

The mobile development components are actively being developed, with core features of the chatbot and attendance tracking nearing completion. We are continuously refining the user interface and improving overall performance.

## Team Members 👥

- **Muhammad Haikal Fikri** (MD)
- **Bramasta Natanael Dangawa** (MD)

## Disclaimer ⚠️

This project is a work in progress and may contain bugs or unfinished features. Please use it at your own risk.
---

Thank you for contributing to the mobile development section of Athena! Your efforts help enhance the educational experience for teachers and students. 🎓
