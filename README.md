# frameworkDemo2025

## Simple Java Selenium Maven TestNG Project

A minimal and straightforward example of a Java-based Selenium automation testing project using Maven and TestNG.

### Project Structure

```
frameworkDemo2025/
├── src/
│   ├── main/
│   │   ├── java/          # Main source code
│   │   └── resources/     # Main resources
│   └── test/
│       ├── java/
│       │   └── com/selenium/
│       │       └── GoogleSearchTest.java  # Sample test case
│       └── resources/     # Test resources
├── pom.xml               # Maven configuration
├── testng.xml           # TestNG test suite configuration
└── README.md            # This file
```

### Technologies Used

- **Java 11** - Programming language
- **Maven 3.6+** - Build tool
- **Selenium WebDriver 4.15.0** - Web automation library
- **TestNG 7.8.1** - Test framework
- **ChromeDriver** - WebDriver for Chrome browser

### Prerequisites

- Java JDK 11 or higher installed
- Maven 3.6 or higher installed
- Google Chrome browser installed
- ChromeDriver compatible with your Chrome version

### Setup Instructions

1. **Clone the repository:**
   ```bash
   git clone https://github.com/somnathsarak/frameworkDemo2025.git
   cd frameworkDemo2025
   ```

2. **Download ChromeDriver:**
   - Download from: https://chromedriver.chromium.org/
   - Update the path in GoogleSearchTest.java: `/path/to/chromedriver`

3. **Install Maven dependencies:**
   ```bash
   mvn clean install
   ```

4. **Run tests:**
   ```bash
   mvn test
   ```

### Test Cases

**GoogleSearchTest.java:**
- Opens Google.com
- Verifies page title
- Searches for "Selenium WebDriver"
- Verifies search results page

### Key Features

✅ Simple and clean project structure  
✅ Maven-based build tool  
✅ TestNG annotations (@BeforeClass, @Test, @AfterClass)  
✅ Page assertions and validations  
✅ Exception handling  
✅ Resource cleanup in tearDown  

### Running Tests with TestNG XML

You can also run tests using the testng.xml configuration:

```bash
mvn clean test -DsuiteXmlFile=testng.xml
```

### Extending the Project

To add more tests:

1. Create new test classes in `src/test/java/com/selenium/`
2. Use TestNG annotations (@Test, @BeforeClass, @AfterClass)
3. Follow the same pattern as GoogleSearchTest.java
4. Run tests with Maven

### Notes

- Update ChromeDriver path based on your system
- Use explicit waits for better test reliability
- Consider adding Page Object Model for larger projects

### Author

Somnath Sarak

### Repository

https://github.com/somnathsarak/frameworkDemo2025
