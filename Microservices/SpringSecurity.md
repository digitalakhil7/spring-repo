## Spring Security
**Dependencies:** Web, DevTools, Security <br>
**Default Credentials:**
```
username: user
password: generated password from console
```
To override default credentials
```properties
spring.security.user.name=akhil
spring.security.user.password=akhil
```
By default SpringSecurity provides security to all the HttpMethods

## Secure Specific URLs
/about and /contact should be available to everyone, but /hidden and /private should be avaiable to only logged in users
### RestController
```java
@RestController
public class MyController {
	
	@GetMapping("/private")
	public String privatePage() {
		return "Private Page for Customers";
	}
	
	@GetMapping("/hidden")
	public String hiddenPage() {
		return "Secret Details";
	}
	
	@GetMapping("/contact")
	public String contactPage() {
		return "Contact us today";
	}
	
	@GetMapping("/about")
	public String aboutPage() {
		return "About Us";
	}
}
```
### SecurityConfigurer
```java
@Configuration
@EnableWebSecurity
public class SecurityConfigurer {
	
	@Bean
	public SecurityFilterChain securityFilter(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests((req)->
		req.requestMatchers("/contact","/about").permitAll()
		.anyRequest().authenticated()
		).formLogin();
		
		return http.build();
	}
}
```
## OAUTH
**Pre-requisites:** **clientId** and **clientSecret** from GitHub (Settings -> Developer Settings -> OAUTH Apps) <br>
**Dependencies:** Web, Devtools, Security, OAUTH
### application.properties
```properties
spring.security.oauth2.client.registration.github.client-id=Ov23liFqvFBYEG7r
spring.security.oauth2.client.registration.github.client-secret=5de6348f1ffb4df459e8762bfbecb
```
### RestController
```java
@RestController
public class MyController {
	
	@GetMapping("/")
	public String showHome() {
		return "Welcome to HomePage";
	}
}
```
