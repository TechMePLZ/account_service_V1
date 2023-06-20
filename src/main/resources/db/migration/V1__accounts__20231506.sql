create table accounts (
 account_id serial primary key,
 username varchar (128) not null  unique,
 balance numeric (666) ,
 email varchar  (132) not null,
 created_at timestamp
 );