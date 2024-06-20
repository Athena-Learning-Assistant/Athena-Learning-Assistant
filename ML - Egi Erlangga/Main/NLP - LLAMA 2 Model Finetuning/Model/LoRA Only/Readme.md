# Model
If you want to use, clone, or download my model, you can go to:
1. LoRA + ff16 merged model : [Equinox391/Athena-Llama-2-7b-chat-finetune](https://huggingface.co/Equinox391/Athena-Llama-2-7b-chat-finetune)
2. LoRA only : [Equinox391/Athena-LLAMA-2-LoRA-FineTuned](https://huggingface.co/Equinox391/Athena-LLAMA-2-LoRA-FineTuned)
3. GGUF : [Equinox391/Athena-gguf-llama2](https://huggingface.co/Equinox391/Athena-gguf-llama2)

Or if you want to use it directly by using transformer, use this command:
```
# Use a pipeline as a high-level helper
from transformers import pipeline
pipe = pipeline("text-generation", model="Equinox391/Athena-Llama-2-7b-chat-finetune") #Change this to model you want to use
```

```
# Load model directly
from transformers import AutoTokenizer, AutoModelForCausalLM

tokenizer = AutoTokenizer.from_pretrained("Equinox391/Athena-Llama-2-7b-chat-finetune")
model = AutoModelForCausalLM.from_pretrained("Equinox391/Athena-Llama-2-7b-chat-finetune")
```

If you want to clone the model repository
```
# Make sure you have git-lfs installed (https://git-lfs.com)
git lfs install
git clone https://huggingface.co/Equinox391/Athena-LLAMA-2-LoRA-FineTuned
```
```
# If you want to clone without large files - just their pointers
GIT_LFS_SKIP_SMUDGE=1 git clone https://huggingface.co/Equinox391/Athena-LLAMA-2-LoRA-FineTuned
```
