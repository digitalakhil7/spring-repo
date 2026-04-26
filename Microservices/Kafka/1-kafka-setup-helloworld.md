## Setup
```cmd
https://kafka.apache.org/community/downloads/
(download 3.9.2 scala version)

cd C:\kafka
bin\windows\kafka-storage.bat random-uuid
bin\windows\kafka-storage.bat format -t <your-cluster-id> -c config\kraft\server.properties
bin\windows\kafka-server-start.bat config\kraft\server.properties

(New Terminal)
bin\windows\kafka-topics.bat --create --topic test-topic --bootstrap-server localhost:9092
bin\windows\kafka-console-producer.bat --topic test-topic --bootstrap-server localhost:9092

(New Terminal)
bin\windows\kafka-console-consumer.bat --topic test-topic --from-beginning --bootstrap-server localhost:9092
```
## Concept
**Producer:** send data to topic <br>
**Consumer:** read data from topic
## Spring projects
### 1. user-service
Dependencies: web, devtools, kafka
```java
@RestController
public class UserController {
	
	RestTemplate rt = new RestTemplate();
	
	@Autowired
	KafkaTemplate<String, String> kt;
	
	@PostMapping("/createAccount/{userEmail}")
	public String createAccount(@PathVariable String userEmail) {
		
		// save user to db
		
		// send message to topic
		kt.send("new-user", userEmail);
		
		/*
		 * Map<String, String> map = new HashMap<>(); map.put("userEmail", userEmail);
		 * 
		 * // send email to user
		 * 
		 * @Nullable String resp =
		 * rt.getForObject("http://localhost:8082/email/{userEmail}", String.class,
		 * map); System.out.println(resp);
		 */
		
		return "User created: "+userEmail;
	}
}
```
### 2. email-service
```java
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@KafkaListener(topics = {"new-user"}, groupId = "email-service")
	public void sendEmail(String userName) {
		System.out.println("Email sent to: "+userName);
	}

}
```
