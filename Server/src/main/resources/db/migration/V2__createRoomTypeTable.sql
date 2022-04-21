CREATE OR REPLACE TABLE room_type
(
    id    bigint(20)    not null AUTO_INCREMENT,
    type  varchar(50)   not null,
    price decimal(8, 2) not null,
    primary key (id)
);

INSERT INTO `room_type` (`id`, `type`, `price`)
VALUES (NULL, 'Ekonomiczny', '200.00'),
       (NULL, 'Standardowy', '350.00'),
       (NULL, 'Deluxe', '500.00');
