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
  
create table customer (
  `id` bigint  primary key not null auto_increment,
  `name` varchar(255) not null,
  `location` varchar(255) not null,
  `tax_number` int not null,
  `owner` varchar(255) not null,
  `department_id` bigint not null,
  foreign key (department_id) references maindb.department(id);

create table provider (
  `id` bigint primary key not null auto_increment,
  `name` varchar(255) not null,
  `tax_number` int not null,
  `location` varchar(45) not null,
  `owner` varchar(45) null,
  `department_id` bigint not null,
  foreign key (department_id) references maindb.department(id);

create table material (
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
  `workday` date not null;

create table employee_task (
  `id` bigint primary key not null auto_increment,
  `employee_id` bigint not null,
  `task_id` bigint not null,
  foreign key (employee_id) references maindb.employee(id),
  foreign key (task_id) references maindb.task(id));

CREATE TABLE `maindb`.`role` (
  `id` BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(150) NOT NULL);

CREATE TABLE `maindb`.`employee_role` (
  `id` BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `employee_id` BIGINT NOT NULL,
  `role_id` BIGINT NOT NULL,
  FOREIGN KEY (employee_id) references maindb.employee(id),
  FOREIGN KEY (role_id) references maindb.role(id));
