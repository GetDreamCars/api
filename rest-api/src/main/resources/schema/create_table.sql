CREATE TABLE advert
(
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    amount          INTEGER,
    door_count      INTEGER,
    engine_capacity INTEGER,
    engine_power    INTEGER,
    mileage         INTEGER,
    manufacture_year INTEGER,
    image_collection_id INTEGER,
    created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    valid_to        TIMESTAMP,
    body_type       VARCHAR(255),
    brand           VARCHAR(255),
    city            VARCHAR(255),
    color           VARCHAR(255),
    description     VARCHAR(255),
    first_name      VARCHAR(255),
    generation      VARCHAR(255),
    gross_net       VARCHAR(255),
    last_name       VARCHAR(255),
    model           VARCHAR(255),
    phone_number    VARCHAR(255),
    title           VARCHAR(255),
    version         VARCHAR(255),
    video           VARCHAR(255),
    vin             VARCHAR(255),
    advertiser_type VARCHAR(50),
    currency        VARCHAR(10),
    fuel_type       VARCHAR(20),
    gearbox         VARCHAR(20),
    status          VARCHAR(20)
);

CREATE TABLE image_collection (
    id BIGINT AUTO_INCREMENT PRIMARY KEY
);

CREATE TABLE image (
   id BIGINT AUTO_INCREMENT PRIMARY KEY,
   url VARCHAR(255) NOT NULL,
   image_collection_id BIGINT,
   FOREIGN KEY (image_collection_id) REFERENCES image_collection(id) ON DELETE CASCADE
);

ALTER TABLE advert
ADD FOREIGN KEY (image_collection_id) REFERENCES image_collection(id);