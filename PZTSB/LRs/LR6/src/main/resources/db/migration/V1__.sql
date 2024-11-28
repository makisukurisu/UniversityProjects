CREATE TABLE city
(
    id         UUID NOT NULL,
    name       VARCHAR(255),
    created_at TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_city PRIMARY KEY (id)
);

CREATE TABLE flight
(
    id              UUID    NOT NULL,
    departs_from_id UUID,
    arrives_to_id   UUID,
    departure_time  TIMESTAMP WITHOUT TIME ZONE,
    arrival_time    TIMESTAMP WITHOUT TIME ZONE,
    price           INTEGER NOT NULL,
    flight_number   VARCHAR(255),
    created_at      TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_flight PRIMARY KEY (id)
);

CREATE TABLE ticket
(
    id         UUID NOT NULL,
    user_id    UUID,
    flight_id  UUID,
    pnr        VARCHAR(255),
    created_at TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_ticket PRIMARY KEY (id)
);

CREATE TABLE "user"
(
    id              UUID NOT NULL,
    first_name      VARCHAR(255),
    last_name       VARCHAR(255),
    username        VARCHAR(255),
    created_at      TIMESTAMP WITHOUT TIME ZONE,
    hashed_password VARCHAR(255),
    CONSTRAINT pk_user PRIMARY KEY (id)
);

ALTER TABLE flight
    ADD CONSTRAINT FK_FLIGHT_ON_ARRIVESTO FOREIGN KEY (arrives_to_id) REFERENCES city (id);

ALTER TABLE flight
    ADD CONSTRAINT FK_FLIGHT_ON_DEPARTSFROM FOREIGN KEY (departs_from_id) REFERENCES city (id);

ALTER TABLE ticket
    ADD CONSTRAINT FK_TICKET_ON_FLIGHT FOREIGN KEY (flight_id) REFERENCES flight (id);

ALTER TABLE ticket
    ADD CONSTRAINT FK_TICKET_ON_USER FOREIGN KEY (user_id) REFERENCES "user" (id);