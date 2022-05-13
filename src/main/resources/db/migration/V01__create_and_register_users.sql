CREATE TABLE user(
  id int primary key auto_increment,
  full_name varchar(200),
  created_at timestamp
);

insert into user (full_name) values ('Naruto');
insert into user (full_name) values ('Tanjiro');