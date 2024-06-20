# Athena Learning Assistant - Face Recognition and Object Detection Training

This repository contains code for training object detection and face recognition models. These models are intended for use in Athena Learning Assistant, particularly for face recognition-based attendance systems.

## Requirements

Make sure you have the following libraries installed:

- OpenCV (`opencv-python`)
- Face Recognition (`face_recognition`)
- MoviePy (`moviepy`)
- TensorFlow (`tensorflow`)
- NumPy (`numpy`)
- Pandas (`pandas`)

You can install these libraries using `pip`:

```bash
pip install opencv-python face_recognition moviepy tensorflow numpy pandas
```

## Steps to Train the Models

### 1. Extract Frames from Videos

Frames are extracted from uploaded videos (`class1.mp4`, `class2.mp4`, `class3.mp4`) to detect faces. Run the following code snippet in a Python environment like Jupyter Notebook or Google Colab:

```python
# Code for extracting frames from videos
# Ensure videos are uploaded and paths are correctly set
```

### 2. Detect Faces in Extracted Frames

Using `face_recognition` library, faces are detected in the extracted frames. The detected faces are saved and can be visualized using the provided code.

```python
# Code for detecting faces in extracted frames
```

### 3. Train a Convolutional Neural Network (CNN)

A CNN model is trained using TensorFlow/Keras on the extracted and labeled face images. The model architecture is defined and trained with the extracted faces.

```python
# Code for training a CNN model
```

### 4. Predictions and Visualization

Once trained, the model predicts faces in new frames from videos and provides predictions. Predictions can be visualized using matplotlib.

```python
# Code for making predictions and visualization
```

## Files and Downloads

- **Extracted Faces**: Folder containing extracted faces from videos.
- **predictions.csv**: CSV file containing prediction results.

## Usage

To utilize the trained models for face recognition-based attendance in Athena Learning Assistant:

1. Ensure the required libraries are installed.
2. Run the provided code snippets in a Python environment.
3. Use the trained models for face recognition tasks in your application.

## License

This project is not licensed yet.
