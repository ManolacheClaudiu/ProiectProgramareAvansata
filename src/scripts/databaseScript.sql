drop table actors;
drop table genres;
drop table associative;
drop table directors;
drop table movies;

CREATE TABLE movies(
                       id_movie int PRIMARY KEY NOT NULL AUTO_INCREMENT,
                       title VARCHAR(30),
                       release_date DATE,
                       duration int,
                       score int
)
;
CREATE TABLE genres(
                       id_gen int PRIMARY KEY NOT NULL AUTO_INCREMENT,
                       name VARCHAR(30)
)
;
CREATE TABLE actors(
                       id_actor int PRIMARY KEY NOT NULL AUTO_INCREMENT,
                       id_movie int ,
                       name VARCHAR(30)
    --  FOREIGN KEY(id_movie)
--                            REFERENCES movies(id_movie)
)
;
CREATE TABLE associative(
                            id_associative int PRIMARY KEY NOT NULL AUTO_INCREMENT,
                            id_movie int,
                            id_gen int
    --     FOREIGN KEY(id_movie)
--                                 REFERENCES movies(id_movie)
)
;
CREATE TABLE directors(
                          id_director int PRIMARY KEY NOT NULL AUTO_INCREMENT,
                          id_movie int ,
                          name varchar(30)
    --     FOREIGN KEY(id_movie)
--                               REFERENCES movies(id_movie)
)
;

INSERT INTO movies (title ,release_date,duration,score) VALUES ('Movie1', STR_TO_DATE("10 May 2017", "%d %M %Y"),130,142);
INSERT INTO movies (title ,release_date,duration,score) VALUES   ( 'Movie2', STR_TO_DATE("15 March 2007", "%d %M %Y"),170,105);
INSERT INTO movies (title ,release_date,duration,score)  VALUES   ( 'Movie3', STR_TO_DATE("13 August 2019", "%d %M %Y"),140,132);
INSERT INTO movies (title ,release_date,duration,score)  VALUES   ( 'Movie4', STR_TO_DATE("16 June 2002", "%d %M %Y"),120,1233);
INSERT INTO movies (title ,release_date,duration,score)  VALUES   ( 'Movie5', STR_TO_DATE("30 December 2021", "%d %M %Y"),100,12);
INSERT INTO genres (name) VALUES ( 'Action');
INSERT INTO genres (name) VALUES ( 'Drama');
INSERT INTO associative (id_movie, id_gen) VALUES ('111','21');
INSERT INTO associative (id_movie, id_gen)  VALUES ('112','22');
INSERT INTO associative (id_movie, id_gen)  VALUES ('113','22');
INSERT INTO associative  (id_movie, id_gen) VALUES ('114','21');
INSERT INTO associative  (id_movie, id_gen) VALUES ('115','22');
INSERT INTO actors(id_movie, name)  VALUES ('111', 'Ion Mihai');
INSERT INTO actors(id_movie, name)  VALUES ('111', 'Ioana Maria');
INSERT INTO directors (id_movie, name) VALUES ('111', 'Ionel Mihaila');
INSERT INTO directors (id_movie, name) VALUES ('114', 'Magda Belciug');
COMMIT;