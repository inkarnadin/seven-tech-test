drop table if exists account;

create table account (
  id int auto_increment primary key,
  account_number varchar(250) not null,
  balance numeric not null
);

insert into account (account_number, balance) values
    ('S-564', 100.5),
    ('S-567', 120.55),
    ('S-009', 0.07),
    ('S-700', 2015.23);
