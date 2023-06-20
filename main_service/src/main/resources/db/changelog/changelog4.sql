--liquibase formatted sql

--changeset edolgy:4
INSERT INTO article (id, user_id, title, context, created_at)
VALUES (1, 1, 'text', 'text text', '2023-06-12');
INSERT INTO article (id, user_id, title, context, created_at)
VALUES (2, 1, 'some new article', 'somme new information about something', '2023-06-14');
INSERT INTO article (id, user_id, title, context, created_at)
VALUES (3, 1, 'Cars (film)',
        'Cars is a 2006 American computer-animated sports comedy film produced by Pixar Animation Studios for Walt Disney Pictures. The film was directed by John Lasseter from a screenplay by Dan Fogelman, Lasseter, Joe Ranft, Kiel Murray, Phil Lorin, and Jorgen Klubien and a story by Lasseter, Ranft, and Klubien, and was the final film independently produced by Pixar after its purchase by Disney in January 2006.',
        '2023-06-12');
INSERT INTO article (id, user_id, title, context, created_at)
VALUES (4, 1, 'New Year',
        'New Year is the time or day at which a new calendar year begins and the calendar''s year count increments by one. Many cultures celebrate the event in some manner. In the Gregorian calendar, the most widely used calendar system today, New Year occurs on January 1 (New Year''s Day, preceded by New Year''s Eve). This was also the first day of the year in the original Julian calendar and the Roman calendar (after 153 BC).',
        '2023-06-12');
INSERT INTO article (id, user_id, title, context, created_at)
VALUES (5, 1, 'Bears',
        'Bears are carnivoran mammals of the family Ursidae. They are classified as caniforms, or doglike carnivorans. Although only eight species of bears are extant, they are widespread, appearing in a wide variety of habitats throughout the Northern Hemisphere and partially in the Southern Hemisphere. Bears are found on the continents of North America, South America, Europe, and Asia. Common characteristics of modern bears include large bodies with stocky legs, long snouts, small rounded ears, shaggy hair, plantigrade paws with five nonretractile claws, and short tails. ',
        '2023-06-12');
INSERT INTO article (id, user_id, title, context, created_at)
VALUES (6, 2, 'Beer',
        'Beer is one of the oldest types of alcoholic drinks in the world, and the most widely consumed. It is the third most popular drink overall after potable water and tea.',
        '2023-06-12');
INSERT INTO article (id, user_id, title, context, created_at)
VALUES (7, 2, 'Football',
        'Football is a family of team sports that involve, to varying degrees, kicking a ball to score a goal. Unqualified, the word football normally means the form of football that is the most popular where the word is used. Sports commonly called football include association football (known as soccer in North America, Ireland and Australia); gridiron football (specifically American football or Canadian football); Australian rules football; rugby union and rugby league; and Gaelic football.',
        '2023-06-14');
INSERT INTO article (id, user_id, title, context, created_at)
VALUES (8, 2, 'River',
        'A river is a natural flowing watercourse, usually freshwater stream, flowing on the surface or inside caves towards another waterbody at a lower elevation, such as an ocean, sea, bay, lake, wetland, or another river. In some cases, a river flows into the ground or becomes dry at the end of its course without reaching another body of water.',
        '2023-06-14');
INSERT INTO article (id, user_id, title, context, created_at)
VALUES (9, 9, 'Lake',
        'A lake is a naturally occurring, relatively large body of water localized in a basin surrounded by dry land. A lake generally has a slower-moving flow than the inflow or outflow stream(s) that serve to feed or drain it. Lakes lie completely on land and are separate from the ocean, although, like the much larger oceans, they form part of the Earth''s water cycle by serving as large standing pools of storage water. Most lakes are freshwater and account for almost all the world''s surface freshwater, but some are salt lakes with salinities even higher than that of seawater.',
        '2023-06-14');
INSERT INTO article (id, user_id, title, context, created_at)
VALUES (10, 9, 'Java (programming language)',
        'ava is a high-level, class-based, object-oriented programming language that is designed to have as few implementation dependencies as possible. It is a general-purpose programming language intended to let programmers write once, run anywhere (WORA),meaning that compiled Java code can run on all platforms that support Java without the need to recompile.',
        '2023-06-14');

alter sequence article_id_seq restart with 11;