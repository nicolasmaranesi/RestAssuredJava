# RestAssuredJava

Automated API testing framework built with **Java** and **Rest Assured**.

This project is meant to:
- Make HTTP requests  (GET, POST, PUT, DELETE)
- validate status codes, headers y body
- Parse and validate JSON
---

## 📂 Project Structure

```text
RestAssuredJava/
├─ src
│  └─ test
│      └─ java
│          ├─ base/        
│          ├─ models/      
│          ├─ tests/       
│          └─ utils/       
├─ pom.xml                 
└─ README.md
```

---
## 🧪 Tech Stack

Java 17+ (pom.xml)

Maven

Rest Assured –  test APIs REST library for Java

GitHub

TestNG 

Hamcrest / AssertJ

ExtentReports for reports

---
## 🚀 Getting Started

1. Clone the repository

```bash
git clone https://github.com/nicolasmaranesi/RestAssuredJava.git
cd RestAssuredJava
```

2. Import it in your IDE


3. Install dependencies


```bash
mvn clean install
```
---

## 📘 Tests example

✅ GET: list all products


```java
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ProductsTest extends BaseTest {

    @Test
    public void shouldReturnAllProducts() {
        given()
                .spec(reqSpec)
                .when()
                .get("/products")
                .then()
                .statusCode(200)
                .body("$", not(empty()));
    }
}

```



---
## 📊 Reports

```bash
```bash
open src/report/API_08_11_2025 19_22_09.html
``````
---
## 👤 Author
Nicolás Carlos Maranesi

QA Automation Engineer / Test Architect

LinkedIn: nicolas-maranesi



