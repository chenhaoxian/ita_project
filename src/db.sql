create table person(id number primary key,cname varchar2(10),birth number(8),tel number(11),departId number(4),salary number(6));
insert into person(id,cname,birth,tel,departId,salary) values (Sun.nextval,'Sun_Hy',19950603,13726259726,0101,100000);

select * from person;

create sequence department;
create table depart(id number primary key, dname nvarchar2(10), city nvarchar2(10);

select * from depart;
