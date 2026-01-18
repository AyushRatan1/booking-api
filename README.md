# API Test Framework

A REST API test automation framework using Java 17, REST Assured, TestNG, and Hamcrest.

## 📋 Tech Stack

| Component | Library |
|-----------|---------|
| Language | Java 17 |
| Build Tool | Maven |
| API Client | REST Assured 5.4.0 |
| Test Framework | TestNG 7.9.0 |
| Assertions | Hamcrest 2.2 |
| JSON | Jackson 2.16.1 |

## 🚀 Setup on a New System (IntelliJ IDEA)

### Prerequisites
- **Java 17** installed (check with `java -version`)
- **Maven** installed (check with `mvn -version`)
- **IntelliJ IDEA** (Community or Ultimate)

### Step-by-Step Setup

1. **Clone the repository**
   ```bash
   git clone https://github.com/AyushRatan1/booking-api.git
   cd booking-api
   ```

2. **Open in IntelliJ IDEA**
   - Open IntelliJ IDEA
   - Click **File → Open**
   - Navigate to the `booking-api` folder and click **Open**
   - If prompted, click **Trust Project**

3. **Import Maven Project**
   - IntelliJ should automatically detect `pom.xml` and start importing
   - If not, right-click on `pom.xml` → **Add as Maven Project**
   - Wait for Maven to download all dependencies (check the progress bar at bottom)

4. **Set Project SDK**
   - Go to **File → Project Structure → Project**
   - Set SDK to **Java 17**
   - Set Language Level to **17**
   - Click **Apply → OK**

5. **Reload Maven** (if dependencies show errors)
   - Open the Maven tool window (View → Tool Windows → Maven)
   - Click the **Reload** button (🔄) at the top

## ▶️ Running Tests

### From IntelliJ IDEA
- **Run all tests**: Right-click on `testng.xml` → **Run 'testng.xml'**
- **Run single test class**: Right-click on test file → **Run 'ClassName'**
- **Run single test method**: Click green arrow next to test method

### From Command Line
```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=PostApiTest

# Run specific test method
mvn test -Dtest=UserApiTest#testGetUser

# Clean and run
mvn clean test
```

## 📁 Project Structure

```
booking-api/
├── pom.xml                              # Maven configuration
├── testng.xml                           # TestNG suite configuration
│
└── src/
    ├── main/java/com/booker/api/
    │   ├── client/
    │   │   ├── PostClient.java          # Posts API (4 operations)
    │   │   └── UserClient.java          # Users API (4 operations)
    │   └── models/
    │       ├── Post.java                # Simple POJO with Builder
    │       ├── User.java                # Complex POJO with nested objects
    │       ├── Address.java             # Nested in User
    │       ├── Geo.java                 # Nested in Address
    │       └── Company.java             # Nested in User
    │
    └── test/
        ├── java/com/booker/api/tests/
        │   ├── PostApiTest.java         # 4 tests (GET, POST, PUT, DELETE)
        │   └── UserApiTest.java         # 4 tests with nested objects
        └── resources/
            └── config.properties        # API configuration
```

## 🧪 Test Summary

| Test Class | Tests | API Endpoint |
|------------|-------|--------------|
| PostApiTest | 4 | `/posts` |
| UserApiTest | 4 | `/users` |
| **Total** | **8** | |

## 🌐 API Under Test

This framework tests the [JSONPlaceholder API](https://jsonplaceholder.typicode.com):
- Free, stable, fake REST API for testing
- No authentication required
- Supports full CRUD operations

## 📝 License

MIT License
