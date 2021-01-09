set foreign_key_checks =0;
drop table User;
drop table Room;
drop table Application;


CREATE TABLE IF NOT EXISTS User
(
    id          BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    login       VARCHAR(25) UNIQUE NOT NULL,
    password    VARCHAR(50)        NOT NULL,
    is_DISABLED BIT                    DEFAULT 0,
    role        ENUM ('USER', 'ADMIN') DEFAULT ('USER')
);

CREATE TABLE IF NOT EXISTS Room
(
    id             BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    apartment_type ENUM ('STANDARD', 'BUSINESS', 'SKY_WALKER', 'LUX'),
    capacity       TINYINT            NOT NULL,
    room_number    INT UNIQUE         NOT NULL,
    status         ENUM ('AVAILABLE', 'DISABLED', 'OCCUPIED'),
    price          DECIMAL            NOT NULL,
    picture_url    VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS Application
(
    id                BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    person_amount     TINYINT            NOT NULL,
    apartment_type    ENUM ('STANDARD', 'BUSINESS', 'SKY_WALKER', 'LUX'),
    arrival_date      DATE               NOT NULL,
    leaving_date      DATE               NOT NULL,
    user_id           BIGINT             NOT NULL,
    application_state ENUM ('APPROVED', 'IN_ORDER', 'DENIED') DEFAULT ('IN_ORDER'),
    FOREIGN KEY (user_id) REFERENCES User (id)
);

ALTER TABLE application
    ADD room_id bigint;
ALTER TABLE application
    ADD total_price int;
ALTER TABLE application
    ADD FOREIGN KEY (room_id) REFERENCES Room(id);


INSERT INTO user(login, password, is_DISABLED, role)
VALUES ('admin', SHA1('pass'), false, 'ADMIN');
INSERT INTO user(login, password, is_DISABLED)
VALUES ('billy', SHA1('random word'), false);
INSERT INTO user(login, password, is_DISABLED)
VALUES ('harrington', SHA1('nico nico doga'), true);
INSERT INTO user(login, password, is_DISABLED)
VALUES ('dieHard', SHA1('228'), false);
INSERT INTO user(login, password, is_DISABLED, role)
VALUES ('Ronald', SHA1('322'), false, 'USER');
INSERT INTO user(login, password, is_DISABLED, role)
VALUES ('adminy', SHA1('pass'), false, 'USER');
INSERT INTO user(login, password, is_DISABLED, role)
VALUES ('bill', SHA1('random word'), false, 'USER');
INSERT INTO user(login, password, is_DISABLED, role)
VALUES ('harringtony', SHA1('nico nico doga'), true, 'USER');
INSERT INTO user(login, password, is_DISABLED, role)
VALUES ('dieHardy', SHA1('228'), false, 'USER');
INSERT INTO user(login, password, is_DISABLED, role)
VALUES ('Geronald', SHA1('322'), false, 'USER');
INSERT INTO user(login, password, is_DISABLED, role)
VALUES ('Roony', SHA1('228'), false, 'USER');
INSERT INTO user(login, password, role, is_DISABLED)
VALUES ('RayCartery', SHA1('322'), 'USER', false);


INSERT INTO room(apartment_type, room_number, capacity, status, price)
VALUES ('STANDARD', 202, 2, 'AVAILABLE', 150.2);
INSERT INTO room(apartment_type, room_number, capacity, status, price)
VALUES ('BUSINESS', 105, 1, 'OCCUPIED', 200.1);
INSERT INTO room(apartment_type, room_number, capacity, status, price)
VALUES ('LUX', 306, 4, 'DISABLED', 450.2);
INSERT INTO room(apartment_type, room_number, capacity, status, price)
VALUES ('STANDARD', 104, 5, 'AVAILABLE', 400.2);
INSERT INTO room(apartment_type, room_number, capacity, status, price)
VALUES ('SKY_WALKER', 101, 2, 'AVAILABLE', 150.2);
INSERT INTO room(apartment_type, room_number, capacity, status, price)
VALUES ('STANDARD', 106, 5, 'AVAILABLE', 400.2);
INSERT INTO room(apartment_type, room_number, capacity, status, price)
VALUES ('SKY_WALKER', 109, 2, 'AVAILABLE', 150.2);


INSERT INTO application(person_amount, apartment_type, arrival_date, leaving_date, application_state, user_id)
VALUES (1, 'STANDARD', '2020-09-09', '2020-10-09', 'IN_ORDER', 1);
INSERT INTO application(person_amount, apartment_type, arrival_date, leaving_date, application_state, user_id)
VALUES (1, 'LUX', '2020-10-01', '2020-10-11', 'APPROVED', 2);
INSERT INTO application(person_amount, apartment_type, arrival_date, leaving_date, application_state, user_id)
VALUES (1, 'SKY_WALKER', '2020-12-22', '2021-01-05', 'DENIED', 3);
INSERT INTO application(person_amount, apartment_type, arrival_date, leaving_date, user_id)
VALUES (1, 'SKY_WALKER', '2020-12-22', '2021-02-05', 1);
INSERT INTO application(person_amount, apartment_type, arrival_date, leaving_date, application_state, user_id)
VALUES (1, 'SKY_WALKER', '2020-12-22', '2021-02-05', 'APPROVED', 4);
INSERT INTO application(person_amount, apartment_type, arrival_date, leaving_date, application_state, user_id)
VALUES (1, 'SKY_WALKER', '2020-12-22', '2021-02-05', 'DENIED', 4);
INSERT INTO application (person_amount, apartment_type, arrival_date, leaving_date, user_id)
VALUES (1, 'LUX', '2021-01-05', '2021-01-06', 1);
INSERT INTO application (person_amount,  application_state, apartment_type, arrival_date, leaving_date, user_id, room_id, total_price)
VALUES (1, 'APPROVED', 'LUX', '2021-01-05', '2021-01-06', 1, 2, 2345);
INSERT INTO application (person_amount, application_state, apartment_type, arrival_date, leaving_date, user_id, room_id, total_price)
VALUES (1, 'APPROVED', 'LUX', '2021-01-05', '2021-01-06', 4, 1, 700);


select *
from User
where login = 'admin'
  and password = SHA1('pass');
# select *
# from application_room;
# # select * from User where id='1';
# # update User set login='dieHard', pass='cad06f3c4901bbcd4a396dd83c4544a146d6e3e8', is_DISABLED=1 where login='dieHard'
# SELECT Application.id,
#        person_amount,
#        apartment_type,
#        arrival_date,
#        leaving_date,
#        user_id,
#        room_id,
#        application_state
# FROM Application
#          LEFT JOIN Application_Room on Application.id = Application_Room.application_id
# WHERE Application.id = 1;

SELECT DATEDIFF('2017/08/25', '2011/08/25');

SELECT (SELECT price FROM room WHERE id = 1) *
       (SELECT DATEDIFF(
                       (SELECT Application.leaving_date FROM Application WHERE id = 1),
                       (SELECT Application.arrival_date FROM Application WHERE id = 1)
                   ));


SELECT *
FROM application;
#          LEFT JOIN application_room ON application.id = application_room.application_id
#          RIGHT JOIN room
#                     ON Room.id = Application_Room.room_id
# where application_id = 6;
#
#
# SELECT *
# FROM application_room
#          RIGHT JOIN application ON application.id = application_room.application_id
# where user_id = 4;



SELECT *
FROM Application
where user_id = 4;

SELECT * FROM Application WHERE application_state = 'IN_ORDER'   ORDER BY arrival_date DESC LIMIT ?, ?;
SELECT * FROM Application ORDER BY arrival_date DESC ;

# SELECT *
# FROM Application A
#          INNER JOIN Application_Room AR ON A.id = AR.application_id
#          INNER JOIN Room R ON AR.room_id = R.id
# WHERE application_id = 1;
#
# SELECT *
# FROM Application
#          LEFT JOIN Application_Room on Application.id = Application_room.application_id
#          RIGHT JOIN Room on Room.id = Application_Room.room_id
# WHERE application_id = 2;

DELETE FROM Room WHERE id=10;

SELECT COUNT(*) FROM Room;

SELECT * FROM Room;

UPDATE Application SET room_id = null where id=2;
SELECT * FROM Application where application_state = 'IN_ORDER';
SELECT * FROM user WHERE role ='USER' ORDER BY login LIMIT 2,12;
SELECT * FROM Application LEFT JOIN room R ON application.room_id = R.id WHERE user_id=1 ORDER BY arrival_date DESC LIMIT 1, 3;
# UPDATE Application SET person_amount=2, apartment_type='LUX', arrival_date='2020-12-22', leaving_date='2021-02-05', application_state='IN_ORDER', user_id=1, room_id=null, total_price=null WHERE id=7;
