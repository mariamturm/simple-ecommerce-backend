Simple E-commerce REST API (Spring Boot)

A minimal E-commerce REST API built with Java 17 and Spring Boot, created to practice backend development, REST design, services, controllers, custom exceptions, and Swagger documentation.

The application runs in-memory (no database) and supports two logical users:

- admin - can create, update, delete products  
- user - can view products, manage shopping cart, and place orders  

A budget system (1000 GEL) and stock validation ensure realistic order processing.

---

Features

User Roles
- Admin
  - Create new products  
  - Update product details  
  - Delete products  

- User
  - View all products  
  - Add/remove items from shopping cart  
  - View shopping cart  
  - Place orders  
  - Budget decreases with purchases  

---

Core Business Rules

- User starts with 1000 GEL budget  
- When placing an order:
  - Must have enough budget  
  - Products must have enough stock  
- Each API request must include a `username` query parameter (`admin` or `user`)


API Documentation (Swagger UI)

This project uses springdoc-openapi for interactive API documentation.

After starting the application, open:
http://localhost:8080/swagger-ui/index.html

You will be able to test all endpoints directly from Swagger.


Technologies Used

- **Java 17**
- **Spring Boot 4**
- **Spring Web (REST)**
- **springdoc-openapi Swagger UI**
- **In-memory data storage (Maps, Lists)**
- **Custom Exceptions**



How to Run the Project

1. Clone the repository

2. Run `EcommerceApplication` in IntelliJ IDEA.

3. Open Swagger UI  
http://localhost:8080/swagger-ui/index.html



Example API Endpoints

Products (admin + user)
| Method | Endpoint                | Description |

| GET    | `/products`             | Get all products |
| GET    | `/products/{id}`        | Get product by ID |
| POST   | `/products`             | **Admin:** add product |
| PUT    | `/products/{id}`        | **Admin:** update |
| DELETE | `/products/{id}`        | **Admin:** delete |

Example:  
`GET /products?username=user`

---

Shopping Cart (user only)
| Method | Endpoint                | Description |
|--------|--------------------------|-------------|
| POST   | `/cart/add`             | Add item to cart |
| POST   | `/cart/remove`          | Remove item |
| GET    | `/cart`                 | View cart |
| GET    | `/cart/total`           | Get total price |

Example:  
`POST /cart/add?username=user&productId=1&quantity=2`

---

Orders (user only)
| Method | Endpoint | Description |
|--------|-----------|-------------|
| POST   | `/orders` | Place order |
| GET    | `/orders` | View order history |

Example:  
`POST /orders?username=user`


Author
Mariam Turmanidze
