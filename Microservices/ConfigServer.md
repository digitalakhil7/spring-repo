## Prerequisites
properites files with same name as application names **(OR)** application.properties (default)
```git
https://github.com/digitalakhil7/config-server-app
```

## Config Server
**Dependencies:** DevTools, Config Server<br>
**Start Class:** @EnableConfigServer<br>
**application.properties**
```properties
spring.cloud.config.server.git.uri=https://github.com/digitalakhil7/config-server-app.git
spring.cloud.config.server.git.clone-on-start=true
```
## Greet (Api)
**Dependencies:** DevTools, Config Client, Web<br>
**application.properties**
```properties
server.port=8081
spring.application.name=greet
spring.config.import=optional:configserver:http://localhost:8080/
```
### GreetRestController
```java
@RestController
public class GreetRestController {
	@Value("${msg}")
	private String msg;
	
	@GetMapping("/greet")
	public String greetMsg() {
		return msg;
	}
	
}
```
## Welcome (Api)
**Dependencies:** DevTools, Config Client, Web<br>
**application.properties**
```properties
server.port=8082
spring.application.name=welcome
spring.config.import=optional:configserver:http://localhost:8080/
```
### WelcomeRestController
```java
@RestController
public class WelcomeRestController {
	@Value("${msg}")
	private String msg;
	
	@GetMapping("/welcome")
	public String getWelcomeMsg() {
		return msg;
	}
	
}
```
