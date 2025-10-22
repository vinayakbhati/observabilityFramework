
# Observability Framework Example

This repository demonstrates a **Spring Boot Observability Framework** that automatically traces method calls and logs request flow with total duration.

## 📘 Project Structure

```
observability-example/
├── observability-framework/   # Core Observability library
└── observability-demo-service/ # Demo Spring Boot microservice using it
```

## 🚀 How It Works

- The **Observability Filter** starts a new trace for each incoming request.
- The **AOP Aspect** intercepts all method calls inside `com.example.demo` package.
- The trace path and execution duration are logged automatically in structured format.

### Example Output

```
OBSERVABILITY SUMMARY -> [API Call: GET /api/hello, INVOKE: DemoController.sayHello(), EXIT: DemoController.sayHello(), Total Duration: 205 ms]
```

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

## ✅ Key Benefits

- Understand **end-to-end flow** of requests.
- Identify **latency hotspots** easily.
- Works **without modifying business logic**.
- Easily extendable for AWS metrics, tracing tools (e.g., X-Ray, CloudWatch).

