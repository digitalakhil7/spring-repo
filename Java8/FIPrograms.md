## Runnable
Program to print numbers from 1 to 5 using Thread with help of Runnable interface
```java
@SpringBootApplication
public class Application{

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		
		Runnable r = () -> {
			for (int i = 1; i <= 5; i++) {
				System.out.println(i);
			}
		};
		
		Thread t = new Thread(r);
		t.start();
		
		for (int i = 1; i <= 5; i++) {
			System.out.println(i+"hey");
		}
	}
}
```
## Comparator
Store Numbers in an ArrayList and sort numbers in descending order
```java
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		
		List<Integer> l  = Arrays.asList(5,3,7,2,9,6,1,4);
		System.out.println(l);
		Collections.sort(l, (num1,num2) -> num1 > num2 ? -1 : 1);
		System.out.println(l);
	}

}
```
