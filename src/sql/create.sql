CREATE TABLE IF NOT EXISTS User
(
    id          BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    login       VARCHAR(25) UNIQUE NOT NULL,
    pass        VARCHAR(50)        NOT NULL,
    is_disabled BIT                         DEFAULT 0,
    role     ENUM ('USER', 'ADMIN') DEFAULT ('USER')
);

CREATE TABLE IF NOT EXISTS Room
(
    id             BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    apartment_type ENUM ('standard', 'business', 'sky_walker', 'lux'),
    person_amount  TINYINT            NOT NULL,
    room_status      ENUM ('available', 'disabled', 'occupied'),
    room_price     DOUBLE             NOT NULL,
    picture_url    VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS Application
(
    id             BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    person_amount  TINYINT            NOT NULL,
    apartment_type ENUM ('standard', 'business', 'sky_walker', 'lux'),
    arrival_date      DATE NOT NULL,
    leaving_date      DATE NOT NULL,
    user_id     BIGINT            NOT NULL ,
    FOREIGN KEY (user_id) REFERENCES User (id)
);

CREATE TABLE IF NOT EXISTS User_Room
(
    id             BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    user_id     BIGINT            NOT NULL,
    room_id     BIGINT            NOT NULL,
    room_state ENUM ('payed', 'in_order', 'approved', 'denied'),
    FOREIGN KEY (user_id) REFERENCES User (id),
    FOREIGN KEY (room_id) REFERENCES Room (id)
);
