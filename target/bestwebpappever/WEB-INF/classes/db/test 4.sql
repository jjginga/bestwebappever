<!-- TODO SQL INJECTION? -->
CREATE TABLE customer(
    id integer AUTO_INCREMENT,
    firstname CHAR(64),
    lastname CHAR(64),
    email CHAR(64),
    phone CHAR(64),
    primary key(id)
);

CREATE TABLE specimen(
    id integer AUTO_INCREMENT,
    specimennumber integer,
    name CHAR(64),
    email CHAR(64),
    description CHAR(64),
    phone CHAR(64),
    customer_id integer,
    foreign key (id) references customer(id)
);

INSERT INTO customer(ID, FIRSTNAME, LASTNAME, EMAIL, PHONE) VALUES
  (1, 'Joel', 'Ginga', 'mail@gmail.com', '777888'),
  (2, 'Ana', 'Dorotea', 'mail@gmail.com', '777888'),
  (3, 'André', 'Raja', 'mail@gmail.com', '777888'),
  (4, 'Franchico', 'Abrantes', 'mail@gmail.com', '777888'),
  (5, 'João', 'Matias', 'mail@gmail.com', '777888');

INSERT INTO specimen(ID, SPECIMENNUMBER, NAME, EMAIL, DESCRIPTION, PHONE, CUSTOMER_ID) VALUES
  (1, 3, 'Sergio Gouveia', 'sergio@gmail.com', 'My colleague Sergio from A/C', '777888', 1),
  (2, 5, 'Bruno Ferreira', 'bruno@gmail.com', 'My colelague Bruno from A/C', '777888', 1);

