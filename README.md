# 🎓 Smart University Course & Career Advisor

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?logo=springboot&logoColor=white)
![Spring AI](https://img.shields.io/badge/Spring%20AI-6DB33F?logo=spring&logoColor=white)
![Groq](https://img.shields.io/badge/Groq-000000?logo=groq&logoColor=white)
![Java](https://img.shields.io/badge/Java-ED8B00?logo=java&logoColor=white)

A Retrieval-Augmented Generation (RAG) application that helps students find the perfect university courses based on their career goals. It utilizes high-speed inference via Groq and semantic search powered by Spring AI.

---

## 🌟 Key Features

*   **Semantic Search:** Matches queries by meaning, not just exact keywords.
*   **Lightning-Fast Inference:** Leverages Groq's LLaMA 3 models for near-instant responses.
*   **Smart Metadata Filtering:** Restricts searches based on credits and prerequisites.
*   **Automated Data Ingestion:** Loads and embeds JSON course catalogs automatically on startup.
*   **Guided Recommendations:** Connects abstract career ambitions to concrete class paths.

---

## 🛠️ Tech Stack

*   **Framework:** 🍃 Spring Boot 3.x
*   **AI Orchestration:** 🤖 Spring AI
*   **LLM Provider:** ⚡ Groq (via OpenAI-compatible API)
*   **Vector Database:** 🗄️ PGvector (or in-memory `SimpleVectorStore` for local dev)
*   **Language:** ☕ Java 17+

---

## 🏗️ Architecture

The project implements a classic two-part RAG pipeline:
1.  **Ingestion:** Reads `courses.json`, generates vector embeddings, and stores them in the vector database.
2.  **Retrieval & Generation:** Intercepts user queries, retrieves relevant course chunks, and prompts Groq to build a tailored response.

---

## 🚀 Getting Started

### Prerequisites
*   Java 17 or higher
*   A Groq API Key (get one at [://groq.com](https://://groq.com/))
*   Docker (optional, for PGvector)

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com
   cd spring-ai-course-advisor
   ```

2. Set your Groq API key in your environment variables:
   ```bash
   export GROQ_API_KEY="your_actual_api_key_here"
   ```

3. Run the application:
   ```bash
   ./mvnw spring-boot:run
   ```

### 🔌 API Usage

Ask the advisor for recommendations using a standard curl command:

```bash
curl -X GET "http://localhost:8080/api/advisor/ask?query=I%20want%20to%20be%20a%20Data%20Scientist%20but%20I%20hate%20heavy%20coding"
```

---

## 📄 Dataset Structure

Place your custom data in `src/main/resources/courses.json`. 

## 📜 License
This project is licensed under the MIT License.
