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
