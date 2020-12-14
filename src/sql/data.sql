
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
VALUES ('BUSINESS', 105,1, 'OCCUPIED', 200.1);
INSERT INTO room(apartment_type, room_number, capacity, status, price)
VALUES ('LUX', 306, 4, 'DISABLED', 450.2);
INSERT INTO room(apartment_type, room_number, capacity, status, price)
VALUES ('STANDARD', 104, 5, 'AVAILABLE', 400.2);
INSERT INTO room(apartment_type, room_number, capacity, status, price)
VALUES ('SKY_WALKER', 101, 2, 'AVAILABLE', 150.2);


INSERT INTO application(person_amount, apartment_type, arrival_date, leaving_date, application_state, user_id)
VALUES (1, 'STANDARD', '2020-09-09', '2020-10-09', 'IN_ORDER', 1);
INSERT INTO application(person_amount, apartment_type, arrival_date, leaving_date, application_state, user_id)
VALUES (1, 'LUX', '2020-10-01', '2020-10-11', 'APPROVED', 2);
INSERT INTO application(person_amount, apartment_type, arrival_date, leaving_date, application_state, user_id)
VALUES (1, 'SKY_WALKER', '2020-12-22', '2021-01-05', 'DENIED', 3);
INSERT INTO application(person_amount, apartment_type, arrival_date, leaving_date, user_id)
VALUES (1, 'SKY_WALKER', '2020-12-22', '2021-02-05', 1);


INSERT INTO application_room(application_id, room_id)
VALUES (1, 1);
INSERT INTO application_room(application_id, room_id)
VALUES (1, 2);
INSERT INTO application_room(application_id, room_id)
VALUES (2, 1);
INSERT INTO application_room(application_id, room_id)
VALUES (3, 3);
INSERT INTO application_room(application_id, room_id)
VALUES (4, 3);

