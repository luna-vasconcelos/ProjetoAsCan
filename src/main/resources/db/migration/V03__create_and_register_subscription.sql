CREATE TABLE subscription(

  id int auto_increment primary key,
  user_id int,
  status_id int,
  created_at timestamp,
  updated_at timestamp,
  foreign key(user_id) references USER(id),
  foreign key(status_id) references Status(id)
);
