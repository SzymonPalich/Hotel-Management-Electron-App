CREATE OR REPLACE TABLE client
(
    id           bigint(20)  not null AUTO_INCREMENT,
    name         varchar(50) not null,
    surname      varchar(50) not null,
    email        varchar(50) null,
    phone_number varchar(9)  null,

    primary key (id)
);

INSERT INTO `client` (`id`, `name`, `surname`, `email`, `phone_number`)
VALUES (NULL, 'Jan', 'Dzban', 'jan@spurvago.com', '881223295'),
       (NULL, 'Aureliusz', 'Zalewski', 'aur123@spurvago.com', '905984460'),
       (NULL, 'Marcin', 'Sikora', 'czxz21412@spurvago.com', '905984460'),
       (NULL, 'Eugeniusz', 'Witkowski', 'sddsfa@spurvago.com', '833498212'),
       (NULL, 'Joachim', 'Lis', 'joachim@spurvago.com', '151919298'),
       (NULL, 'Henryk', 'Kwiatkowski', 'henry@spurvago.com', '981795766'),
       (NULL, 'Lucjan', 'Wójcik', 'tesatgfa2@spurvago.com', '575091894'),
       (NULL, 'Dariusz', 'Włodarczyk', 'darke@spurvago.com', '705142725'),
       (NULL, 'Adam', 'Stępień', 'adam@spurvago.com', '844994143'),
       (NULL, 'Anastazy', 'Rutkowski', 'anastazy@spurvago.com', '988196368'),
       (NULL, 'Artur', 'Michalak', 'artur@spurvago.com', '673013495'),
       (NULL, 'Patryk', 'Plizga', 'patryk@spurvago.com', '996239062'),
       (NULL, 'Krzysztof', 'Przewoźnik', 'krzysztof@spurvago.com', '143676088'),
       (NULL, 'Przemysław', 'Paluch', 'przemysław@spurvago.com', '697810506'),
       (NULL, 'Adam', 'Kowalski', 'adam@gmail.com', '221824389');