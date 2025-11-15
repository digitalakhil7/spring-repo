## JWT
Jwt consists of 3 parts
1. Header
2. Payload (Claims)
3. Signature

### JWT Flow
<img width="600" height="401" alt="JWT Flow" src="https://github.com/user-attachments/assets/343b267e-5a9d-4d6f-96fa-6667a5ea4b77" />

for token generation and validation we need secretKey

## Code
### maven dependencies
```xml
		<dependency>
			<groupId>io.jsonwebtoken</groupId>
			<artifactId>jjwt-api</artifactId>
			<version>0.11.5</version>
		</dependency>
		<dependency>
			<groupId>io.jsonwebtoken</groupId>
			<artifactId>jjwt-impl</artifactId>
			<version>0.11.5</version>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>io.jsonwebtoken</groupId>
			<artifactId>jjwt-jackson</artifactId>
			<version>0.11.5</version>
			<scope>runtime</scope>
		</dependency>
```
### Token Generation and Reading
```java
public class JwtTest {

	public static void main(String[] args) {
		
		// 1. Generating Token using secretKey
		String token = Jwts.builder()
		.setId("1")
		.setSubject("Akhil")
		.setIssuer("companyName")
		.setIssuedAt(new Date(System.currentTimeMillis()))
		.setExpiration(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(10)))
		.signWith(Keys.hmacShaKeyFor("alongandbigsecreykeytotestjwttokens".getBytes()))
		.compact();
		
		System.out.println(token);
		
		// 2. Reading / Parse token using secretKey and token
		Claims c = Jwts.parserBuilder()
		.setSigningKey(Keys.hmacShaKeyFor("alongandbigsecreykeytotestjwttokens".getBytes())).build()
		.parseClaimsJws(token)
		.getBody();
		
		System.out.println(c);
		
		// 3. validate token: exp(date) > current(date)
		boolean isValid = c.getExpiration().after(new Date(System.currentTimeMillis()));
		System.out.println(isValid);
	}
}
```
