/*テーブル作成*/
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

/*
create table emojicode (
<<<<<<< HEAD
  keyword Text not null,
  emoji_code  Text not null
);*/

/*初期データ挿入*/
=======
  keyword serial primary key,
  emoji_code
);


insert into users(name, mail, filterlevel) values
  ('ichigo.chocomint', 'hoge@hoge.com', 1),
  ('banana.chocomint', 'hogehoge@hoge.com', 2),
  ('pinapple.chocomint', 'hogehogehoge@hoge.com', 3)
;


>>>>>>> aee0b171f30a59de419941fd6741b58d34595557
insert into users(name, age) values
  ('ichigo.chocomint', 99),
  ('banana.chocomint', 98),
  ('pinapple.chocomint', 97)
;
<<<<<<< HEAD

/*
insert into emojicode(keyword, emoji_code) values
  ("楽しい", "&#x1f600;"),
  ("悲しい", "&#x1f923;"),
;初期データ挿入*/
=======
>>>>>>> aee0b171f30a59de419941fd6741b58d34595557
