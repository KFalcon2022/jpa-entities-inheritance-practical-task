create table adult_person(
    id                  uuid            primary key,
    first_name          varchar(100)    not null,
    last_name           varchar(100)    not null,
    birth_date          timestamp       not null,
    gender              varchar(10)     not null,
    address             varchar(255)    not null,
    created             timestamp       not null,
    updated             timestamp       not null,
    monthly_income      decimal         not null,
    job                 varchar(255)
);

create table child(
    id                  uuid            primary key,
    first_name          varchar(100)    not null,
    last_name           varchar(100)    not null,
    birth_date          timestamp       not null,
    gender              varchar(10)     not null,
    address             varchar(255)    not null,
    created             timestamp       not null,
    updated             timestamp       not null,
    fk_father           uuid            not null references adult_person(id),
    fk_mother           uuid            not null references adult_person(id)
);
