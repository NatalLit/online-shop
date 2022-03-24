CREATE TABLE products(
    id SERIAL PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    price NUMERIC(9,2) NOT NULL,
    creation_date DATE DEFAULT now()
);

INSERT INTO products (name, price) VALUES ('Samsung Galaxy S22','1700');
INSERT INTO products (name, price) VALUES ('Apple iPhone 11','800');
INSERT INTO products (name, price) VALUES ('Xiaomi 11 Lite','400');
