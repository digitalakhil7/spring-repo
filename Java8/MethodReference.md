## Static and Instance Method Reference
```java
interface MyInterface{
	void m1();
}

@SpringBootApplication
public class Application {
	
	public void m2() {
		System.out.println("M2 Method");
	}
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		
		// static method reference
//		MyInterface mi = Application::m2;
		
		// instance method reference
		MyInterface mi = new Application()::m2;
		
		mi.m1();
	}

}
```
