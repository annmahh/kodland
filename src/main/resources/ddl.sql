drop table if exists person cascade;
drop table if exists employee cascade;
drop table if exists candidate cascade;
drop table if exists job cascade;
drop table if exists department cascade;


create table if not exists person (
    id integer not null,
    name varchar(255),
    gender boolean,
    age integer not null,
    jobId integer not null,
    primary key (id)
);

create table if not exists employee (
    id integer not null,
    name varchar(255),
    gender boolean,
    age integer not null,
    jobId integer not null,
    departmentId integer not null,
    dateStart varchar(255),
    dateEnd varchar(255),
    primary key (id)
    foreign key (person) references Person(id) on delete cascade
);

create table if not exists candidate (
    id integer not null,
    name varchar(255),
    gender boolean,
    age integer not null,
    jobId integer not null,
    testingResult boolean,
    interviewingResult boolean,
    offerResult boolean,
    primary key (id)
    foreign key (person) references Person(id) on delete cascade
);

create table if not exists job (
    id integer not null,
    grade varchar(255),
    area varchar(255),
    position varchar(255),
    salary integer not null,
    primary key (id)
);

create table if not exists department (
    id integer not null,
    name varchar(255),
    primary key (id)
);