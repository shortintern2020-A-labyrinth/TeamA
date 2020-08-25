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
  contents varchar(100)
);

create table talk (
  id serial primary key,
  userid INTEGER not null,
  friendid INTEGER not null,
  contents varchar(30),
  create_at timestamp
);

create table requestfriend (
  id serial primary key,
  userid INTEGER not null,
  requestfriendid INTEGER not null,
  contents varchar(30),
  create_at timestamp
);

create table emojicode (
  keyword Text not null,
  emoji_code  Text not null
);

insert into users(name, mail, filterlevel) values
  ('ichigo.chocomint', 'hoge@hoge.com', 1),
  ('banana.chocomint', 'hogehoge@hoge.com', 2),
  ('pinapple.chocomint', 'hogehogehoge@hoge.com', 3)
;

insert into emojicode(keyword, emoji_code) values
  ('radio', ':radio:'),
  ('movies', ':movie_camera:'),
  ('bowling',':bowling:'),
  ('bank',':bank:')
;

insert into talk(userid, friendid,contents,create_at) values
  (100, 101,'Hello World !', current_timestamp),
  (101,100, 'Hi !', current_timestamp),
  (101,100,'See you.','2020-08-25 10:23:23')
;

insert into emolog(userid,friendid,create_at,contents) values
  (101,100,'2020-08-25 10:23:23',':grinning: :smiley: :wink: :radio: :credit_card:'),
  (100,101,'2020-08-25 10:23:23',':wink: :radio: :credit_card: :grinning: :smiley:')
;



