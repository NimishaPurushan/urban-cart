# Configure your environment before use:
```
export POSTGRES_DB=your_database_name
export POSTGRES_USER=your_username
export POSTGRES_PASSWORD=your_password
export REDIS_PASSWORD=your_redis_password
export REDIS_DB=your_redis_db
export POSTGRES_URL=jdbc:postgresql://localhost:5432/${POSTGRES_DB}
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

For Hot Reloading, add following dependency in `build.gradle`:

| Terminal 1                            | Terminal 2                  | Checks unittest | Checks Formatting |
| ------------------------------------- | --------------------------- | --------------- | ----------------- |
| ```$ ./gradlew build --continuous ``` | ```$ ./gradlew bootRun  ``` | Yes             | Yes               |
| ```$ ./gradlew -t :bootJar  ```       | ```$ ./gradlew bootRun  ``` | No              | No                |

# Diffrence between `@RequestParam`  vs `@QueryParam` 

| Feature                 | `@RequestParam` (Spring)                                                                  | `@QueryParam` (JAX-RS)                                                                                                                           |
| ----------------------- | ----------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------ |
| **Framework**           | Spring MVC                                                                                | JAX-RS (Java API for RESTful Web Services)                                                                                                       |
| **Usage**               | Used in Spring MVC controllers to extract values from the request parameters.             | Used in JAX-RS resource classes and methods to extract values from the query parameters of the URI.                                              |
| **Example**             | ```java                                                                                   | ```java                                                                                                                                          |
|                         | @GetMapping("/example")                                                                   | @Path("/example")                                                                                                                                |
|                         | public String exampleEndpoint(@RequestParam String param) {                               | @GET                                                                                                                                             |
|                         | // Method implementation                                                                  | @Produces(MediaType.TEXT_PLAIN)                                                                                                                  |
|                         | }                                                                                         | public String exampleEndpoint(@QueryParam("param") String param) {                                                                               |
|                         |                                                                                           | // Method implementation                                                                                                                         |
| **Default Value**       | Supports specifying a default value if the parameter is not present in the request.       | Supports specifying a default value if the parameter is not present in the request.                                                              |
| **Required**            | Supports marking a parameter as required, and an exception is thrown if it's not present. | Does not have an explicit required attribute. If a query parameter is not present, the value will be `null` unless a default value is specified. |
| **Multiple Parameters** | Supports handling multiple parameters of the same name using arrays or `List`.            | Supports handling multiple parameters of the same name using arrays or `List`.                                                                   |
| **Binding to Objects**  | Supports binding request parameters to objects using `@ModelAttribute`.                   | Not typically used for binding to complex objects; JAX-RS often uses `@BeanParam` for this purpose.                                              |
| **Scope**               | Primarily used in Spring MVC for web applications.                                        | Primarily used in JAX-RS for building RESTful web services.                                                                                      |
---


# Diffrence between `@Autowired`: Constructor VS Variable
| Feature                     | `@Autowired` in Constructor                                  | `@Autowired` in Variable                                                |
| --------------------------- | ------------------------------------------------------------ | ----------------------------------------------------------------------- |
| **Injection Type**          | Constructor Injection                                        | Field Injection                                                         |
| **Example**                 | `@Autowired`                                                 | `@Autowired`                                                            |
|                             | `public MyClass(@Autowired Dependency dependency) {`         | `@Autowired private Dependency dependency;`                             |
| **When Injection Occurs**   | During object creation                                       | After object creation                                                   |
| **Control over Init**       | Better control; can make fields final for immutability       | Limited control; initialization occurs after object creation            |
| **Testability**             | Easier to unit test; dependencies are set at construction    | Can be harder to test; dependencies are set after object creation       |
| **Dependency Relationship** | Clear dependency relationship between class and dependencies | Dependencies are set directly on fields; might be less clear            |
| **Circular Dependencies**   | Less likely to cause issues with circular dependencies       | Can lead to issues with circular dependencies if not managed carefully  |
| **Best Practice**           | Often considered a best practice for required dependencies   | Used for optional dependencies or in scenarios where brevity is desired |
