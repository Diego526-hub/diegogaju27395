# E-Commerce Product API - Test Commands

## Base URL
http://localhost:8080

## Test All Endpoints

### 1. Get All Products
curl -X GET http://localhost:8080/api/products

### 2. Get All Products with Pagination (page 0, limit 5)
curl -X GET "http://localhost:8080/api/products?page=0&limit=5"

### 3. Get Product by ID (Product ID: 1)
curl -X GET http://localhost:8080/api/products/1

### 4. Get Products by Category (Electronics)
curl -X GET http://localhost:8080/api/products/category/Electronics

### 5. Get Products by Brand (Apple)
curl -X GET http://localhost:8080/api/products/brand/Apple

### 6. Search Products by Keyword (laptop)
curl -X GET "http://localhost:8080/api/products/search?keyword=laptop"

### 7. Get Products by Price Range (100-500)
curl -X GET "http://localhost:8080/api/products/price-range?min=100&max=500"

### 8. Get In-Stock Products
curl -X GET http://localhost:8080/api/products/in-stock

### 9. Add New Product
curl -X POST http://localhost:8080/api/products ^
  -H "Content-Type: application/json" ^
  -d "{\"name\":\"iPad Pro\",\"description\":\"Tablet device\",\"price\":799.99,\"category\":\"Electronics\",\"stockQuantity\":40,\"brand\":\"Apple\"}"

### 10. Update Product (Product ID: 1)
curl -X PUT http://localhost:8080/api/products/1 ^
  -H "Content-Type: application/json" ^
  -d "{\"name\":\"iPhone 14 Pro\",\"description\":\"Latest Apple smartphone with Pro features\",\"price\":1099.99,\"category\":\"Electronics\",\"stockQuantity\":60,\"brand\":\"Apple\"}"

### 11. Update Stock Quantity (Product ID: 6, quantity: 50)
curl -X PATCH "http://localhost:8080/api/products/6/stock?quantity=50"

### 12. Delete Product (Product ID: 10)
curl -X DELETE http://localhost:8080/api/products/10

## Expected HTTP Status Codes
- 200 OK - Successful GET, PUT, PATCH
- 201 Created - Successful POST
- 204 No Content - Successful DELETE
- 404 Not Found - Resource not found

## Sample Products (10 pre-loaded)
1. iPhone 14 - Apple - Electronics - $999.99 - Stock: 50
2. Samsung Galaxy S23 - Samsung - Electronics - $899.99 - Stock: 30
3. MacBook Pro - Apple - Electronics - $2499.99 - Stock: 20
4. Nike Air Max - Nike - Footwear - $129.99 - Stock: 100
5. Adidas Ultraboost - Adidas - Footwear - $149.99 - Stock: 80
6. Sony WH-1000XM5 - Sony - Electronics - $399.99 - Stock: 0 (Out of stock)
7. Levi's 501 Jeans - Levi's - Clothing - $69.99 - Stock: 150
8. North Face Jacket - North Face - Clothing - $249.99 - Stock: 45
9. Dell XPS 13 - Dell - Electronics - $1299.99 - Stock: 25
10. Puma Sneakers - Puma - Footwear - $79.99 - Stock: 120
