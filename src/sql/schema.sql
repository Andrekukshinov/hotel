
CREATE TABLE IF NOT EXISTS Role
(
    id   BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    role VARCHAR(20)        NOT NULL DEFAULT ('USER')
);

CREATE TABLE IF NOT EXISTS User
(
    id          BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    login       VARCHAR(25) UNIQUE NOT NULL,
    password    VARCHAR(50)        NOT NULL,
    is_disabled BOOLEAN DEFAULT FALSE,
    role_id     BIGINT             NOT NULL,
    FOREIGN KEY (role_id) REFERENCES Role (id)
);

CREATE INDEX authIndex on User(login, password);

CREATE TABLE IF NOT EXISTS Room
(
    id             BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    apartment_type ENUM ('STANDARD', 'BUSINESS', 'SKY_WALKER', 'LUX'),
    capacity       TINYINT            NOT NULL,
    room_number    INT UNIQUE         NOT NULL,
    is_available   BOOLEAN DEFAULT TRUE,
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
    application_state ENUM ('APPROVED', 'USER_REJECTED', 'IN_ORDER', 'DENIED', 'CANCELLED') DEFAULT ('IN_ORDER'),
    FOREIGN KEY (user_id) REFERENCES User (id)
);

ALTER TABLE application
    ADD room_id bigint DEFAULT NULL;
ALTER TABLE application
    ADD total_price int;
ALTER TABLE application
    ADD FOREIGN KEY (room_id) REFERENCES Room (id);
