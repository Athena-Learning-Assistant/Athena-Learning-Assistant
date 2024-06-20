## RAG System Using Llama2 with Hugging Face
![Athena RAG Workflow](images/RAG.png)
### Overview

This project demonstrates how to build a Retrieval-Augmented Generation (RAG) system using Llama2 and Hugging Face. The RAG system combines retrieval mechanisms with generative models to provide accurate and context-aware responses to queries based on a given set of documents. Notes that the model i used here is a finetuned version of llama 2, so if you want to know more about how to deploy your own model, you can get it here :

feel fre to visit my huggingface hub at : Equinox391

### Requirements

Make sure to install the following dependencies:

```sh
pip install pypdf transformers einops accelerate langchain bitsandbytes sentence-transformers llama_index llama-index-llms-huggingface langchain-community llama-index-embeddings-langchain
```

### Project Setup

1. **Data Import**: Download the required data files from the provided source.

2. **Libraries Import**: Import necessary libraries for PDF manipulation, transformer models, and other utilities.

```python
import os
import sys
import shutil
from tempfile import NamedTemporaryFile
from urllib.request import urlopen
from zipfile import ZipFile
import tarfile
import torch
from llama_index.core import VectorStoreIndex, SimpleDirectoryReader, ServiceContext
from llama_index.llms.huggingface import HuggingFaceLLM
from llama_index.core.prompts.prompts import SimpleInputPrompt
from huggingface_hub import notebook_login
from langchain.embeddings.huggingface import HuggingFaceEmbeddings
from llama_index.embeddings.langchain import LangchainEmbedding
```

3. **Load Data Files**: Use `SimpleDirectoryReader` to load your documents from the specified directory.

```python
documents = SimpleDirectoryReader("/kaggle/input/paper-nurul/").load_data()
```

4. **Setup Model and Tokenizer**:

```python
system_prompt = """
Kamu adalah seorang asisten guru. tugas kamu adalah memberikan semua jawaban dari pertanyaan yang ditanyakan, dan permintaan yang diminta oleh guru seakurat mungkin berdasarkan instruksi dan konteks yang diberikan. Jika kamu tidak tahu jawabannya, bilang kamu tidak mengetahui hal tersebut karena keterbatasan pengetahuan.
"""
query_wrapper_prompt = SimpleInputPrompt("{query_str}")

llm = HuggingFaceLLM(
    context_window=4096,
    max_new_tokens=512,
    generate_kwargs={"temperature": 0.5, "do_sample": False},
    system_prompt=system_prompt,
    query_wrapper_prompt=query_wrapper_prompt,
    tokenizer_name="Equinox391/Athena-Llama-2-7b-chat-finetune",
    model_name="Equinox391/Athena-Llama-2-7b-chat-finetune",
    device_map="auto",
    model_kwargs={"torch_dtype": torch.float16, "load_in_8bit": True}
)
```

5. **Initialize Embeddings**:

```python
embed_model = LangchainEmbedding(
    HuggingFaceEmbeddings(model_name="Hvare/Athena-indobert-finetuned-indonli-SentenceTransformer")
)
```

6. **Setup Service Context**:

```python
service_context = ServiceContext.from_defaults(
    chunk_size=1024,
    llm=llm,
    embed_model=embed_model
)
```

7. **Create Vector Store Index**:

```python
index = VectorStoreIndex.from_documents(documents, service_context=service_context)
```

8. **Query Engine**:

```python
query_engine = index.as_query_engine()
```

### Testing the Model

Run a test query to ensure the model is working correctly:

```python
response = query_engine.query("Apa itu lstm?")
print(response)
```

### What is RAG?

Retrieval-Augmented Generation (RAG) combines retrieval mechanisms with generative models to provide enhanced and context-aware responses. It involves retrieving relevant documents or passages from a knowledge base and using these as context to generate more accurate and informative answers. This approach leverages both the retrieval capabilities of search engines and the generative capabilities of language models to improve response quality.

By following this guide, you will be able to set up a robust RAG system that leverages the strengths of both retrieval and generation, providing comprehensive and contextually relevant answers based on your document corpus.

### License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---

Feel free to explore and extend this project to suit your needs. Contributions are welcome!
