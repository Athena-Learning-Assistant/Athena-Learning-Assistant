# Athena: AI-Powered Learning Assistant for Teachers - Machine Learning 🧠🤖

Welcome to the machine learning section of the Athena repository! This part of the project focuses on developing and integrating machine learning models to empower the AI-powered learning assistant, enhancing its capabilities in natural language processing, object detection, and more.

## Key Features ✨

- **Document-Based Chatbot**: Interact with a chatbot trained on specific educational materials to answer student questions and provide personalized guidance.
- **Object Detection-Based Attendance**: Automate attendance tracking using object detection technology.
- **(Future Development) Document Reader (OCR)**: Extract text from documents and images for easy access and analysis.
- **(Future Development) Object Detection-Based Answer Sheet Reader**: Automatically grade answer sheets using object detection.

## Programming Languages 🖥️

- **Python**: For building machine learning models, data preprocessing, and backend scripting.

## Libraries & Frameworks 🛠️

### Machine Learning Frameworks
- **TensorFlow**: Popular deep learning framework for building and training models.
- **PyTorch**: Powerful deep learning framework offering flexibility and dynamic computation graphs.

### Natural Language Processing (NLP)
- **Hugging Face Transformers**: Library providing state-of-the-art NLP models (e.g., BERT, GPT-2) and tools for seamless integration.
- **Scikit-learn**: Versatile library for traditional machine learning algorithms and data preprocessing tasks.
- **NLTK (Natural Language Toolkit)**: Comprehensive toolkit for various NLP tasks, including tokenization, stemming, and part-of-speech tagging.
- **SpaCy**: Industrial-strength NLP library known for its speed and accuracy in text processing.

### Object Detection
- **OpenCV**: Library for computer vision tasks, including object detection and image processing.
- **TensorFlow Object Detection API**: API for building, training, and deploying object detection models.

## Project Structure 📁

```plaintext
athena/
└── machine-learning/
    ├── models/
    │   ├── chatbot/
    │   ├── object_detection/
    │   └── ocr/
    ├── data/
    │   ├── raw/
    │   └── processed/
    ├── notebooks/
    │   ├── data_preprocessing.ipynb
    │   ├── model_training.ipynb
    │   └── model_evaluation.ipynb
    ├── src/
    │   ├── preprocessing.py
    │   ├── train.py
    │   └── evaluate.py
    └── README.md
```

## Setup and Installation 🛠️

### Prerequisites
- Python 3.7 or higher
- Virtual environment tool (e.g., `venv`, `virtualenv`)
- Git

### Steps

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/yourusername/athena.git
   cd athena/machine-learning
   ```

2. **Create and Activate a Virtual Environment**:
   ```bash
   python -m venv venv
   source venv/bin/activate  # On Windows: venv\Scripts\activate
   ```

3. **Install Dependencies**:
   ```bash
   pip install -r requirements.txt
   ```

4. **Download and Prepare Data**:
   - Place raw data in the `data/raw/` directory.
   - Run preprocessing scripts or Jupyter notebooks in the `notebooks/` directory to prepare the data.

5. **Train Models**:
   - Use the training scripts or Jupyter notebooks in the `notebooks/` or `src/` directory to train your models.

6. **Evaluate Models**:
   - Evaluate the performance of your models using the provided evaluation scripts or notebooks.

## Project Status 🚀

The machine learning components are in active development, with core features of the chatbot and attendance tracking nearing completion. We are continuously refining models and improving overall performance.

## Team Members 👥

- **Gilang Kurnia Mandari** (ML)
- **Egi Erlangga** (ML)
- **Nikolaus Harris** (ML)

## Disclaimer ⚠️

This project is a work in progress and may contain bugs or unfinished features. Please use it at your own risk.

---

Thank you for contributing to the machine learning section of Athena! Your efforts help enhance the educational experience for teachers and students. 🎓
