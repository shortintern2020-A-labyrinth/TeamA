create table users (
  id serial primary key,
  name varchar(30) not null,
  mail varchar(50) not null,
  filterlevel smallint
);

create table friend (
  id serial primary key,
  userid INTEGER not null,
  friendid INTEGER not null,
  name varchar(30) not null,
  latestemolog varchar(30)
);

create table emolog (
  id serial primary key,
  userid INTEGER not null,
  friendid INTEGER not null,
  create_at timestamp,
  contents varchar(30)
);

create table talk (
  id serial primary key,
  userid INTEGER not null,
  friendid INTEGER not null,
  contents varchar(30)
  create_at timestamp,
);

create table requestfriend (
  id serial primary key,
  userid INTEGER not null,
  requestfriendid INTEGER not null,
  contents varchar(30)
  create_at timestamp,
);




insert into users(name, mail, filterlevel) values
  ('ichigo.chocomint', 'hoge@hoge.com', 1),
  ('banana.chocomint', 'hogehoge@hoge.com', 2),
  ('pinapple.chocomint', 'hogehogehoge@hoge.com', 3)
;

