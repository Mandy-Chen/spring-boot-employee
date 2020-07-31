create table employee(
    id int not null auto_increment,
    name varchar(45),
    gender varchar(45),
    age int ,
    salary int ,
    primary key (id),
   foreign key em_com(companyId) references company(companyId)
)