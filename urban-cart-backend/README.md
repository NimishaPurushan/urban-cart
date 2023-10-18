# Configure your environment before use:
```
export POSTGRES_DB=your_database_name
export POSTGRES_USER=your_username
export POSTGRES_PASSWORD=your_password
export POSTGRES_JDBC_URL=jdbc:postgresql://localhost:5432/${POSTGRES_DB}
```

### Development Commands

| Command                     | Description                 |
| --------------------------- | --------------------------- |
| `$ ./gradlew bootRun`       | Run the development server. |
| `$ ./gradlew test`          | Run unit tests.             |
| `$ ./gradlew spotlessApply` | Apply code formatting.      |
| `$ ./gradlew check`         | Test code coverage          |

If you want to see code coverage when test fails then run:
```bash
$ cd build/jacocoHtml/; python3 -m http.server
```

You can see report at http://localhost:8000/com.example.urbancart/
___
