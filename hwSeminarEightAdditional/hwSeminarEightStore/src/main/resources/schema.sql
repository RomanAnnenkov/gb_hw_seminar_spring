CREATE TABLE if NOT EXISTS products
(
    id INT NOT NULL PRIMARY KEY,
    product_name varchar(50) NOT NULL,
    product_price DECIMAL,
    product_quantity INT
);