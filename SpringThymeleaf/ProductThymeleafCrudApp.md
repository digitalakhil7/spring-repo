## Home Page
![Screen Shot 2024-03-15 at 11 01 20 AM](https://github.com/digitalakhil7/spring-repo/assets/63640168/9debd18b-1e2e-4d4c-a918-b1621d9886e4)
## Products List Page
![Screen Shot 2024-03-15 at 11 00 33 AM](https://github.com/digitalakhil7/spring-repo/assets/63640168/1cd6989a-0975-4126-8926-9d9fff302749)
## Empty Products List Page
![Screen Shot 2024-03-15 at 11 00 54 AM](https://github.com/digitalakhil7/spring-repo/assets/63640168/77a63312-8ec9-4b9b-a0e4-b708aafc3ef4)
### Dependencies: Lombok, DevTools, Web, Thymeleaf, Jpa, MySql/H2, Validation <br>
### application.properties
```properties
#DataBaseConnection
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/akhil
spring.datasource.username=root
spring.datasource.password=akhilakhil

#ORMDetails
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
#spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
```

### Entity Class
```java
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message = "Name cannot be empty")
	@Size(message = "Name should be min 3 and max 15 chars", min=3, max=15)
	private String name;
	
	@NotNull(message = "Price cannot be null")
	@Positive(message = "Price should be greater than 0")
	private Double price;
	
	@NotNull(message = "Quantity cannot be null")
	@Positive(message = "Quantity should be greater than 0")
	private Integer quantity;
	
//	@Email - for email validation
}
```

### Repository Interface
```java
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

}
```

### Controller
```java
@Controller
public class MyController {
	
	@Autowired
	private ProductRepository repo;
	
	@GetMapping("/")
	public String showHome(Model model) {
		model.addAttribute("product", new Product());
		return "index";
	}
	
	@PostMapping("/saveProduct")
	public String saveProduct(Model model, @Validated @ModelAttribute("product") Product product,
			BindingResult result) {
		
		if(result.hasErrors()) {
			return "index";
		}
		
		product = repo.save(product);
		if(product.getId()!=null) {
			model.addAttribute("msg","Product Saved");
		}
		return "index";
	}
	
	@GetMapping("/showProducts")
	public String showProducts(Model model) {
		model.addAttribute("products", repo.findAll());
		return "showproducts";
	}
	
	@GetMapping("/deleteOneProduct")
	public String deleteOneProduct(@RequestParam Integer pid, Model model) {
		repo.deleteById(pid);
		model.addAttribute("msg","Product Deleted");
		model.addAttribute("products", repo.findAll());
		return "showproducts";
	}
	
	@GetMapping("/editOneProduct")
	public String editOneProduct(@RequestParam Integer pid, Model model) {
		Optional<Product> opt = repo.findById(pid);
		if(opt.isPresent()) {
			Product p = opt.get();
			model.addAttribute("product",p);
		}
		return "editproduct";
	}
	
	@PostMapping("/updateProduct")
	public String updateProduct(Model model,@Validated @ModelAttribute("product") Product product,
			BindingResult result) {
		
		if(result.hasErrors()) {
			return "editproduct";
		}
		
		product = repo.save(product);
		if(product.getId()!=null) {
			model.addAttribute("msg","Product Updated");
		}
		model.addAttribute("products", repo.findAll());
		return "showproducts";
	}
}
```

### index.html
```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <title>Document</title>
</head>
<body>
	<div class="container">
    <h1>Product Form</h1>
    <p th:text="${msg}" class="text-success"></p>
    <hr>
    <form th:action="@{/saveProduct}" th:object=${product} method="post">
        <table>
            <tr>
                <td>Name:</td>
                <td><input type="text" th:field="*{name}" id="name"></td>
                <td th:if="${#fields.hasErrors('name')}" th:errors="*{name}" class="text-danger"></td>
            </tr>
            <tr>
                <td>Price:</td>
                <td><input type="number" th:field="*{price}" id="price"></td>
                <td th:if="${#fields.hasErrors('price')}" th:errors="*{price}" class="text-danger"></td>
            </tr>
            <tr>
                <td>Quantity:</td>
                <td><input type="number" th:field="*{quantity}" id="quantity"></td>
                <td th:if="${#fields.hasErrors('quantity')}" th:errors="*{quantity}" class="text-danger"></td>
            </tr>
        </table><br>
        <input type="reset" value="Reset" class="btn btn-secondary">
        <input type="submit" value="Submit" class="btn btn-primary">
    </form><br>
    <a href="/showProducts">View All Products</a>
   </div>
</body>
</html>
```

### showproducts.html
```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <title>Document</title>
    <script type="text/javascript">
		function deleteConfirm(){
			return confirm("Do you want to delete this record?");
		}
	</script>
</head>
<body>
	<div class="container">
    <h1>View Product Info</h1>
    <p th:text="${msg}" class="text-danger"></p>
    <hr>
    <a href="/" class="btn btn-primary mb-4">(+) Add New Product</a>
    <table class="table table-bordered table-striped">
        <tr>
            <td>Product Id</td>
            <td>Product Name</td>
            <td>Product Price</td>
            <td>Product Quantity</td>
            <td>Action</td>
        </tr>
        <tr th:each="product : ${products}">
            <td th:text="${product.id}"></td>
            <td th:text="${product.name}"></td>
            <td th:text="${product.price}"></td>
            <td th:text="${product.quantity}"></td>
            <td><a th:href="@{/deleteOneProduct(pid=${product.id})}"  onclick="return deleteConfirm()"
            class="btn btn-danger">Delete</a> || 
            <a th:href="@{/editOneProduct(pid=${product.id})}" class="btn btn-warning">Edit</a>
            </td>
        </tr>
        <tr>
			<td th:if="${products == null or products.isEmpty()}" colspan="5" class="text-center">No Records Found</td>
		</tr>
    </table>
    </div>
</body>
</html>
```
### editproduct.html
```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <title>Document</title>
</head>
<body>
	<div class="container">
    <h1>Edit Product Form</h1>
    <p th:text="${msg}" class="text-success"></p>
    <hr>
    <form th:action="@{/updateProduct}" th:object=${product} method="post">
        <table>
			<tr>
                <td>Id:</td>
                <td><input type="text" th:field="*{id}" id="name" readonly></td>
            </tr>
            <tr>
                <td>Name:</td>
                <td><input type="text" th:field="*{name}" id="name"></td>
                <td th:if="${#fields.hasErrors('name')}" th:errors="*{name}" class="text-danger"></td>
            </tr>
            <tr>
                <td>Price:</td>
                <td><input type="number" th:field="*{price}" id="price"></td>
                <td th:if="${#fields.hasErrors('price')}" th:errors="*{price}" class="text-danger"></td>
            </tr>
            <tr>
                <td>Quantity:</td>
                <td><input type="number" th:field="*{quantity}" id="quantity"></td>
                <td th:if="${#fields.hasErrors('quantity')}" th:errors="*{quantity}" class="text-danger"></td>
            </tr>
        </table><br>
        <input type="reset" value="Reset" class="btn btn-secondary">
        <input type="submit" value="Submit" class="btn btn-primary">
    </form><br>
    <a href="/showProducts">View All Products</a>
   </div>
</body>
</html>
```
