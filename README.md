# 🛒 Product Catalog Application

A Spring Boot based RESTful backend application for managing a product catalog, secured with JWT authentication.

---

## 🚀 Features

- 🔐 JWT-based authentication (Login & Registration)
- 🛍️ Add, update, delete products
- 📂 Add, update, delete product categories
- 🧾 Clean architecture using DTOs and Mappers


---

## 🛠 Tech Stack

- Java 17+
- Spring Boot
- Spring Data JPA (Hibernate)
- Spring Security with JWT
- MySQL
- Maven
- Postman (for API testing)

---

## ⚙️ How to Run

### 1. Clone the Repository

```bash
git clone https://github.com/Dishikamewada-01/Product-catlog.git
cd Product-catlog
```

### 2. Open in IDE
```
Import the project as a Maven Project in Eclipse or IntelliJ IDEA.
Wait until all dependencies are downloaded by the IDE.
```

### 3. Configure Database
Update the following in your application.properties file:
```
spring.datasource.url=jdbc:mysql://localhost:3306/product_db
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 4. Run the Application
```
Run the main class ProductCatalogApplication.java
Your Spring Boot app will be available at: http://localhost:8080
```

### 5. API Endpoints
```
GET  /api/products     - Get all products
POST /api/products     - Add a new product
GET  /api/categories   - Get all categories
POST /api/categories   - Add a new category
```

### 6. Postman Testing

All APIs have been tested using **Postman** for proper request/response validation.

### 🔑 Authentication

**Login**

```
POST /api/auth/login
Content-Type: application/json

{
"username": "john",
"password": "1234"
}
Response:
{
"token": "eyJhbGciOiJIUzI1NiIsInR..."
}
```


**Register**
```
POST /api/auth/register
Content-Type: application/json

{
"username": "john",
"password": "1234",
}
```

### 📦 Product APIs

**Add Product**
```
POST /api/products
Headers:
Authorization: Bearer <JWT Token>
Content-Type: application/json

{
"name": "iPhone 14",
"price": 79999,
"categoryId": 2
}
```

**Get All Products**
```
GET /api/products
Headers:
Authorization: Bearer <JWT Token>
```

### 📂 Category APIs

**Add Category**
```
POST /api/categories
Headers:
Authorization: Bearer <JWT Token>
Content-Type: application/json

{
"name": "MakeUp"
}
```

**Get All Categories**
```
GET /api/categories
Headers:
Authorization: Bearer <JWT Token>
```
