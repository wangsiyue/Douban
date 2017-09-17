
drop table book
create table BookType(
	typeid int primary key auto_increment,
	typename varchar(500)
);
insert into BookType(typename) values('java');
insert into BookType(typename) values('html');
insert into BookType(typename) values('css');
insert into BookType(typename) values('js');
insert into BookType(typename) values('c++');
insert into BookType(typename) values('springmvc');
insert into BookType(typename) values('mysql');
insert into BookType(typename) values('redis');
select * from book
create table bookrank(
	rid int primary key auto_increment,
	ip varchar(400),
	bookid int,
	scores int
);

create table book(
	bookid int primary key auto_increment,
	ISBN varchar(100),
	name varchar(500),
	author varchar(500),
	price double,
	typeid int,
	pdfs varchar(5000),
	description varchar(5000)

);

