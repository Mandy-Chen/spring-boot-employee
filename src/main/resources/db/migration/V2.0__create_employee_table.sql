create table employee(
    id int not null auto_increment,
    name varchar(45),
    age int ,
    gender varchar(45),
    salary int ,
    companyId int ,
    foreign key (companyId) references company(companyId)

)