## StringJoiner
```java
		StringJoiner sj1 = new StringJoiner("-");
		sj1.add("Akhil");
		sj1.add("Ved");
		System.out.println(sj1); // Akhil-Ved
		
		StringJoiner sj2 = new StringJoiner("-", "(", ")");
		sj2.add("Akhil");
		sj2.add("Ved");
		sj2.add("Dev");
		System.out.println(sj2); // (Akhil-Ved-Dev)
```

## Optional
### User class
```java
public class User {
	public Optional<String> getUserNameById(Integer id) {
		
		String name = null;
		
		if(id==100)
			name = "Akhil";
		else if(id==200)
			name = "Nikhil";
		else if(id==300)
			name = "Anand";
		
		return Optional.ofNullable(name);
	}
}
```
### Main class
```java
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		
		System.out.print("Enter User Id: ");
		Scanner sc = new Scanner(System.in);
		int id = sc.nextInt();
		
		User u = new User();
		
		Optional<String> opt = u.getUserNameById(id);
		if(opt.isPresent()) {
			String name = opt.get();
			System.out.println(name);
		}
		else
			System.out.println("Invalid Id");
		
	}

}
```
