create table if not exists hibernate_sequence
(
    next_val bigint
);
insert into hibernate_sequence
values (1);
create table if not exists user
(
    id                         bigint       not null,
    email                      varchar(32)  not null,
    first_name                 varchar(32)  not null,
    is_account_non_expired     bit          not null,
    is_account_non_locked      bit          not null,
    is_credentials_non_expired bit          not null,
    is_enabled                 bit          not null,
    last_name                  varchar(32)  not null,
    password                   varchar(255) not null,
    token                      varchar(255),
    token_expiration_date      datetime(6),
    primary key (id)
    );
create table if not exists user_role
(
    user_id bigint not null,
    roles   varchar(255)
    );
create table if not exists vacancy
(
    id                bigint       not null,
    company_name      varchar(32)  not null,
    expected_salary   integer      not null,
    link_to_vacancy   varchar(255) not null,
    position          varchar(32)  not null,
    recruiter_contact varchar(255) not null,
    status_update     date         not null,
    vacancy_status    varchar(255),
    user_id           bigint,
    primary key (id)
    );
alter table user
    add constraint UK_ob8kqyqqgmefl0aco34akdtpe unique (email);
alter table user
    add constraint UK_kiqfjabx9puw3p1eg7kily8kg unique (password);
alter table user_role
    add constraint FK859n2jvi8ivhui0rl0esws6o foreign key (user_id) references user (id);
alter table vacancy
    add constraint FKe1xylfqo4n5y24qxvu5emc8l9 foreign key (user_id) references user (id);