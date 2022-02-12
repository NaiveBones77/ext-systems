INSERT INTO cr_district (district_code, district_name)
VALUES (1, 'Выборгский');

INSERT INTO cr_street (street_code, street_name)
VALUES (1, 'Сампсоньевский проспект');

INSERT INTO cr_adress (district_code, street_code, building, extension, appartment)
VALUES (1, 1, '10', '2', '121');
INSERT INTO cr_adress (district_code, street_code, building, extension, appartment)
VALUES (1, 1, '271', null, '4');



INSERT INTO cr_person (sur_name, given_name, patronymic, date_of_birth,
                       passport_seria, passport_number, passport_date, certificate_number, certificate_date)
VALUES ('Васильев', 'Павел', 'Николаевич', '1995-03-18', '1234', '123456', '2015-04-11',
        null, null);

INSERT INTO cr_person (sur_name, given_name, patronymic, date_of_birth,
                       passport_seria, passport_number, passport_date, certificate_number, certificate_date)
VALUES ('Васильева', 'Ирина', 'Петровна', '1997-08-21', '4321', '654321', '2017-09-19',
        null, null);

INSERT INTO cr_person (sur_name, given_name, patronymic, date_of_birth,
                       passport_seria, passport_number, passport_date, certificate_number, certificate_date)
VALUES ('Васильева', 'Евгения', 'Павловна', '2016-01-11', null, null, null, '456123', '2016-01-21');

INSERT INTO cr_person (sur_name, given_name, patronymic, date_of_birth,
                       passport_seria, passport_number, passport_date, certificate_number, certificate_date)
VALUES ('Васильев', 'Александр', 'Павлович', '2018-10-24', null, null, null, '321654', '2018-11-09');


INSERT INTO cr_adress_person (adress_id, person_id, start_date, end_date, temporal)
VALUES (1, 1, '2014-10-12', null, false);

INSERT INTO cr_adress_person (adress_id, person_id, start_date, end_date)
VALUES (2, 2, '2014-10-12', null);

INSERT INTO cr_adress_person (adress_id, person_id, start_date, end_date)
VALUES (1, 3, '2016-02-05', null);

INSERT INTO cr_adress_person (adress_id, person_id, start_date, end_date)
VALUES (1, 4, '2018-12-11', null);