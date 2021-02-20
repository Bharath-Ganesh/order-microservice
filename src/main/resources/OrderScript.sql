create schema parcel_xyz;
use parcel_xyz;
/**
Address of the reciever could be stored in the user table with a role as user. 
*/
/**This could be replaced by an entity like address to store the data.
sender_phonenumber varchar(15),
receiver_phonenumbe
*/
create table Orders (
order_id integer AUTO_INCREMENT primary key,
height double NOT NULL ,
length double,
width double,
weight double NOT NULL ,
parcel_type double,
pick_up_point varchar(50),
shipping_address varchar(50),
/**Replace with date field.*/
order_date varchar(10), 
status varchar(1));


select * from Orders;
drop table Orders;