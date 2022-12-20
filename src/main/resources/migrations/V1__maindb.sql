create table user (
  `id` bigint  primary key not null auto_increment,
  `firstname` varchar(100) not null,
  `lastname` varchar(100) not null,
  `email` varchar(255) not null,
  `login` varchar(255) not null,
  `password` varchar(255) not null,
  unique index email_unique (`email` asc) visible,
  unique index login_unique (`login` asc) visible);
 
create table department (
  `id` bigint primary key not null auto_increment,
  `name` varchar(255) not null,
  `location` varchar(255) not null;
  
create table employee (
  `id` bigint primary key not null auto_increment,
  `name` varchar(45) not null,
  `user_id` bigint not null,
  `department_id` bigint not null,
  foreign key (user_id) references maindb.user (id),
  foreign key (department_id) references maindb.department(id));
  
create table material_storage (
  `id` bigint primary key not null auto_increment,
  `name` varchar(255) not null,
  `property` varchar(45) null,
  `value` int not null,
  `department_id` bigint not null,
  foreign key (department_id) references maindb.department(id));

create table product (
  `id` bigint primary key not null auto_increment,
  `name` varchar(45) not null,
  `produce_time` int not null,
  `department_id` bigint not null,
  foreign key (department_id) references maindb.department(id));
  
create table task (
  `id` bigint primary key not null auto_increment,
  `name` varchar(150) not null,
  `description` varchar(150) not null,
  `dead_line` datetime(6) not null,
  `priority` int null,
  `workday` date not null,
  `flag` enum('DONE', 'IN_PROCESS', 'CONFIRMED', 'FAILED') null;

create table employee_task (
  `id` bigint primary key not null auto_increment,
  `employee_id` bigint not null,
  `task_id` bigint not null,
  foreign key (employee_id) references maindb.employee(id),
  foreign key (task_id) references maindb.task(id));

create table `maindb`.`role` (
  `id` bigint primary key not null AUTO_INCREMENT,
  `name` varchar(150) not null);

create table `maindb`.`employee_role` (
  `id` bigint primary key not null AUTO_INCREMENT,
  `employee_id` bigint not null,
  `role_id` bigint not null,
  foreign key (employee_id) references maindb.employee(id),
  foreign key (role_id) references maindb.role(id));

create table `maindb`.`temp_material` (
  `id` bigint primary key not null auto_increment,
  `name` varchar(255) not null,
  `property` varchar(45) null,
  `value` int not null,
  `task_id` bigint not null,
  foreign key (task_id) references maindb.task(id));

  create table `maindb`.`bid` (
  `id` bigint primary key not null auto_increment,
  `value` int not null,
  `company_id` bigint not null,
  `contract_type` enum('BUY', 'SELL') not null,
  `state` enum('IN_PROCESS', 'CONFIRMED', 'DONE', 'FAILED') not null default('IN_PROCESS'),
  `contract_day` datetime not null,
  foreign key (company_id) references maindb.company(id));

CREATE TABLE company (
  `id` BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `tax_number` INT NOT NULL,
  `location` VARCHAR(45) NOT NULL,
  `owner` VARCHAR(45) NULL,
  `role` enum('CUSTOMER', 'PROVIDER') not null);

create table material_bid (
`id` bigint primary key not null auto_increment,
`material_id` bigint not null,
`bid_id` bigint not null,
foreign key (material_id) references material_storage(id),
foreign key (bid_id) references bid(id));

create table product_bid (
`id` bigint primary key not null auto_increment,
`product_id` bigint not null,
`bid_id` bigint not null,
foreign key (product_id) references product(id),
foreign key (bid_id) references bid(id));

alter table company
add column email varchar(250) not null;

alter table company
add column deleted boolean default false after role;
