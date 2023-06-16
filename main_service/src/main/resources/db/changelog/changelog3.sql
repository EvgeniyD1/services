--liquibase formatted sql

--changeset edolgy:3
INSERT INTO users (id, username, password, email, registration_date, role)
VALUES (1, 'user', '$2a$08$bsEgKLX0HB9MiXE7TgYoYuFPzhusbwJnV2AQvmzvNbtMxh1QAE1bK', 'newemail@mail.comnew',
        '2023-06-13', 'ADMIN');
INSERT INTO users (id, username, password, email, registration_date, role)
VALUES (2, 'zzz', '$2a$08$HNWNJNTbWK3sJq66u.ZSqe6Ig6ELSkSMIGVSphkIHn.NeQXiZMChq', 'kek@kek.com', '2023-06-12', 'USER');
INSERT INTO users (id, username, password, email, registration_date, role)
VALUES (3, 'user-1', '$2a$08$bsEgKLX0HB9MiXE7TgYoYuFPzhusbwJnV2AQvmzvNbtMxh1QAE1bK', 'user-1@milo.com', '2023-06-12',
        'ADMIN');
INSERT INTO users (id, username, password, email, registration_date, role)
VALUES (4, 'nikita', '$2a$08$bsEgKLX0HB9MiXE7TgYoYuFPzhusbwJnV2AQvmzvNbtMxh1QAE1bK', 'nikita@milaaqo', '2023-06-12',
        'ADMIN');
INSERT INTO users (id, username, password, email, registration_date, role)
VALUES (5, 'pivo', '$2a$08$HNWNJNTbWK3sJq66u.ZSqe6Ig6ELSkSMIGVSphkIHn.NeQXiZMChq', 'pivo@pivo.com', '2023-06-13',
        'ADMIN');
INSERT INTO users (id, username, password, email, registration_date, role)
VALUES (6, 'newuser', '$2a$08$bsEgKLX0HB9MiXE7TgYoYuFPzhusbwJnV2AQvmzvNbtMxh1QAE1bK', 'new@milo.com', '2023-06-12',
        'ADMIN');
INSERT INTO users (id, username, password, email, registration_date, role)
VALUES (7, 'kek', '$2a$08$bsEgKLX0HB9MiXE7TgYoYuFPzhusbwJnV2AQvmzvNbtMxh1QAE1bK', 'milo@a', '2023-06-14', 'USER');
INSERT INTO users (id, username, password, email, registration_date, role)
VALUES (8, 'lol', '$2a$08$bsEgKLX0HB9MiXE7TgYoYuFPzhusbwJnV2AQvmzvNbtMxh1QAE1bK', 'milo@d.s', '2023-06-15', 'USER');
INSERT INTO users (id, username, password, email, registration_date, role)
VALUES (9, 'lul', '$2a$08$bsEgKLX0HB9MiXE7TgYoYuFPzhusbwJnV2AQvmzvNbtMxh1QAE1bK', 'milo@milo.a', '2023-06-07', 'USER');
INSERT INTO users (id, username, password, email, registration_date, role)
VALUES (10, 'kekw', '$2a$08$bsEgKLX0HB9MiXE7TgYoYuFPzhusbwJnV2AQvmzvNbtMxh1QAE1bK', 'privet@kek.da', '2023-06-01',
        'USER');

alter sequence users_id_seq restart with 11;