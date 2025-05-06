CREATE TABLE role
(
    id   UUID NOT NULL,
    name VARCHAR(255),
    CONSTRAINT pk_role PRIMARY KEY (id)
);

CREATE TABLE user_roles
(
    user_id  UUID NOT NULL,
    roles_id UUID NOT NULL,
    CONSTRAINT pk_user_roles PRIMARY KEY (user_id, roles_id)
);

ALTER TABLE flight
    DROP COLUMN updated_at;