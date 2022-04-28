CREATE TABLE IF NOT EXISTS room
(
    id           bigint(20) not null AUTO_INCREMENT,
    room_number  int(50)    not null,
    room_type_id bigint(20) not null,
    status       int(20)    not null,

    PRIMARY KEY (id),
    CONSTRAINT room_type_fk FOREIGN KEY (room_type_id) REFERENCES room_type (id)
);

INSERT INTO `room` (`id`, `room_number`, `room_type_id`, `status`)
VALUES (NULL, 101, 1, 1),
       (NULL, 102, 2, 2),
       (NULL, 103, 3, 3);
