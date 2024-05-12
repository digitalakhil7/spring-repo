JPA - sepcification, makes applications loosely coupled with ORM tools <br>
Hibernate - implementaion of JPA

## Features of Hibernate
**1. HQL (Hibernate Query Language) -** database independent
```sql
select * from emp; (SQL)
select e from Employee e; (HQL)

select empno,ename from emp; (SQL)
select e.empNo,e.empName from Employee e; (HQL)
```

**2. Caching -** to reduce no of db calls (first searches in cache then db) <br>
**3. Lazy Loading -** loading object only when required <br>
**4. Connection Pooling -** Pool of reusable connections <br>
**5. Criteria -** creates tuned queries <br>
**6. Locking -** only 1 transaction can modify data at once <br>

**7. Associations (MAIN CONCEPT OF HIBERNATE)***** <br>
One to One, One to Many, Many to One, Many to Many <br>
1 Person - 1 Passport <br>
1 Customer - (Many) Orders <br>
(Many) Products - 1 Category <br>
## Files in Hibernate with JPA
1. Entity class
2. persistence.xml (src/main/resouces/META-INF/persistence.xml)
3. Test class
### Objects in JPA
1. **EntityManagerFactory** object - has configuration data
2. **EntityManager** object - establish connection with DB
3. **EntityTransaction** - for insert, update, delete queries - Transaction Operations
4. CRUD
5. commit(), rollback() Transaction
6. close emf, em

## Files in Hibernate without JPA
1. Entity class
2. Configuration file (hibernate.cfg.xml in src/main/resources)
3. Mapping file (hbm.xml in src/main/resources) - Jpa Annotations are used in real time
4. Test class

### Mapping file - link class with table and variable with columns
employee.hbm.xml

```xml
<hibernate-mapping>
	<class name="com.example.Employee" table="emp_tab">
		<id name="empNo" column="emp_id"/> // column is optional if (name=column)
		<property name="empName" column="emp_name"/>
		<property name="empSal" column="emp_sal"/>
	</class>
</hibernate-mapping>
```
### Configuration file (hibernate.cfg.xml)
 configure - driverClassName, url, userName, password

dialect - prepares SQL queries wrt to db
```xml
<hibernate-configuration>
	<session-factory>
		<property name="hibernate.connection.driver_class>
			com.mysql.cj.jdbc.Driver
		</property>
     		<property name = "hibernate.connection.url">
        		jdbc:mysql://localhost:3306/test
	      	</property>
     		<property name = "hibernate.connection.username">
        		root
	      	</property>
     		<property name = "hibernate.connection.password">
        		akhilakhil
	      	</property>
		
     		<property name = "hibernate.dialect">
        		org.hibernate.dialect.MySQLDialect
	      	</property>
     		<property name = "hibernate.show_sql">
        		true
	      	</property>
     		<property name = "hibernate.hbm2ddl.auto">
        		create
	      	</property>		

		<mapping file = "employee.hbm.xml"/>
	</session-factory>
</hibernate-configuration>
```

### Test class
1. **SessionFactory** object - has configuration data
2. **Session** object - establish connection with DB
3. **Trasaction** - for insert, update, delete queries - Transaction Operations
4. CRUD
5. commit(), rollback() Transaction
6. close session, sessionFactory
