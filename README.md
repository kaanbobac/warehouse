[![Build Status](https://www.travis-ci.com/kaanbobac/warehouse.svg?branch=master)](https://www.travis-ci.com/kaanbobac/warehouse)
# Warehouse Software
It is a software project for managing a warehouse. Warehouse has articles in its inventory. Based on warehouse inventory and product catalog, users can buy product.



## Getting Started

### Prerequisites
* Git
* JDK 8 or later
* Maven 3.0 or later
* Project Lombok Plugin for IDE
https://projectlombok.org/setup/overview


### Clone
To get started you can simply clone this repository using git:
```
git clone git@github.com:kaanbobac/warehouse.git
```

### Configuration

 1. Once Springboot application is started, two json files, i.e inventory.json and products.json, are parsed and stored in in-memory H2 database.
 2. Inventory resource file : `inventory.json`
 Structure of the json should be as below:
 ```json
{
  "inventory": [
    {
      "art_id": "1",
      "name": "leg",
      "stock": "12"
    }
  ]
}

```

2. Product resource file: `products.json`
 Structure of the json should be as below:
```json
{
  "products": [
    {
      "name": "Dining Chair",
      "contain_articles": [
        {
          "art_id": "1",
          "amount_of": "4"
        },
        {
          "art_id": "2",
          "amount_of": "8"
        },
        {
          "art_id": "3",
          "amount_of": "1"
        }
      ]
    }    
  ]
}
```
3. Json file paths and other configuratios are located in `src/resources/application.properties`.
```properties
app.data.inventory.json.path=/json/inventory.json
app.data.product.json.path=/json/products.json
```
5. Project uses in memory H2 database. Configurations can be adjusted in application properties.
```properties
app.data.inventory.json.path=/json/inventory.json
app.data.product.json.path=/json/products.json
```

###  How to test
####  Query All Products
This API is used for query all products in the system with quantites.

`GET /query-all/`

    http://localhost:8080/query-all

#### Response
    [{"id":4,"name":"Dining Chair","quantity":2},{"id":8,"name":"Dinning Table","quantity":0}]
 
 ####  Sell Product
This API is used to remove a product. Once a product is removed, corrsponding articles will be also reduced in inventory stocks. After e calculation, API returns new product list
`POST /sell-product/`

    http://localhost:8080/sell-product?id=XXX

