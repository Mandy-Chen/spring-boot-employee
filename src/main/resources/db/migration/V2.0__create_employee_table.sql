create table employee(
    id int not null auto_increment,
    name varchar(45),
    age int ,
    gender varchar(45),
    salary int ,
    company_id int ,
    foreign key (company_id) references company(company_id)

)