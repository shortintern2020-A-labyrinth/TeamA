/*テーブル作成*/
create table users (
                       id serial primary key,
                       name varchar(30) not null,
                       mail varchar(50) not null,
                       filterlevel smallint
);

create table sample (
  friendid serial primary key,
                        name varchar(30) not null,
                        mail varchar(50) not null,
                        filterlevel smallint
);

create table friend (
                        id serial primary key,
                        userid INTEGER not null,
                        friendid INTEGER not null,
                        name varchar(30) not null,
                        latestemolog varchar(30),
                        updated_at timestamp,
                        lasttweetid INTEGER DEFAULT 0
);

create table emolog (
                        id serial primary key,
                        userid INTEGER not null,
                        friendid INTEGER not null,
                        created_at timestamp,
                        contents varchar(30)
);

create table chat (
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
                               create_at timestamp
);

/* BEGIN:nakamura */
insert into users(name, mail, filterlevel) values
('ichigo.chocomint', 'ichigo@hoge.com', 1),
('banana.chocomint', 'banana@hoge.com', 2),
('pinapple.chocomint', 'pineapple@hoge.com', 3),
('apple.chocomint', 'apple@hoge.com', 4),
('lemon.chocomint', 'lemon@hoge.com', 5)
;

insert into friend(userid, name, latestemolog, updated_at) values
(123, 'ichigo.chocomint', '🍎🍋🍇', '2001-01-01 01:01:01'),
(456, 'banana.chocomint', '🍈🍓🍌', '2002-02-02 02:02:02'),
(789, 'pinapple.chocomint', '🍊🥝🍎', '2003-03-03 03:03:03'),
(321, 'apple.chocomint', '🍋🍇🍈', '2004-04-04 04:04:04'),
(654, 'lemon.chocomint', '🍓🍌🍊', '2005-05-05 05:05:05'),
(1919, 'CNN', '🦍🍗🚽', '2005-05-05 05:05:05'),
(810, 'samurai_badass', '🌮🌾🥢', '2005-05-05 05:05:05'),
(114, 'capital_noodle', '💃💃💃', '2005-05-05 05:05:05'),
(514, 'roland_0fficial', '😲😲😲', '2005-05-05 05:05:05')
;

insert into emolog(userid, friendid, created_at, contents) values
(1, 2, '2006-06-06 06:06:06', '⛰🌋🗻'),
(2, 1, '2007-07-07 07:07:07', '🏘🏠🏡'),
(1, 3, '2008-08-08 08:08:08', '🐒🦍🐒'),
(3, 1, '2009-09-09 09:09:09', '⭐️🌟⭐️'),
(2, 3, '2010-10-10 10:10:10', '🐈🐈🐈')
;

insert into chat(userid, friendid, contents, create_at) values
(123, 456, 'Hello', '2011-11-11 11:11:11'),
(789, 123, 'Good morning', '2012-12-12 12:12:12'),
(456, 789, 'Good afternoon', '2013-01-13 01:13:13'),
(456, 123, 'Good evening', '2014-02-14 02:14:14'),
(123, 789, 'Good night', '2015-03-15 03:15:15')

;

insert into requestfriend(userid, requestfriendid, create_at) values
(12, 34, '2016-04-16 04:16:16'),
(56, 78, '2017-05-17 05:17:17'),
(91, 23, '2018-06-18 06:18:18'),
(45, 67, '2019-07-19 07:19:19'),
(89, 12, '2020-08-20 08:20:20')
;

insert into users(name, mail, filterlevel) values
  ('ichigo.chocomint', 'hoge@hoge.com', 1),
  ('banana.chocomint', 'hogehoge@hoge.com', 2),
  ('pinapple.chocomint', 'hogehogehoge@hoge.com', 3)
;

/* END:nakamura */

