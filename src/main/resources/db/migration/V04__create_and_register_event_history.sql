create table EventHistory(

	id int auto_increment primary key,
    type varchar(100),
    subscription_id int,
    created_at timestamp,
    foreign key(subscription_id) references Subscription(id)
);
