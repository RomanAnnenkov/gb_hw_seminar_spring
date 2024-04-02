INSERT INTO products (id, product_name, product_price, product_quantity)
VALUES ('1', 'block', '10', '20')
ON CONFLICT (id) DO UPDATE
SET product_quantity = EXCLUDED.product_quantity;