DELETE FROM client;
ALTER TABLE client AUTO_INCREMENT = 1;

INSERT INTO `client` (`id`, `first_name`, `last_name`, `email`, `phone_number`)
VALUES (1, 'Test1', 'Test1', 'test1@test.test', '111111111'),
       (2, 'Test2', 'Test2', 'test2@test.test', '222222222'),
       (3, 'Test3', 'Test3', 'test3@test.test', '333333333'),
       (4, 'Test4', 'Test4', 'test4@test.test', '444444444'),
       (5, 'Test5', 'Test5', 'test5@test.test', '555555555');