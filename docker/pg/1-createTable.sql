/*テーブル作成*/
create table users (
  id serial primary key,
  name varchar not null,
  age integer not null
);

/*
create table emojicode (
  keyword Text not null,
  emoji_code  Text not null
);*/

/*初期データ挿入*/
insert into users(name, age) values
  ('ichigo.chocomint', 99),
  ('banana.chocomint', 98),
  ('pinapple.chocomint', 97)
;

/*
insert into emojicode(keyword, emoji_code) values
  ("楽しい", "&#x1f600;"),
  ("悲しい", "&#x1f923;"),
;初期データ挿入*/