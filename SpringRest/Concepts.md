## Actuator
To manage and monitor applications <br>
**Dependencies:** Web, DevTools, Actuator <br>
**To Access Actuators:** localhost:8080/actuator <br>
By default only 1 actuator **/health** is exposed, to expose all actuators use the below properties
```properties
server.port=9090
management.endpoints.web.exposure.include=*
```
## @RequestHeader
To send header data to controller <br>
Headers - Authorization, Cookies, Content-type, Accept
```java
	@PostMapping("/details")
	public ResponseEntity<String> showDetails(@RequestHeader("Content-Type") String type,
			@RequestHeader("Authorization") String auth,
			HttpServletRequest req){
		
		// Servlets
		Enumeration<String> headerNames = req.getHeaderNames();
		while(headerNames.hasMoreElements()) {
			String nextElement = headerNames.nextElement();
			System.out.println(nextElement);
		}
		
		return new ResponseEntity<String>("CHECK: "+auth, HttpStatus.OK);
	}
```
### Send Header data from Controller
```java
	@GetMapping("/data")
	public ResponseEntity<String> getData(){
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("MyHeader", "MyHeaderValue");
		
		return new ResponseEntity<>("Body",headers, HttpStatus.OK);
	}
```
## Dynamic return type <?>
```java
	@GetMapping("/test")
	public ResponseEntity<?> getTestData(){
		if(false) {
			return new ResponseEntity<String>("STRING", HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Integer>(10, HttpStatus.OK);
		}
	}
```
## XML
```xml
		<dependency>
			<groupId>com.fasterxml.jackson.dataformat</groupId>
			<artifactId>jackson-dataformat-xml</artifactId>
		</dependency>
```
```text
#Header
Accept: application/xml        (Receive data)
Content-type: application/xml  (Send data)
```
@JsonIgnore - ignore variable <br>
@JsonProperty - alias name to variable
```java
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	@JsonProperty("uid")
	private Integer id;
	@JsonProperty("uname")
	private String name;
	private String role;
	@JsonIgnore
	private String password;
}
```
