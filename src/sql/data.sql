INSERT INTO Role (id, role)
VALUES (1, 'ADMIN');
INSERT INTO Role ()
VALUES ();

INSERT INTO user(login, password, is_disabled, role_id)
VALUES ('admin', SHA1('pass'), false, 1);
INSERT INTO user(login, password, is_disabled, role_id)
VALUES ('billy', SHA1('random word'), false, 2);
INSERT INTO user(login, password, is_disabled, role_id)
VALUES ('harrington', SHA1('nico nico doga'), true, 2);
INSERT INTO user(login, password, is_disabled, role_id)
VALUES ('dieHard', SHA1('228'), false, 2);
INSERT INTO user(login, password, is_disabled, role_id)
VALUES ('Ronald', SHA1('322'), false, 2);
INSERT INTO user(login, password, is_disabled, role_id)
VALUES ('adminy', SHA1('pass'), false, 2);
INSERT INTO user(login, password, is_disabled, role_id)
VALUES ('bill', SHA1('random word'), false, 2);
INSERT INTO user(login, password, is_disabled, role_id)
VALUES ('harringtony', SHA1('nico nico doga'), true, 2);
INSERT INTO user(login, password, is_disabled, role_id)
VALUES ('dieHardy', SHA1('228'), false, 2);
INSERT INTO user(login, password, is_disabled, role_id)
VALUES ('Geronald', SHA1('322'), false, 2);
INSERT INTO user(login, password, is_disabled, role_id)
VALUES ('Roony', SHA1('228'), false, 2);
INSERT INTO user(login, password, role_id, is_disabled)
VALUES ('RayCartery', SHA1('322'), 2, false);


INSERT INTO room(apartment_type, room_number, capacity, price)
VALUES ('STANDARD', 202, 2, 150.2);
INSERT INTO room(apartment_type, room_number, capacity, price)
VALUES ('BUSINESS', 105, 1, 200.1);
INSERT INTO room(apartment_type, room_number, capacity, price)
VALUES ('LUX', 306, 4, 450.2);
INSERT INTO room(apartment_type, room_number, is_available, capacity, price)
VALUES ('STANDARD', 104, true, 5, 400.2);
INSERT INTO room(apartment_type, room_number, capacity, price)
VALUES ('SKY_WALKER', 101, 2, 150.2);
INSERT INTO room(apartment_type, room_number, capacity, price)
VALUES ('STANDARD', 106, 5, 400.2);
INSERT INTO room(apartment_type, room_number, capacity, price)
VALUES ('SKY_WALKER', 109, 2, 150.2);


INSERT INTO application(person_amount, apartment_type, arrival_date, leaving_date, application_state, user_id)
VALUES (1, 'STANDARD', '2020-09-09', '2020-10-09', 'IN_ORDER', 2);
INSERT INTO application(person_amount, apartment_type, arrival_date, leaving_date, application_state, user_id)
VALUES (1, 'STANDARD', '2020-09-09', '2020-10-09', 'CANCELLED', 4);
INSERT INTO application(person_amount, apartment_type, arrival_date, leaving_date, application_state, user_id)
VALUES (1, 'LUX', '2020-10-01', '2020-10-11', 'USER_REJECTED', 2);
INSERT INTO application(person_amount, apartment_type, arrival_date, leaving_date, application_state, user_id)
VALUES (1, 'SKY_WALKER', '2020-12-22', '2021-01-05', 'DENIED', 3);
INSERT INTO application(person_amount, apartment_type, arrival_date, leaving_date, user_id)
VALUES (1, 'SKY_WALKER', '2020-12-22', '2021-02-05', 3);
INSERT INTO application(person_amount, apartment_type, arrival_date, leaving_date, application_state, user_id)
VALUES (1, 'SKY_WALKER', '2020-12-22', '2021-02-05', 'USER_REJECTED', 4);
INSERT INTO application(person_amount, apartment_type, arrival_date, leaving_date, application_state, user_id)
VALUES (1, 'SKY_WALKER', '2020-12-22', '2021-02-05', 'DENIED', 4);
INSERT INTO application (person_amount, apartment_type, arrival_date, leaving_date, user_id)
VALUES (1, 'LUX', '2021-01-05', '2021-01-06', 4);
INSERT INTO application (person_amount, application_state, apartment_type, arrival_date, leaving_date, user_id, room_id,
                         total_price)
VALUES (1, 'APPROVED', 'LUX', '2021-01-01', '2021-01-06', 3, 2, 2345);
INSERT INTO application (person_amount, application_state, apartment_type, arrival_date, leaving_date, user_id, room_id,
                         total_price)
VALUES (1, 'APPROVED', 'LUX', '2021-01-04', '2021-01-10', 4, 1, 700);

INSERT INTO application(person_amount, apartment_type, arrival_date, leaving_date, application_state, user_id)
VALUES (1, 'STANDARD', '2020-09-09', '2020-10-09', 'USER_REJECTED', 3);
