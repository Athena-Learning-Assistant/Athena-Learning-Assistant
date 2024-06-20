# Athena: AI-Powered Learning Assistant for Teachers - Cloud Computing ☁️💻

Welcome to the cloud computing section of the Athena repository! This part of the project focuses on leveraging cloud services to support the AI-powered learning assistant, ensuring scalability, reliability, and efficiency.

## Key Features ✨

- **Backend Hosting**: Utilize Google Cloud Platform (GCP) for hosting the backend infrastructure.
- **Machine Learning Model Deployment**: Deploy and manage machine learning models on GCP.
- **Database Management**: Use GCP services (e.g., Firebase) for database and authentication management.

## Cloud Services Utilized 🌐

### Google Cloud Platform (GCP)
- **Cloud Run**: For serverless service to host backend services.
- **Cloud Storage**: Secure and scalable object storage for storing educational materials, images, and documents.
- **Cloud Functions**: Serverless execution environment for building and connecting cloud services.
- **Firebase**: For real-time database and user authentication services.
- **AI Platform**: For training and deploying machine learning models.

### Deployment Architecture 📊

1. **Frontend (Android App)**:
   - User interacts with the Android app.
   - App sends requests to backend services hosted on GCP.

2. **Backend Services**:
   - Hosted on Google Cloud Run or Cloud Functions.
   - Handles requests from the app, processes data, and communicates with the database.

3. **Machine Learning Models**:
   - Deployed on GCP AI Platform.
   - Models are used for NLP tasks, object detection, and other AI functionalities.

4. **Database and Authentication**:
   - Managed using Firebase.
   - Provides real-time data synchronization and secure user authentication.

## Setup and Deployment 🛠️

### Prerequisites
- Google Cloud Platform account.
- Firebase project setup.
- GCP SDK installed on your local machine.

### Api Documentation
- **For Api Documentation you can visit this link**
   - https://documenter.getpostman.com/view/27889467/2sA3XV7JsC
   

### Steps

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/yourusername/athena.git
   cd athena/cloud-computing
   ```

2. **Set Up GCP Project**:
   - Create a new project on GCP.
   - Enable necessary APIs: Compute Engine, Cloud Storage, Cloud Functions, AI Platform, Firebase.

3. **Deploy Backend Services**:
   - Use GCP Cloud Functions or Compute Engine to deploy backend services.
   - Ensure services are configured to handle requests from the Android app.

4. **Deploy Machine Learning Models**:
   - Train models using AI Platform.
   - Deploy models to AI Platform for serving predictions.

5. **Configure Firebase**:
   - Set up Firebase in your GCP project.
   - Configure real-time database and authentication.

6. **Update Android App Configuration**:
   - Ensure the app is configured to communicate with the backend services and Firebase.

7. **Create Config Folder**:
   - Ensure the config folder is located in root.

8. **Install Required Dependencies**:
   ```npm install
   ```

9. **Run Script**:
   ```npm run start
   ```

9. **Run Script**:
   ```gcloud run deploy
   ```

## Project Status 🚀

The cloud computing components are actively being developed and refined to ensure robust support for Athena's AI features. We are focused on optimizing performance, security, and scalability.

## Team Members 👥

- **Muhammad Dzaki Fakhrezi** (CC)
- **Juliandi Kurniansyah** (CC)

## Disclaimer ⚠️

This project is a work in progress and may contain bugs or unfinished features. Please use it at your own risk.

---

Thank you for contributing to the cloud computing section of Athena! Your efforts help enhance the educational experience for teachers and students. 🎓
