CREATE TABLE IF NOT EXISTS product
(
    id              bigint(20)    not null AUTO_INCREMENT,
    product_name    varchar(50)   not null,
    retail_price    decimal(8, 2) not null,
    wholesale_price decimal(8, 2) not null,
    PRIMARY KEY (id)
);

INSERT INTO `product` (product_name, retail_price, wholesale_price)
VALUES ('Tequila', 50.20, 30.00),
       ('Whisky', 70.50, 45.00),
       ('Sake', 65.00, 40.00);