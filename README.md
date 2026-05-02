# 🎓 AI Course & Career Advisor (RAG Powered)

<p align="center">
  <img src="https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white" />
  <img src="https://img.shields.io/badge/Spring%20AI-6DB33F?style=for-the-badge&logo=spring&logoColor=white" />
  <img src="https://img.shields.io/badge/Groq-000000?style=for-the-badge&logoColor=white" />
  <img src="https://img.shields.io/badge/Ollama-000000?style=for-the-badge&logo=ollama&logoColor=white" />
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white" />
</p>

A Retrieval-Augmented Generation (RAG) powered application that provides personalized course and career recommendations based on a user’s interests, strengths, and preferences.

Instead of relying on generic advice, this system uses semantic search and AI reasoning to deliver tailored guidance.

---

## 🌟 Key Features

- **Personalized Career Advice:** Users describe their interests or strengths, and the AI suggests suitable career paths and courses.
- **RAG-Powered Intelligence:** Combines retrieval (vector search) with generation (LLM) for accurate and contextual responses.
- **Semantic Search:** Matches meaning, not just keywords, for better recommendations.
- **Fast AI Inference:** Uses Groq for low-latency, high-performance responses.
- **Efficient Embeddings:** Utilizes Ollama for generating embeddings locally.
- **Lightweight Vector Store:** Uses an in-memory vector store for fast development and testing.
- **Extensible Dataset:** Easily plug in your own course or career datasets.

---

## 🛠️ Tech Stack

- **Framework:** Spring Boot 3.x  
- **AI Orchestration:** Spring AI  
- **LLM Provider:** Groq (OpenAI-compatible API)  
- **Embeddings:** Ollama  
- **Vector Store:** In-memory (`SimpleVectorStore`)  
- **Language:** Java 17+  

---

## 🏗️ Architecture

This project follows a standard RAG (Retrieval-Augmented Generation) architecture:

### 1. Ingestion Phase
- Loads structured data (e.g., courses/careers JSON)
- Generates embeddings using Ollama
- Stores embeddings in an in-memory vector store

### 2. Retrieval & Generation Phase
- User submits a query describing interests or goals
- System performs semantic search to retrieve relevant data
- Retrieved context is passed to Groq LLM
- LLM generates a personalized recommendation

---
