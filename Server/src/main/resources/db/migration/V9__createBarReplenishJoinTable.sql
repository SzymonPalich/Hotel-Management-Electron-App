CREATE TABLE IF NOT EXISTS bar_replenish
(
    id             bigint(20) not null AUTO_INCREMENT,
    maid_ticket_id bigint(20) not null,
    product_id     bigint(20) not null,
    amount         int(20)    not null,
    primary key (id),
    constraint maid_ticket_fk foreign key (maid_ticket_id) references maid_ticket (id) on delete cascade,
    constraint product_fk foreign key (product_id) references product (id) on delete cascade
);

INSERT INTO `bar_replenish` (maid_ticket_id, product_id, amount)
VALUES (2, 1, 2),
       (3, 2, 15),
       (2, 2, 7);