
drop table if exists cr_adress_person;
drop table if exists cr_person;
drop table if exists cr_adress;
drop table if exists cr_district;
drop table if exists cr_street;





create table cr_district (
        district_code integer not null,
        primary key(district_code),
        district_name varchar(100)
);

create table cr_street (
        street_code integer not null,
        primary key(street_code),
        street_name varchar(100)
);

create table cr_adress(
        adress_id serial,
        district_code integer not null,
        street_code integer not null,
        building varchar(10) not null,
        extension varchar(10),
        appartment varchar(10),
        primary key(adress_id),
        foreign key (district_code) references cr_district(district_code) on delete restrict,
        foreign key (street_code) references cr_street(street_code) on delete restrict
);

create table cr_person(
        person_id serial,
        sur_name varchar(100) not null,
        given_name varchar(100) not null,
        patronymic varchar(100) not null,
        date_of_birth date not null,
        passport_seria varchar(10),
        passport_number varchar (10),
        passport_date date,
        certificate_number varchar (10),
        certificate_date date,
        primary key (person_id)
);

create table cr_adress_person
(
    person_adress_id serial,
    adress_id integer  not null,
    person_id integer not null,
    start_date date not null ,
    end_date date,
    temporal boolean DEFAULT false,
    primary key(person_adress_id),
    foreign key (adress_id) references cr_adress(adress_id) on delete restrict,
    foreign key (person_id) references cr_person(person_id) on delete restrict
);