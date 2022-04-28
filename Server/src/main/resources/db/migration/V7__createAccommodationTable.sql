CREATE TABLE IF NOT EXISTS accommodation
(
    id               bigint(20) not null auto_increment,
    client_id        bigint(20) not null,
    room_id          bigint(20) not null,
    start_date       date       not null,
    end_date         date       not null,
    reservation_only tinyint(1) not null,

    PRIMARY KEY (id),
    CONSTRAINT accommodation_client_fk FOREIGN KEY (client_id) REFERENCES client (id),
    CONSTRAINT accommodation_room_fk FOREIGN KEY (room_id) REFERENCES room (id)
);

INSERT INTO `accommodation`(`client_id`, `room_id`, `start_date`, `end_date`, `reservation_only`)
VALUES (1, 1, '2022-04-15', '2022-04-23', 1),
       (2, 2, '2021-06-21', '2021-07-03', 0);