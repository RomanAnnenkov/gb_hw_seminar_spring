CREATE TABLE if NOT EXISTS accounts
(
    id INT NOT NULL PRIMARY KEY,
    account_name varchar(50) NOT NULL,
    account_amount DECIMAL
);