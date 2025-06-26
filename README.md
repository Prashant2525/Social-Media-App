# Social Media App

A full-featured Android social media application built with Java, Firebase, and modern Android best practices.

## Table of Contents

- [About The Project](#about-the-project)
- [Features](#features)
- [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Installation](#installation)
- [Usage](#usage)
- [Technologies Used](#technologies-used)
- [Project Structure](#project-structure)
- [Contributing](#contributing)
- [License](#license)
- [Contact](#contact)
- [Acknowledgments](#acknowledgments)

## About The Project

This project is a modern Android social media app that allows users to register, log in, create posts (with images or image URLs), like, comment, and manage their profiles. It demonstrates best practices in Android development, Firebase integration, and user experience design.

## Features

- User registration and login (Firebase Authentication)
- Create, edit, and delete posts with images or image URLs
- Like and comment on posts
- Real-time updates with Firebase Realtime Database
- User profile management (update name, password, profile picture via URL)
- Responsive UI with RecyclerView and Glide for image loading
- Exception handling and user-friendly error messages


### Prerequisites

- Android Studio (latest version recommended)
- Android SDK (API 21+)
- A Firebase project with Realtime Database and Authentication enabled

### Installation

1. **Clone the repository:**
   ```bash
   git clone https://github.com/Prashant2525/Social-Media-App.git
   cd social-media-app
   ```
2. **Open in Android Studio.**
3. **Add your `google-services.json` file** to the `app/` directory (from your Firebase Console).
4. **Sync Gradle:** Let Android Studio download all dependencies.
5. **Run the app** on an emulator or physical Android device.

## Usage

- Register a new account or log in with existing credentials.
- Create posts with text and images (by pasting an image URL).
- Like and comment on posts in real time.
- Update your profile information and profile picture.

## Technologies Used

- **Java** (Android SDK)
- **Firebase** (Authentication, Realtime Database, Storage)
- **Glide** (image loading)
- **RecyclerView** (dynamic lists)
- **Material Design** components

## Project Structure

```
app/
├── build/
├── src/
|   ├── androidTest/
│   ├── main/
│   │   ├── java/com/example/socialmediaapp/
│   │   │   ├── activities/
│   │   │   ├── fragments/
│   │   │   ├── adapters/
│   │   │   ├── models/
│   │   ├── res/
│   │   │   ├── menu/
│   │   │   ├── layout/
│   │   │   ├── drawable/
│   │   │   ├── values/
│   │   ├── AndroidManifest.xml
│   ├── build.gradle
├── README.md
```

## Contributing

Contributions are welcome! Please open an issue or submit a pull request for any improvements or bug fixes.

## License

This project is licensed under the [MIT License](LICENSE).

## Contact

- **Your Name:** [dhimalprashant25@gmail.com](mailto:dhimalprashant25@gmail.com)
- **GitHub:** [Prashant2525](https://github.com/Prashant2525)

## Acknowledgments

- [Firebase](https://firebase.google.com/)
- [Glide](https://github.com/bumptech/glide)
- [Android Developers Documentation](https://developer.android.com/docs)
- [Best README Template](https://github.com/othneildrew/Best-README-Template)
