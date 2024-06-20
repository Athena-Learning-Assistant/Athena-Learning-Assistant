## Finding dataset in internet
Here's the place where we find the best dataset:
1. Kaggle
2. Huggingface
3. Google Scholars
4. Satu Data Indonesia

But due to MVP as goals now, we use some light dataset from huggingface.

## Creating your own dataset using ChatGPT / Gemini extension in Spreadsheet

On this step, it required you to install some chatgpt or gemini extension for spreadsheet, if you have token for openai and gemini, you can also use the app script to auto generate data.

This is the step to semi auto-generate the dataset for your llama.

1. You need to make some context column for making the dataset, in my case i will use "Tingkat", "Mata Pelajaran", "Kategori",	"Subkategori" in Salinan Dataset Soal.xml
2. Then make one column for concatenate the context, in my case it will be "Untuk" column, and use this formula:
   ```spread sheet formula
   =CONCATENATE(A2;" ";B2;" ";C2;" ";D2;)
   ```
3. After you have installe the extension, you can activate it and use this formula:
   ```extension prompt
   =GPT("buat 1 pertanyaan ujian tanpa disertai jawaban saja, tanpa kata-kata lain selain soal untuk "; E2)
   ```
4. Now you have your question column, do the answer based on your question, and use the prompt to make answer based on your needs!

## Creating and Uploading a Dataset using Hugging Face

This a step-by-step guide for creating a dataset formatted for Llama training using the Hugging Face `datasets` library and uploading it to the Hugging Face Hub.

### Overview

This project demonstrates how to:

1. Load a dataset from Hugging Face or your own custom dataset.
2. Format the data for Llama training.
3. Convert the data into a Hugging Face dataset format.
4. Upload the dataset to the Hugging Face Hub.

### Requirements

Ensure you have the following dependencies installed:

```sh
pip install pandas datasets
```

### Steps

#### 1. Import Libraries

First, import the necessary libraries.

```python
import pandas as pd
from datasets import load_dataset, Dataset
```

#### 2. Load the Dataset

Load the dataset from Hugging Face.

```python
# Load dataset
dataset = load_dataset("indonlp/cendol_collection_v2")

# Extract the train split
train_data = dataset['train']
```

#### 3. Format the Data

Process the data to create a `text` column with the desired format.

```python
# Function to format the text
def format_text(row):
    return f"<s>[INST]{row['input']}[/INST]{row['output']}</s>"

# Apply format_text function to each row in train_data
formatted_data = train_data.map(lambda row: {'text': format_text(row)})

# Convert to DataFrame with a 'text' column
df = pd.DataFrame({'text': formatted_data['text']})

# Set Pandas to display all cell content
pd.set_option('display.max_colwidth', None)

# Display the first row
print(df.iloc[0])
```

#### 4. Convert to Hugging Face Dataset

Convert your data into the Hugging Face `Dataset` format.

```python
# Convert to Hugging Face Dataset
dataset_hf = Dataset.from_dict({'text': formatted_data['text']})
```

#### 5. Upload to Hugging Face Hub

Upload the dataset to the Hugging Face Hub. Ensure you are logged in to your Hugging Face account.

```python
from datasets import push_to_hub

# Define dataset name and description
dataset_name = "Equinox391/Athena-Big-indonlpV2-llama"

# Upload dataset to Hugging Face
push_to_hub(dataset=dataset_hf, repo_name=dataset_name)
```

### Additional Information

- Ensure you replace `"Equinox391/Athena-Big-indonlpV2-llama"` with your desired dataset name.
- If you encounter any issues with uploading, make sure you have the necessary permissions and are logged in to your Hugging Face account.

### License

This project is not licensed yet

---

This README.md file provides clear instructions for creating, formatting, and uploading a dataset using Hugging Face. Make sure to adjust paths and names as per your specific requirements.
