CREATE TABLE IF NOT EXISTS maid_ticket
(
    id                bigint(20) not null AUTO_INCREMENT,
    room_id           bigint(20) not null,
    employee_id       bigint(20) default null,
    finalization_date date       default null,
    primary key (id),
    constraint room_fk FOREIGN KEY (room_id) references room (id) on delete cascade,
    constraint emp_fk FOREIGN KEY (employee_id) references employee (id) on delete set null
);

INSERT INTO `maid_ticket`(`room_id`, `employee_id`)
VALUES (2, 2),
       (1, 2),
       (2, null);