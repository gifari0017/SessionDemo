# SessionDemo — Java Servlet Session Timeout Demo

A minimal Java Servlet project demonstrating HTTP session creation, attribute storage, and automatic session expiration after a configurable period of inactivity.

## What it does

- `/login` generates a random username and password, stores them in an `HttpSession`, and sets the session to expire after a set period of inactivity.
- `/check` reads back the session data if the session is still active, or reports that it has expired.

## Tech stack

- Java 17
- Java Servlet API 3.1 (`javax.servlet`)
- Apache Tomcat 7 (via the `tomcat7-maven-plugin`)
- Maven
## Project structure

SessionDemo/
├── pom.xml
├── src/main/java/com/example/
│ ├── LoginServlet.java # generates credentials, creates session
│ └── CheckServlet.java # verifies session is still active
└── src/main/webapp/WEB-INF/


## How session timeout works

`LoginServlet` calls:

```java
session.setMaxInactiveInterval(120); // seconds
```

This tells the servlet container (Tomcat) to destroy the session automatically after the given number of seconds pass with no request touching it. Any request against that session resets the idle timer. `CheckServlet` detects expiration by calling `req.getSession(false)`, which returns `null` if the session is gone.

## Running locally

1. Clone the repository:

git clone https://github.com/gifari0017/SessionDemo.git
cd SessionDemo

2. Run the embedded Tomcat server via Maven:

mvn tomcat7:run

3. Open a browser:
    - `http://localhost:8080/SessionDemo/login` — creates a session and shows the generated credentials.
    - `http://localhost:8080/SessionDemo/check` — confirms whether the session is still active.

## Notes

- Session timeout is currently set to 120 seconds in `LoginServlet.java` — adjust `setMaxInactiveInterval()` to change it.
- Each `/login` request invalidates any existing session before creating a new one, so a fresh session ID is issued every time.