**Functionalities:** Book Ticket, Get Ticket<br>
**Input:** Passenger obj<br>
**Output:** Ticket obj<br>
**consumes/produces:** application/json

**Dependencies:** Web, Lombok, DevTools, Swagger (https://www.baeldung.com/spring-rest-openapi-documentation)<br>
```
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.2.0</version>
</dependency>
```
**Packaging:** Jar <br>
**Accessing Swagger UI:** URL:8080/swagger-ui.html<br>
**Accessing Swagger Doc:*** URL:8080/v3/api-docs<br>
**Cloud Deployment:** java -jar IRCTCApp.jar<br>
### Passenger class
```java
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Passenger {
	private String name;
	private String from;
	private String to;
	private String doj;
	private String trainNumber;
}
```

### Ticket class
```java
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
	private Integer ticketNum;
	
	private String name;
	private String from;
	private String to;
	private String doj;
	private String trainNumber;
	
	private String status;
	private Double ticketCost;
}
```

### BookingService (I)
```java
public interface BookingService {
	public Ticket bookTicket(Passenger p);
	
	public Ticket getTicketDetails(Integer ticketNum);
}
```
### BookingServiceImpl
```java
@Service
public class BookingServiceImpl implements BookingService {
	
	private Map<Integer,Ticket> ticketMap = new HashMap<>();
	private Integer ticketNum = 1;
	
	@Override
	public Ticket bookTicket(Passenger p) {
		
		Ticket t = new Ticket();
		BeanUtils.copyProperties(p, t);
		t.setStatus("Confirmed");
		t.setTicketCost(399.00);
		t.setTicketNum(ticketNum);
		ticketMap.put(ticketNum, t);
		ticketNum++;
		return t;
	}

	@Override
	public Ticket getTicketDetails(Integer ticketNum) {
		if(ticketMap.containsKey(ticketNum)) {
			return ticketMap.get(ticketNum);
		}
		return null;
	}

}
```

### BookingController
```java
@RestController
public class BookingController {
	
	@Autowired
	private BookingService service;
	
	@PostMapping(value="/bookTicket", consumes= {"application/json"}
	,produces= {"application/json"})
	public ResponseEntity<Ticket> bookTicket(@RequestBody Passenger p){
		Ticket t = service.bookTicket(p);
		return new ResponseEntity<Ticket>(t,HttpStatus.CREATED);
	}
	
	
	@GetMapping(value="/getTicketDetails/{ticketNum}", produces="application/json")
	public Ticket getOneTicket(@PathVariable Integer ticketNum) {
		return service.getTicketDetails(ticketNum);
	}
	
}
```
