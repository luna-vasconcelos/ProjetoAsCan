CREATE TABLE status(
  id int primary key auto_increment,
  status_name varchar(20)
);

insert into status (status_name) values ('Ativa');
insert into status (status_name) values ('Cancelada');