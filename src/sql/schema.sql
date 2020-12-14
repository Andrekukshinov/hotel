

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
    room_number       INT     UNIQUE       NOT NULL,
    status         ENUM ('AVAILABLE', 'DISABLED', 'OCCUPIED'),
    price          DECIMAL            NOT NULL,
    picture_url    VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS Application
(
    id             BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    person_amount  TINYINT            NOT NULL,
    apartment_type ENUM ('STANDARD', 'BUSINESS', 'SKY_WALKER', 'LUX'),
    arrival_date   DATE               NOT NULL,
    leaving_date   DATE               NOT NULL,
    user_id        BIGINT             NOT NULL,
    application_state     ENUM ('IN_ORDER', 'APPROVED', 'DENIED') DEFAULT ('IN_ORDER'),
    FOREIGN KEY (user_id) REFERENCES User (id)
);

CREATE TABLE IF NOT EXISTS Application_Room
(
    id             BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    application_id BIGINT             NOT NULL,
    room_id        BIGINT             NOT NULL,
    FOREIGN KEY (application_id) REFERENCES Application (id),
    FOREIGN KEY (room_id) REFERENCES Room (id)
);
