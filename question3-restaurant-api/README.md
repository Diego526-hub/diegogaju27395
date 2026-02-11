# Restaurant Menu API

A Spring Boot REST API for managing restaurant menu items.

## Setup

1. Run the application:
```bash
mvnw.cmd spring-boot:run
```
![Programs started](started.jpg)

2. API runs on: `http://localhost:8083`

## API Endpoints

### Get All Menu Items
```
GET http://localhost:8083/api/menu
```
![Menu Items](menu.jpg)
### Get Menu Item by ID
```
GET http://localhost:8083/api/menu/1
```
![Items by ID](<by id.jpg>)
### Get Items by Category
```
GET http://localhost:8083/api/menu/category/Appetizer
GET http://localhost:8083/api/menu/category/Main%20Course
GET http://localhost:8083/api/menu/category/Dessert
GET http://localhost:8083/api/menu/category/Beverage
```
![by category and i chose beverages](<by cat.jpg>)
### Get Available Items
```
GET http://localhost:8083/api/menu/available?available=true
GET http://localhost:8083/api/menu/available?available=false
```
![available Items](avail.jpg)
### Search by Name
```
GET http://localhost:8083/api/menu/search?name=Salmon
```
![By name, Salmon](<by name.jpg>)
### Add Menu Item
```
POST http://localhost:8083/api/menu

```
![added new item, margarita pizza.](<add nw.jpg>)
### Toggle Availability
```
PUT http://localhost:8083/api/menu/1/availability
```
![toggle avil](ava.jpg)
### Delete Menu Item
```
DELETE http://localhost:8083/api/menu/1
```
![deleted item at 5](delete.jpg)


