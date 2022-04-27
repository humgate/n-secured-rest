## RESTful application example implementing querying postgreSQL database and featuring authorization
Exposes two GET endpoints:<br>
- at /products/fetch-product with query parameter `name`. Unauthorized access allowed<br>
To test go to http://localhost:8080/products/fetch-product?name=alexey.
- at /customers. Requires authorization. To test http://localhost:8080/products.<br> 
Two test users added to db with names: alexey (password = passAlex) and fedor (password = passFedor).<br>
alexey has access to /customers endpoint by fedor does not.<br>
Spring security configured with custom UserDetailsService getting authorization info from db using hibernate.
