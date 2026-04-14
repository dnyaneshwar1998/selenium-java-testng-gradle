# Selenium Java TestNG Gradle Project

This project is a UI automation starter template using:
- Selenium WebDriver
- TestNG
- Gradle
- WebDriverManager
- AssertJ

## Project structure

```
src
├── main
│   └── resources
│       └── config.properties
└── test
    ├── java
    │   └── com/example
    │       ├── framework
    │       │   ├── config
    │       │   ├── driver
    │       │   └── pages
    │       └── tests
    └── resources
        └── testng.xml
```

## Best practices included

- Thread-safe `WebDriver` handling using `ThreadLocal`
- Centralized config management with system property overrides
- Page Object Model foundation (`BasePage` + page classes)
- Reusable test lifecycle hooks (`BaseTest`)
- Test suite control from `testng.xml`
- Clear dependency and Java toolchain pinning in Gradle

## Prerequisites

- JDK 17+
- Gradle installed (or generate wrapper once Gradle is installed)

## Run tests

From project root:

```bash
gradle clean test
```

With runtime overrides:

```bash
gradle test -Dbrowser=firefox -Dheadless=true -Dbase.url=https://www.selenium.dev
```

## Optional: generate Gradle wrapper

```bash
gradle wrapper
./gradlew test
```
