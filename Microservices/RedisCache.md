### Installation on macOS
```
https://redis.io/docs/install/install-redis/install-redis-on-mac-os/
```
### Dependencies
Web, Lombok, DevTools, Redis, Jedis (from maven repo)
```xml
		<dependency>
			<groupId>redis.clients</groupId>
			<artifactId>jedis</artifactId>
		</dependency>
```

### Entity class should implement Serializable
```java
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Country implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String countryName;
	private String countryCode;
}
```

### RedisConfig
```java
@Configuration	
public class RedisConfig {
	
	@Bean
	public JedisConnectionFactory jedisConn() {
		JedisConnectionFactory jedis = new JedisConnectionFactory();
//		jedis.setHostName();
//		jedis.setPort();
		return jedis;
	}
	
	@Bean
	public RedisTemplate<String, Country> redisTemplate(){
		RedisTemplate<String, Country> rt = new RedisTemplate<>();
		rt.setConnectionFactory(jedisConn());
		return rt;
	}
}
```

### CountryRestController
```java
@RestController
public class CountryRestController {
		
	private HashOperations<String, Integer, Country> opsForHash = null;
	
	public CountryRestController(RedisTemplate<String, Country> redisTemplate) {
		this.opsForHash = redisTemplate.opsForHash();
	}
	
	@PostMapping("/country")
	public String saveCountry(@RequestBody Country country) {
		opsForHash.put("COUNTRIES", country.getId(), country);
		return "Country Added";
	}
	
	@GetMapping("/countries")
	public Collection<Country> getCountries() {
		Map<Integer, Country> entries = opsForHash.entries("COUNTRIES");
		Collection<Country> values = entries.values();
		return values;
	}
}
```
