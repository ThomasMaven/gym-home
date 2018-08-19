CREATE TABLE account (
  name        CHARACTER VARYING(50) PRIMARY KEY         NOT NULL,
  password    CHARACTER VARYING(255)                    NOT NULL,
  created     TIMESTAMP WITHOUT TIME ZONE DEFAULT now() NOT NULL,
  updated     TIMESTAMP WITHOUT TIME ZONE DEFAULT now() NOT NULL,
  status      INTEGER DEFAULT 0                         NOT NULL,
  email       CHARACTER VARYING(50)
);

CREATE TABLE role (
  name        CHARACTER VARYING(50) PRIMARY KEY NOT NULL,
  permissions CHARACTER VARYING(255),
  modifiable  BOOLEAN               NOT NULL
);

CREATE TABLE account_role (
  account CHARACTER VARYING(50) NOT NULL,
  role    CHARACTER VARYING(50) NOT NULL
);

ALTER TABLE account_role ADD CONSTRAINT account_role_role_fkey FOREIGN KEY (role) REFERENCES role (name);
ALTER TABLE account_role ADD CONSTRAINT account_role_account_fkey FOREIGN KEY (account) REFERENCES account (name);

INSERT INTO account (name, password, created, updated) VALUES ('jpiecuch', '{noop}welcome', now(), now());
INSERT INTO role (name, modifiable) VALUES ('USER', false);
INSERT INTO account_role (account, role) VALUES ('jpiecuch', 'USER');