
# Observability Framework

This repository demonstrates **Observability Framework** that automatically traces method calls and logs request flow with total duration and also get the duration of external call like restTemplate, DB, DDB and others. This helps to understand request flow and system latency in a summarized format.

## 📘 Project Structure

```
observability-example/
├── observability-framework/   # Core Observability library
└── observability-demo-service/ # Demo Spring Boot microservice using it
```

## Tech Stack
Java 17 with Spring Boot 3+

## 🚀 How It Works

- The **Observability Filter** starts a new trace for each incoming request.
- The **AOP Aspect** intercepts all method calls inside `com.example.demo` package.
- The trace path and execution duration are logged automatically in structured format.



## 🧩 How to Run

1. Build the observability framework first:
   ```bash
   cd observability-framework
   mvn clean install
   ```

2. Run the demo service:
   ```bash
   cd ../observability-demo-service
   mvn spring-boot:run
   ```

3. Access the demo API:
   ```bash
   curl http://localhost:8080/api/hello
   ```

4. Observe logs in console showing trace details.


## ⚙️ Design Approach — SOLID in Action  

Here’s how each SOLID principle guided the design:  

1. **S – Single Responsibility Principle**  
   Each component does exactly one thing.  
   - `AutoTraceAspect`: Intercepts and records method-level activity.  
   - `WorkflowContext`: Maintains per-request trace data.  
   - `InfoSummarizer`: Generates structured JSON summaries.  

2. **O – Open/Closed Principle**  
   The framework is open for extension but closed for modification.  
   - You can add new **External Interactions** (e.g., REST, DDB, SQS) without touching the core.  

3. **L – Liskov Substitution Principle**  
   Common interfaces like `TraceRecorder` allow multiple implementations without breaking flow.  

4. **I – Interface Segregation Principle**  
   Lightweight, purpose-specific interfaces — no single class is overloaded with responsibilities.  

5. **D – Dependency Inversion Principle**  
   The framework depends on abstractions, not implementations — making it **framework-agnostic** and **testable**.  

---

## 🧩 Design Patterns Used  

- **Aspect-Oriented Programming (AOP)** → Intercept method calls dynamically  
- **Factory Pattern** → Create trace objects cleanly  
- **Builder Pattern** → Construct the final summary JSON  
- **Strategy Pattern** → Handle different external call types (REST, DDB, Lambda)  
- **Observer Pattern (optional extension)** → Push metrics to tools like CloudWatch  

---

## 📊 What It Does  

The **Observability Framework** provides:  

- **End-to-end request tracing** — tracks the full journey of an API call  
- **Execution timing** for internal and external calls  
- **Structured JSON summary** for quick analysis  
- **Plug-and-play integration** — add one annotation or dependency, and it works automatically  

### 🧾 Sample Summary Output  

```json
{
  "traceId": "b3c7d2a1-ff32-4f2d-b9fa-8c9a9b1edc01",
  "api": { "path": "/v1/playback/segments/{segmentId}", "methodType": "GET" },
  "totalDurationMs": 1670,
  "tracePath": [
    {"class": "controller", "method": "getData", "type": "INTERNAL"},
    {"class": "DataBaseClient", "method": "getData", "type": "EXTERNAL", "interaction": "REST", "durationMs": 540}
  ],
  "status": "SUCCESS"
}
