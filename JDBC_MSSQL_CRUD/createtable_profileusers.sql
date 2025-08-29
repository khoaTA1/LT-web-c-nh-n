create table profile_users (
	id int identity(1,1) primary key,
	username nvarchar(50) not null,
	password varchar(50) not null,
	email varchar(50) not null,
	fullname nvarchar(50),
	roleid int,
	phone varchar(50),
	date date,
)