## Eureka Server
**Dependencies:** DevTools, Eureka Server<br>
**Start Class:** @EnableEurekaServer
```java
@SpringBootApplication
@EnableEurekaServer
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
```
**application.properties**
```properties
server.port=8761
eureka.client.register-with-eureka=false
```

## Admin Server
**Dependencies:** DevTools, Admin Server<br>
**Start Class:** @EnableAdminServer<br>
**application.properties**
```properties
server.port=1111
```

## API-1 (GREET-API)
**Dependencies:** Web, DevTools, Actuator<br>
Eureka Client, Admin Client, Zipkin, Sleuth<br>
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-sleuth</artifactId>
    <version>3.1.11</version>
</dependency>
```
**Start Class:** @EnableDiscoveryClient<br>
**application.properties**
```properties
spring.application.name=GREET-API
server.port=8081

eureka.client.serviceUrl.defaultZone=http://localhost:8761/eureka
spring.boot.admin.client.url=http://localhost:1111
management.endpoints.web.exposure.include=*
#spring.cloud.compatibility-verifier.enabled=false
```
### GreetController
```java
@RestController
public class GreetController {
	
	@GetMapping("/greet")
	public String sendGreet() {
		return "Good Evening";
	}
}
```
## API-2 (WELCOME-API)
**Dependencies:** Web, DevTools, Actuator<br>
Eureka Client, Admin Client, Zipkin, Sleuth, OpenFeign<br>
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-sleuth</artifactId>
    <version>3.1.11</version>
</dependency>
```
**Start Class:** @EnableDiscoveryClient and @EnableFeignClients<br>
**application.properties**
```properties
spring.application.name=WELCOME-API
server.port=9091

eureka.client.serviceUrl.defaultZone=http://localhost:8761/eureka
spring.boot.admin.client.url=http://localhost:1111
management.endpoints.web.exposure.include=*
#spring.cloud.compatibility-verifier.enabled=false
```
### GreetFeignClient
```java
@FeignClient(name = "GREET-API")
public interface GreetFeignClient {
	@GetMapping("/greet")
	public String getMsg();
}

```
### WelcomeController
```java
@RestController
public class WelcomeController {
	
	@Autowired
	private GreetFeignClient feign;
	
	@GetMapping("/welcome")
	public String sendWelcome() {
		
		String resp = feign.getMsg();
		return resp + ", Welcome";
	}
}
```
