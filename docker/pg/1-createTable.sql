create table users (
  id serial primary key,
  name varchar(30) not null,
  mail varchar(50) not null,
  filterlevel smallint
);

insert into users(name, mail, filterlevel) values
  ('ichigo.chocomint', 'hoge@hoge.com', 1),
  ('banana.chocomint', 'hogehoge@hoge.com', 2),
  ('pinapple.chocomint', 'hogehogehoge@hoge.com', 3)
;

create table sample (
  id serial primary key,
  name varchar(30) not null,
  mail varchar(50) not null,
  filterlevel smallint
);

insert into sample(name, mail, filterlevel) values
  ('ichigo.chocomint', 'hoge@hoge.com', 1),
  ('banana.chocomint', 'hogehoge@hoge.com', 2),
  ('pinapple.chocomint', 'hogehogehoge@hoge.com', 3)
;

create table friend (
  id serial primary key,
  userid INTEGER not null,
  name varchar(30) not null,
  latestemolog varchar(30),
  updated_at timestamp
);

insert into friend(userid, name, latestemolog, updated_at) values
  (123, 'ichigo.chocomint', '🍎🍋🍇', 2020-08-24 16:24:29.353806),
  (456, 'banana.chocomint', '🍈🍓🍌', 2020-08-25 16:24:29.353806),
  (789, 'pinapple.chocomint', '🍊🥝', 2020-08-26 16:24:29.353806)
;

create table emolog (
  id serial primary key,
  userid INTEGER not null,
  friendid INTEGER not null,
  created_at timestamp,
  contents varchar(30)
);

insert into emolog(userid, friendid, created_at, contents) values
    (123, 456, 2020-08-26 16:24:29.353806, U+1F600)
;

create table talk (
  id serial primary key,
  userid INTEGER not null,
  friendid INTEGER not null,
  contents varchar(30),
  create_at timestamp
);

insert into talk(userid, friendid, contents, create_at) values
    (123, 456, "こんにちは", 2020-08-26 16:24:29.353806)
;

create table requestfriend (
  id serial primary key,
  userid INTEGER not null,
  requestfriendid INTEGER not null,
  contents varchar(30),
  create_at timestamp
);

insert table requestfriend() values
()
;

create table emojicode (
  keyword serial primary key,
  emoji_code varchar(30)
);

