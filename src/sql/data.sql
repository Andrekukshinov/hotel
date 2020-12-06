INSERT INTO user(login, pass, is_disabled, role)
VALUES ('admin', SHA1('pass'), false, 'ADMIN');
INSERT INTO user(login, pass, is_disabled)
VALUES ('billy', SHA1('random word'), false);
INSERT INTO user(login, pass, is_disabled)
VALUES ('harrington', SHA1('nico nico doga'), true);
INSERT INTO user(login, pass, is_disabled)
VALUES ('dieHard', SHA1('228'), false);
INSERT INTO user(login, pass, is_disabled, role)
VALUES ('Ronald', SHA1('322'), false,'USER');
INSERT INTO user(login, pass, is_disabled, role)
VALUES ('adminy', SHA1('pass'), false,'USER');
INSERT INTO user(login, pass, is_disabled, role)
VALUES ('bill', SHA1('random word'), false,'USER');
INSERT INTO user(login, pass, is_disabled, role)
VALUES ('harringtony', SHA1('nico nico doga'), true,'USER');
INSERT INTO user(login, pass, is_disabled, role)
VALUES ('dieHardy', SHA1('228'), false,'USER');
INSERT INTO user(login, pass, is_disabled, role)
VALUES ('Geronald', SHA1('322'), false,'USER');
INSERT INTO user(login, pass, is_disabled, role)
VALUES ('Roony', SHA1('228'), false,'USER');
INSERT INTO user(login, pass, is_disabled, role)
VALUES ('RayCarter', SHA1('322'), false,'USER');

INSERT INTO room(apartment_type, person_amount, room_status, room_price) VALUES('standard', 2, 'available', 150.2);
INSERT INTO room(apartment_type, person_amount, room_status, room_price) VALUES('business', 1, 'occupied', 200.1);
INSERT INTO room(apartment_type, person_amount, room_status, room_price) VALUES('lux', 4, 'disabled', 450.2);
INSERT INTO room(apartment_type, person_amount, room_status, room_price) VALUES('standard', 5, 'available', 400.2);
INSERT INTO room(apartment_type, person_amount, room_status, room_price) VALUES('sky_walker', 2, 'available', 150.2);


INSERT INTO application(person_amount, apartment_type, arrival_date, leaving_date, user_id) VALUES(1, 'standard', '2020-09-09', '2020-10-09', 1);
INSERT INTO application(person_amount, apartment_type, arrival_date, leaving_date, user_id) VALUES(1, 'lux', '2020-10-01', '2020-10-11', 2);
INSERT INTO application(person_amount, apartment_type, arrival_date, leaving_date, user_id) VALUES(1, 'sky_walker', '2020-12-22', '2021-01-05', 3);


INSERT INTO user_room(user_id, room_id, room_state) VALUES(1,1,'payed' );
INSERT INTO user_room(user_id, room_id, room_state) VALUES(2,1,'payed' );
INSERT INTO user_room(user_id, room_id, room_state) VALUES(3,3,'approved' );
INSERT INTO user_room(user_id, room_id, room_state) VALUES(4,3,'denied' );
