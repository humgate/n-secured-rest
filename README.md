## RESTful application example implementing querying postgreSQL database and featuring authorization

Exposes 4 GET endpoints:<br>
- /products/fetch-product with query parameter `name`. Method access set to ROLE_READ.<br>
To test go to http://localhost:8080/products/fetch-product?name=alexey and login as user = fedor; 
password = passFedor.<br>
- /customers. Method access set to ROLE_ADMIN && ROLE_READ.<br>
To test: logout, go to http://localhost:8080/customers, login as user = alex; password=passAlex.<br>
- /orders with query parameter `customername`. Method allows access only if requesting logged user requests his own
orders (i.e. the `customername` request parameter value equals to user login) and in addition has role ROLE_READ.<br>
To test: logout, go to http://localhost:8080/orders?customername=alex, login as user = alex; password=passAlex.<br>
- /users. Method access set to ROLE_ADMIN && ROLE_READ.<br>
To test: logout, go to http://localhost:8080/orders?customername=Иван, login as user = Иван; password=пассИван.<br>

