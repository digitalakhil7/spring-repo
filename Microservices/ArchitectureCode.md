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

## API-1
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
spring.application.name=FirstApi
server.port=8081

eureka.client.serviceUrl.defaultZone=http://localhost:8761/eureka
spring.boot.admin.client.url=http://localhost:1111
spring.cloud.compatibility-verifier.enabled=false
management.endpoints.web.exposure.include= *
```
