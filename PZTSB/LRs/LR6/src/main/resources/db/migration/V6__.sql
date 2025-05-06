ALTER TABLE role
    ADD updated_at TIMESTAMP WITHOUT TIME ZONE;

ALTER TABLE role
    ADD CONSTRAINT uc_0afdc763cb0f6a4c6e89251e5 UNIQUE (id, name);

ALTER TABLE user_roles
    ADD CONSTRAINT fk_userol_on_role FOREIGN KEY (roles_id) REFERENCES role (id);

ALTER TABLE user_roles
    ADD CONSTRAINT fk_userol_on_user FOREIGN KEY (user_id) REFERENCES "user" (id);