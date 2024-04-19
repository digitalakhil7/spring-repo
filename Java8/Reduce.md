## Reduce
Combine multiple values into a single value
```java
		int[] nums = new int[] {10,20,30,40};
		int sum1 = Arrays.stream(nums).reduce(0, (a,b)->a+b);
		// (OR)
		List<Integer> l = Arrays.asList(10,20,30,40);
		Integer sum2 = l.stream().reduce(0, (a,b)->a+b);
```
## Nashorn Engine
execute javascript code using JVM
### one.js
```js
var myFun = function(){
	print("JavaScript Test");
}

myFun();
```
### console
```cmd
jjs one.js
```
