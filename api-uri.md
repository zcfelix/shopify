#### Store and Product Service

##### store
1. POST   /stores
2. GET    /stores
3. GET    /stores/{id}

store属性：id, owner id, name, description, Lis\<Product>

##### product
1. POST   /stores/{id}/products
2. GET    /stores/{id}/products 
4. PUT    /stores/{id}/products/{id}/unloading
5. PUT    /stores/{id}/products/{id}/uploading
6. GET    /products
7. GET    /products/{id}

product属性：id, store id, name, description, Price, Inventory, state

#### Product Price Service
1. POST    /products/{id}/prices
2. GET     /products/{id}/prices
3. GET     /products/{id}/prices/{id}
4. GET     /products/{id}/current-price

product price属性：id, product id, amount, created_at

#### Inventory Service
1. POST    /products/{id}/inventories
2. GET     /products/{id}/inventories/current
3. PATCH   /products/{id}/inventories/current

inventory 属性：id, product id, amount 

#### Order Service
1. POST    /orders
2. GET     /orders
3. GET     /orders/{id}
4. POST    /orders/{id}/payment
5. POST    /orders/{id}/shipment
6. POST    /orders/{id}/receive-confirmation

order 属性： id, customer id, List\<OrderItem>, total amount, created_at

#### Refund Service
1. POST    /orders/{id}/returns
2. GET     /orders/{id}/returns
3. GET     /orders/{id}/returns/{id}
2. POST    /orders/{id}/returns/{id}/return-confirmation

return 属性：id, order id, List\<ReturnItem>, total amount, created_at

#### Customer Service
1. POST    /customers
2. GET     /customers/{id}
3. PATCH   /customers/{id}

customer 属性：id, name, List\<Order>  

#### Cart Service
1. POST    /customers/{id}/cart/items
2. PUT     /customers/{id}/cart/items

cart 属性: id, customer id, List\<CartItem>, total amount