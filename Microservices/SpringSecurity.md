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
default Security Configurations are present in `SpringBootWebSecurityConfiguration` class in `defaultSecurityFilterChain` method<br>
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
```java
@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> requests.requestMatchers("/private","/hidden")
                .authenticated().requestMatchers("/contact","/about").permitAll());
        http.formLogin(withDefaults());
        http.httpBasic(withDefaults());
        return http.build();
    }
}
```
## InMemoryUserDetailsManager
```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> requests.requestMatchers("/myAccount","/myLoans","/myCards","/myBalance")
                .authenticated().requestMatchers("/contact","/notices","/error").permitAll());
        http.formLogin(withDefaults());
        http.httpBasic(withDefaults());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withUsername("user").password("{noop}akhil@123*").authorities("read").build();
        UserDetails admin = User.withUsername("admin")
                .password("{bcrypt}$2a$12$8odqxGxwVQEqbhlImVHEQODv2ZBJRpOYm23JrnNU7Z3FhxH0eiTdC")
                .authorities("admin").build();
        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    /**
     * From Spring Security 6.3 version
     * @return
     */
    @Bean
    public CompromisedPasswordChecker compromisedPasswordChecker() {
        return new HaveIBeenPwnedRestApiPasswordChecker();
    }
}
```
## JdbcUserDetailsManager
Dependencies: Web, DevTools, Security, Jpa, MySql, Jdbc
### application.properties
```properties
spring.application.name=spring2

logging.level.org.springframework.security=TRACE

logging.pattern.console = ${LOGPATTERN_CONSOLE:%green(%d{HH:mm:ss.SSS}) %blue(%-5level) %red([%thread]) %yellow(%logger{15}) - %msg%n}

spring.datasource.url=jdbc:mysql://${DATABASE_HOST:localhost}:${DATABASE_PORT:3306}/${DATABASE_NAME:eazybank}
spring.datasource.username=${DATABASE_USERNAME:root}
spring.datasource.password=${DATABASE_PASSWORD:akhil}
spring.jpa.show-sql=${JPA_SHOW_SQL:true}
spring.jpa.properties.hibernate.format_sql=${HIBERNATE_FORMAT_SQL:true}
```
### SQL
```sql
use eazybank;
create table users(username varchar(50) not null primary key,password varchar(500) not null,enabled boolean not null);
create table authorities (username varchar(50) not null,authority varchar(50) not null,constraint fk_authorities_users foreign key(username) references users(username));
create unique index ix_auth_username on authorities (username,authority);

INSERT IGNORE INTO `users` VALUES ('user', '{noop}akhil@123*', '1');
INSERT IGNORE INTO `authorities` VALUES ('user', 'read');

INSERT IGNORE INTO `users` VALUES ('admin', '{bcrypt}$2a$12$8odqxGxwVQEqbhlImVHEQODv2ZBJRpOYm23JrnNU7Z3FhxH0eiTdC', '1');
INSERT IGNORE INTO `authorities` VALUES ('admin', 'admin');

## Custom Table ##
CREATE TABLE `customer` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(45) NOT NULL,
  `pwd` varchar(200) NOT NULL,
  `role` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
);

INSERT  INTO `customer` (`email`, `pwd`, `role`) VALUES ('happy@example.com', '{noop}akhil@123*', 'read');
INSERT  INTO `customer` (`email`, `pwd`, `role`) VALUES ('admin@example.com', '{bcrypt}$2a$12$8odqxGxwVQEqbhlImVHEQODv2ZBJRpOYm23JrnNU7Z3FhxH0eiTdC', 'admin');
```
### Java
```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> requests.requestMatchers("/myAccount","/myLoans","/myCards","/myBalance")
                .authenticated().requestMatchers("/contact","/notices","/error").permitAll());
        http.formLogin(withDefaults());
        http.httpBasic(withDefaults());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    /**
     * From Spring Security 6.3 version
     * @return
     */
    @Bean
    public CompromisedPasswordChecker compromisedPasswordChecker() {
        return new HaveIBeenPwnedRestApiPasswordChecker();
    }
}
```
## Custom Table
** Comment UserDetailsService in SecurityConfig
### Model
```java
@Entity
@Getter @Setter
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String pwd;
    private String role;
}
```
### Repo
```java
@Repository
public interface CustomerRepo extends CrudRepository<Customer, Long> {

    Optional<Customer> findByEmail(String email);

}
```
### CustomerUserDetailsService 
```java
@Service
@RequiredArgsConstructor
public class CustomerUserDetailsService implements UserDetailsService {

    private final CustomerRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByEmail(username).orElseThrow(() -> new
                UsernameNotFoundException("User details not found for the user: " + username));
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(customer.getRole()));
        return new User(customer.getEmail(), customer.getPwd(), authorities);
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
