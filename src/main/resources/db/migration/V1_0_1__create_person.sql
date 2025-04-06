create table person
(
    id                  uuid            primary key,
    type                varchar(20)     not null,
    first_name          varchar(100)    not null,
    last_name           varchar(100)    not null,
    birth_date          timestamp       not null,
    gender              varchar(10)     not null,
    address             varchar(255)    not null,
    created             timestamp       not null,
    updated             timestamp       not null
);

create table adult_person(
    fk_person           uuid            not null references person(id),
    monthly_income      decimal         not null,
    job                 varchar(255)
);

create unique index uix_adult_person_fk_person on adult_person(fk_person);

create table child(
    fk_person           uuid            not null references person(id),
    fk_father           uuid            not null references person(id),
    fk_mother           uuid            not null references person(id)
);

create unique index uix_child_fk_person on child(fk_person);
