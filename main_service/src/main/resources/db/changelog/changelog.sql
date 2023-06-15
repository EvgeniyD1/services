--liquibase formatted sql

--changeset edolgy:1
drop table if exists article;
drop table if exists users;

--changeset edolgy:2
create table users
(
    id                bigserial
        constraint users_pk
            primary key,
    username          varchar(30)  not null,
    password          varchar(256) not null,
    email             varchar(256),
    registration_date date         not null,
    role              varchar(30)  not null
);

alter table users
    owner to postgres;

create unique index users_id_uindex
    on users (id);

create unique index users_username_uindex
    on users (username);


--changeset edolgy:3
create table article
(
    id            bigserial
        constraint article_pk
            primary key,
    user_id       bigint         not null
        constraint article_users_id_fk
            references users,
    title         varchar(256)   not null,
    context       varchar(10000) not null,
    creation_date date           not null
);

alter table article
    owner to postgres;

create unique index article_id_uindex
    on article (id);


--changeset edolgy:4
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

--changeset edolgy:5
INSERT INTO article (id, user_id, title, context, creation_date)
VALUES (1, 1, 'text', 'text text', '2023-06-12');
INSERT INTO article (id, user_id, title, context, creation_date)
VALUES (2, 1, 'some new article', 'somme new information about something', '2023-06-14');
INSERT INTO article (id, user_id, title, context, creation_date)
VALUES (3, 1, 'Cars (film)',
        'Cars is a 2006 American computer-animated sports comedy film produced by Pixar Animation Studios for Walt Disney Pictures. The film was directed by John Lasseter from a screenplay by Dan Fogelman, Lasseter, Joe Ranft, Kiel Murray, Phil Lorin, and Jorgen Klubien and a story by Lasseter, Ranft, and Klubien, and was the final film independently produced by Pixar after its purchase by Disney in January 2006.',
        '2023-06-12');
INSERT INTO article (id, user_id, title, context, creation_date)
VALUES (4, 1, 'New Year',
        'New Year is the time or day at which a new calendar year begins and the calendar''s year count increments by one. Many cultures celebrate the event in some manner. In the Gregorian calendar, the most widely used calendar system today, New Year occurs on January 1 (New Year''s Day, preceded by New Year''s Eve). This was also the first day of the year in the original Julian calendar and the Roman calendar (after 153 BC).',
        '2023-06-12');
INSERT INTO article (id, user_id, title, context, creation_date)
VALUES (5, 1, 'Bears',
        'Bears are carnivoran mammals of the family Ursidae. They are classified as caniforms, or doglike carnivorans. Although only eight species of bears are extant, they are widespread, appearing in a wide variety of habitats throughout the Northern Hemisphere and partially in the Southern Hemisphere. Bears are found on the continents of North America, South America, Europe, and Asia. Common characteristics of modern bears include large bodies with stocky legs, long snouts, small rounded ears, shaggy hair, plantigrade paws with five nonretractile claws, and short tails. ',
        '2023-06-12');
INSERT INTO article (id, user_id, title, context, creation_date)
VALUES (6, 2, 'Beer',
        'Beer is one of the oldest types of alcoholic drinks in the world, and the most widely consumed. It is the third most popular drink overall after potable water and tea.',
        '2023-06-12');
INSERT INTO article (id, user_id, title, context, creation_date)
VALUES (7, 2, 'Football',
        'Football is a family of team sports that involve, to varying degrees, kicking a ball to score a goal. Unqualified, the word football normally means the form of football that is the most popular where the word is used. Sports commonly called football include association football (known as soccer in North America, Ireland and Australia); gridiron football (specifically American football or Canadian football); Australian rules football; rugby union and rugby league; and Gaelic football.',
        '2023-06-14');
INSERT INTO article (id, user_id, title, context, creation_date)
VALUES (8, 2, 'River',
        'A river is a natural flowing watercourse, usually freshwater stream, flowing on the surface or inside caves towards another waterbody at a lower elevation, such as an ocean, sea, bay, lake, wetland, or another river. In some cases, a river flows into the ground or becomes dry at the end of its course without reaching another body of water.',
        '2023-06-14');
INSERT INTO article (id, user_id, title, context, creation_date)
VALUES (9, 9, 'Lake',
        'A lake is a naturally occurring, relatively large body of water localized in a basin surrounded by dry land. A lake generally has a slower-moving flow than the inflow or outflow stream(s) that serve to feed or drain it. Lakes lie completely on land and are separate from the ocean, although, like the much larger oceans, they form part of the Earth''s water cycle by serving as large standing pools of storage water. Most lakes are freshwater and account for almost all the world''s surface freshwater, but some are salt lakes with salinities even higher than that of seawater.',
        '2023-06-14');
INSERT INTO article (id, user_id, title, context, creation_date)
VALUES (10, 9, 'Java (programming language)',
        'ava is a high-level, class-based, object-oriented programming language that is designed to have as few implementation dependencies as possible. It is a general-purpose programming language intended to let programmers write once, run anywhere (WORA),meaning that compiled Java code can run on all platforms that support Java without the need to recompile.',
        '2023-06-14');

--changeset edolgy:6
alter sequence users_id_seq restart with 11;
alter sequence article_id_seq restart with 11;