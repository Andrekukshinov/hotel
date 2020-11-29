INSERT INTO role(name) VALUES('admin');
INSERT INTO role(name) VALUES('user');


INSERT INTO user(login, pass, is_disabled, role_id) VALUES('admin', SHA1('pass'), false, 0);
INSERT INTO user(login, pass, is_disabled, role_id) VALUES('billy', SHA1('random word'), false, 1);
INSERT INTO user(login, pass, is_disabled, role_id) VALUES('harrington', SHA1('nico nico doga'), true, 1);
INSERT INTO user(login, pass, is_disabled, role_id) VALUES('dieHard', SHA1('228'), false, 1);
INSERT INTO user(login, pass, is_disabled, role_id) VALUES('Ronald', SHA1('322'), false, 1);


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
