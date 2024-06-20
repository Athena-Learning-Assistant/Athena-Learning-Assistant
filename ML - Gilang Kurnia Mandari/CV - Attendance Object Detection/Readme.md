## README.md

# Athena Learning Assistant: Face Recognition-Based Attendance System

This project involves the development of a face recognition-based attendance system for an app called Athena Learning Assistant. The system uses machine learning techniques to detect faces in videos and classify them for attendance purposes.

## Table of Contents
- [Introduction](#introduction)
- [Installation](#installation)
- [Data Preparation](#data-preparation)
- [Face Detection](#face-detection)
- [Face Recognition](#face-recognition)
- [Training the Model](#training-the-model)
- [Prediction](#prediction)
- [Visualization](#visualization)
- [Saving Predictions](#saving-predictions)
- [Conclusion](#conclusion)

## Introduction
Athena Learning Assistant is an application that leverages face recognition technology to automate the process of taking attendance. This project extracts frames from videos, detects faces, and trains a neural network to recognize these faces for attendance purposes.

## Installation
To get started, you need to install the required libraries. Run the following commands to install them:

```bash
pip install opencv-python face_recognition moviepy dlib==19.18.0 tensorflow
```

## Data Preparation
First, upload your video files and extract frames from them. The frames will be saved in separate folders for each video.

```python
import cv2
import os

def extract_frames_from_videos(video_paths, output_folder_prefix):
    for idx, video_path in enumerate(video_paths):
        output_folder = f"{output_folder_prefix}_{idx}"
        if not os.path.exists(output_folder):
            os.makedirs(output_folder)

        video_capture = cv2.VideoCapture(video_path)
        success, image = video_capture.read()
        count = 0

        while success:
            cv2.imwrite(os.path.join(output_folder, f"frame{count}.jpg"), image)
            success, image = video_capture.read()
            count += 1

video_files = ['class1.mp4', 'class2.mp4', 'class3.mp4']
output_folder_prefix = 'video_frames'
extract_frames_from_videos(video_files, output_folder_prefix)
```

## Face Detection
Detect faces in the extracted frames using the `face_recognition` library.

```python
import face_recognition
import os

def detect_faces_in_multiple_folders(folders):
    all_face_locations = []
    for folder in folders:
        face_locations = []
        frame_files = [f for f in os.listdir(folder) if os.path.isfile(os.path.join(folder, f))]

        for frame_file in frame_files:
            frame_path = os.path.join(folder, frame_file)
            image = face_recognition.load_image_file(frame_path)
            face_locations_in_frame = face_recognition.face_locations(image, model="hog")

            for face_location in face_locations_in_frame:
                face_locations.append((frame_path, face_location))

        all_face_locations.append(face_locations)
    return all_face_locations

folders = [f"video_frames_{i}" for i in range(len(video_files))]
all_face_locations = detect_faces_in_multiple_folders(folders)
print(f"Detected faces in {sum(len(locations) for locations in all_face_locations)} frames.")
```

## Face Recognition
Display the detected faces with bounding boxes.

```python
import cv2
import matplotlib.pyplot as plt

def show_faces(face_locations):
    for frame_path, face_location in face_locations:
        image = cv2.imread(frame_path)
        top, right, bottom, left = face_location
        cv2.rectangle(image, (left, top), (right, bottom), (0, 255, 0), 2)
        plt.imshow(cv2.cvtColor(image, cv2.COLOR_BGR2RGB))
        plt.show()

combined_face_locations = [item for sublist in all_face_locations for item in sublist]
show_faces(combined_face_locations)
```

## Training the Model
Extract faces and prepare the dataset for training a neural network.

```python
import numpy as np
from sklearn.model_selection import train_test_split
import os
import cv2

def load_data(folder_path, target_size=(100, 100)):
    images = []
    labels = []
    for filename in os.listdir(folder_path):
        img_path = os.path.join(folder_path, filename)
        label = filename.split("_")[0]
        image = cv2.imread(img_path)
        resized_image = cv2.resize(image, target_size)
        images.append(resized_image)
        labels.append(label)
    return np.array(images), np.array(labels)

extracted_faces_folder = 'extracted_faces'
images, labels = load_data(extracted_faces_folder)
train_images, test_images, train_labels, test_labels = train_test_split(images, labels, test_size=0.2, random_state=42)

import tensorflow as tf
from tensorflow.keras import layers, models

def create_model(input_shape, num_classes):
    model = models.Sequential()
    model.add(layers.Conv2D(32, (3, 3), activation='relu', input_shape=input_shape))
    model.add(layers.MaxPooling2D((2, 2)))
    model.add(layers.Conv2D(64, (3, 3), activation='relu'))
    model.add(layers.MaxPooling2D((2, 2)))
    model.add(layers.Conv2D(64, (3, 3), activation='relu'))
    model.add(layers.Flatten())
    model.add(layers.Dense(64, activation='relu'))
    model.add(layers.Dense(num_classes, activation='softmax'))
    return model

input_shape = (100, 100, 3)
num_classes = len(set(labels))
model = create_model(input_shape, num_classes)

from sklearn.preprocessing import LabelEncoder

label_encoder = LabelEncoder()
train_labels_encoded = label_encoder.fit_transform(train_labels)
test_labels_encoded = label_encoder.transform(test_labels)

model.compile(optimizer='adam',
              loss='sparse_categorical_crossentropy',
              metrics=['accuracy'])

model.fit(train_images, train_labels_encoded, epochs=10, validation_data=(test_images, test_labels_encoded))
```

## Prediction
Use the trained model to make predictions on frames from the videos.

```python
import cv2
import numpy as np

video_paths = ['class1.mp4', 'class2.mp4', 'class3.mp4']

def preprocess_image(image):
    image = image / 255.0
    return image

for video_path in video_paths:
    video = cv2.VideoCapture(video_path)
    if not video.isOpened():
        print(f"Failed to open video {video_path}.")
    else:
        print(f"Video {video_path} opened successfully.")
        fps = int(video.get(cv2.CAP_PROP_FPS))
        while True:
            ret, frame = video.read()
            if not ret:
                print(f"Video {video_path} processing completed.")
                break
            resized_frame = cv2.resize(frame, (100, 100))
            preprocessed_frame = preprocess_image(resized_frame)
            input_data = np.expand_dims(preprocessed_frame, axis=0)
            predictions = model.predict(input_data)
            print(f"Predictions for frame in {video_path}:", predictions)
        video.release()
```

## Visualization
Visualize the predictions as a bar chart.

```python
import matplotlib.pyplot as plt

predictions = [0.2, 0.5, 0.3]
class_labels = ['Class 1', 'Class 2', 'Class 3']

plt.figure(figsize=(8, 6))
plt.bar(class_labels, predictions, color='blue')
plt.xlabel('Classes')
plt.ylabel('Predicted Probabilities')
plt.title('Predictions')
plt.ylim(0, 1)
plt.grid(axis='y', linestyle='--', alpha=0.7)
plt.show()
```

