create table users (
  id serial primary key,
  name varchar not null,
  age integer not null
);

insert into users(name, age) values
  ('ichigo.chocomint', 99),
  ('banana.chocomint', 98),
  ('pinapple.chocomint', 97)
;