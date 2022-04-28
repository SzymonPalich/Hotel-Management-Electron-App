DELETE FROM client;
ALTER TABLE client AUTO_INCREMENT = 1;

INSERT INTO `client` (`id`, `first_name`, `last_name`, `email`, `phone_number`)
VALUES (1, 'Jacek', 'Testowy', 'j.testowy@test.test', '111111111'),
       (2, 'Marcin', 'Inny', 'm.inny@test.test', NULL),
       (3, 'Jan', 'Kowalski', NULL, '333333333'),
       (4, 'Szymon', 'Jakiś', 's.jakis@test.test', '444444444'),
       (5, 'Piotr', 'Nowak', 'p.nowak@test.test', '555555555');