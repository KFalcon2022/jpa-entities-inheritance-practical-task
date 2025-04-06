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
    updated             timestamp       not null,
    monthly_income      decimal         CHECK (type != 'ADULT' or monthly_income is not null),
    job                 varchar(255),
    fk_father           uuid            references person(id) CHECK (type != 'CHILD' or fk_father is not null),
    fk_mother           uuid            references person(id) CHECK (type != 'CHILD' or fk_mother is not null)
);
