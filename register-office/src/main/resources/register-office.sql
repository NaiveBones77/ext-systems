drop table if exists person;

create table person(
    person_id serial,
    first_name varchar(100),
    last_name varchar(100),
    primary key (person_id)
);