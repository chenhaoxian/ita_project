create table person(id number primary key,cname varchar2(10),birth number(8),tel number(11),departId number(4),salary number(6));
insert into person(id,cname,birth,tel,departId,salary) values (Sun.nextval,'Sun_Hy',19950603,13726259756,7,100000);

select * from person;

create sequence department;
create table depart(id number primary key, dname nvarchar2(10), city nvarchar2(10);
insert into depart(id,dname,city) values (department.nextval,'VEC','GA');
select * from depart;

select * from person left join depart on person.departId= depart.id where depart.id=7;