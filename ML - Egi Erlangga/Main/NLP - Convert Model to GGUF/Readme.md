# Converting PyTorch Traced Model to GGUF Format (Athena-Llama-2-7b-chat-finetune)

## Overview
This guide explains how to convert a PyTorch traced model (GGML) to the GGUF format using a provided Python script. GGUF is a compressed format for optimized deployment and inference of large language models.

## Installation

### Install Required Packages
Ensure you have the necessary Python packages installed by running:

```bash
pip install -r requirements.txt
```

## Conversion Process

### Load Trained Model
Load the fine-tuned LLaMA 2 model using Transformers and PyTorch:

```python
from transformers import AutoModelForCausalLM, AutoTokenizer
import torch

model_name = "Equinox391/Athena-Llama-2-7b-chat-finetune"
model = AutoModelForCausalLM.from_pretrained(model_name)
tokenizer = AutoTokenizer.from_pretrained(model_name)
```

### Trace the Model
Trace the model using a dummy input to create a PyTorch traced model (GGML):

```python
dummy_input = tokenizer("Apa kabar?", return_tensors="pt").input_ids
traced_model = torch.jit.trace(model, dummy_input)
```

### Save the Traced Model
Save the traced model (GGML) to a file:

```python
traced_model.save("model.ggml")
```

### Convert GGML to GGUF
Use the provided Python script `convert-llama-ggmlv3-to-gguf.py` to convert GGML to GGUF format:

```bash
python convert-llama-ggmlv3-to-gguf.py --input model.ggml --output model.gguf --name "Athena-Llama-2-7b-chat-finetune-GGUF" --eps 1e-5 --context-length 4096
```

- `--input`: Input path to the GGML traced model file.
- `--output`: Output path to save the converted GGUF model file.
- `--name`: Name to assign to the converted GGUF model.
- `--eps`: Epsilon value for compression (default: 1e-5).
- `--context-length`: Maximum context length for the model (default: 4096).

## Additional Notes
- Ensure compatibility of the Python environment and packages used for conversion.
- Adjust parameters (`eps`, `context-length`) as per your specific requirements.
- GGUF format offers compressed model deployment suitable for efficient inference.
