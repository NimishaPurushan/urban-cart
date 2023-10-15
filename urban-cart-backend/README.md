# Running Code using CLI
```bash
$ ./gradlew bootRun
```
___
# Running unittest using CLI
```bash
$ ./gradlew test
```
___
# Formatting using CLI:
Add following plugin to `build.gradle`:
```gradle
plugin {
    ...
    id 'com.diffplug.spotless' version '6.22.0'
}

spotless {
    java {
        googleJavaFormat()
    }
}
```
For formatting using Cli:
```bash
$ ./gradlew spotlessApply
```
___
# Hot Reloading
Add following dependency in `build.gradle`:
```gradle
implementation group: 'org.springframework.boot', name: 'spring-boot-devtools'
```


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
