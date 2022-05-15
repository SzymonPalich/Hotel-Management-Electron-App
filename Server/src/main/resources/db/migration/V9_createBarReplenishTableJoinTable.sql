CREATE TABLE IF NOT EXISTS bar_replenish (
    maid_ticket_id  bigint(20) not null,
    product_id      bigint(20) not null,
    primary key (maid_ticket_id, product_id),
    constraint maid_ticket_fk foreign key (maid_ticket_id) references maid_ticket (id),
    constraint product_fk foreign key (product_id) references product (id)
);

INSERT INTO `bar_replenish`
VALUES (2, 1),
       (3, 2),
       (2, 2);