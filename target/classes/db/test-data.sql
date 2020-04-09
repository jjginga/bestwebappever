DELETE FROM customer;
INSERT INTO customer(ID, CREATIONTIME, UPDATETIME, VERSION, FIRSTNAME, LASTNAME, EMAIL, PHONE) VALUES
  (1, TIMESTAMP '2017-10-10 08:45:56.468', TIMESTAMP '2017-10-10 08:45:56.468', 0, 'Joel', 'Ginga', 'mail@gmail.com', '777888'),
  (2, TIMESTAMP '2017-10-10 08:45:56.481', TIMESTAMP '2017-10-10 08:45:56.481', 0, 'Ana', 'Dorotea', 'mail@gmail.com', '777888'),
  (3, TIMESTAMP '2017-10-10 08:45:56.482', TIMESTAMP '2017-10-10 08:45:56.482', 0, 'André', 'Raja', 'mail@gmail.com', '777888'),
  (4, TIMESTAMP '2017-10-10 08:45:56.482', TIMESTAMP '2017-10-10 08:45:56.482', 0, 'Franchico', 'Abrantes', 'mail@gmail.com', '777888')
  (5, TIMESTAMP '2017-10-10 08:45:56.482', TIMESTAMP '2017-10-10 08:45:56.482', 0, 'João', 'Matias', 'mail@gmail.com', '777888');

DELETE FROM specimen;
INSERT INTO specimen(ID, CREATIONTIME, UPDATETIME, VERSION, SPECIMENNUMBER, NAME, EMAIL, DESCRIPTION, PHONE, CUSTOMER_ID) VALUES
  (1, TIMESTAMP '2017-10-10 08:45:56.468', TIMESTAMP '2017-10-10 08:45:56.468', 1, 3, 'Sergio Gouveia', 'sergio@gmail.com', 'My colleague Sergio from A/C', '777888', 1),
  (2, TIMESTAMP '2017-10-10 08:45:56.468', TIMESTAMP '2017-10-10 08:45:56.468', 1, 5, 'Bruno Ferreira', 'bruno@gmail.com', 'My colelague Bruno from A/C', '777888', 1);
