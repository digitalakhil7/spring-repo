## Creating Stream Object
1. Stream.of( )
2. steam( )
```java
		// Approach-1
		Stream<Integer> s1 = Stream.of(10,20,30);
		s1.forEach(System.out::println);
		
		// Approach-2
		List<Integer> l = Arrays.asList(1,2,3);
		Stream s2 = l.stream();
		s2.forEach(System.out::println);
```
