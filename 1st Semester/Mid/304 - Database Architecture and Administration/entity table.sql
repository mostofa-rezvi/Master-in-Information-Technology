-- create new profile or schema
connect / as sysdba;

create user rezvi identified by hr account unlock;

grant all privileges to rezvi;

connect rezvi;
hr

-- Create and Drop table with insert values
-- Customer 01
drop table customer;
create table customer (
    customer_name varchar2(15),
    customer_street varchar2(15),
    customer_city varchar2(15),
    constraint customer_name_pk primary key (customer_name)
);
insert into customer values ('Adams', 'Spring', 'Pittsfield');
insert into customer values ('Brooks', 'Senator', 'Brooklyn');
insert into customer values ('Curry', 'North', 'Rye');
insert into customer values ('Glenn', 'Sand Hill', 'Woodside');
insert into customer values ('Green', 'Walnut', 'Stamford');
insert into customer values ('Hayes', 'Main', 'Harrison');
insert into customer values ('Johnson', 'Alma', 'Palo Alto');
insert into customer values ('Jones', 'Main', 'Harrison');
insert into customer values ('Lindsay', 'Park', 'Pittsfield');
insert into customer values ('Smith', 'North', 'Rye');
insert into customer values ('Turner', 'Putnam', 'Stamford');
insert into customer values ('Williams', 'Nassau', 'Princeton');



-- Branch 02
drop table branch;
create table branch (
    branch_name varchar2(15),
    branch_city varchar2(15),
    assets number(10),
    constraint branch_name_pk primary key (branch_name)
);
insert into branch values ('Brighton', 'Brooklyn', 7100000);
insert into branch values ('Downtown', 'Brooklyn', 9000000);
insert into branch values ('Mianus', 'Horseneck', 400000);
insert into branch values ('North Town', 'Rye', 3700000);
insert into branch values ('Perryridge', 'Horseneck', 1700000);
insert into branch values ('Pownal', 'Bennington', 300000);
insert into branch values ('Redwood', 'Palo Alto', 2100000);
insert into branch values ('Round Hill', 'Horseneck', 8000000);



-- Account 03
drop table account;
create table account (
    account_number varchar2(10),
    branch_name varchar2(15),
    balance number(10),
    constraint account_number_pk primary key (account_number),
    constraint branch_name_fk foreign key (branch_name) references branch (branch_name)
);
insert into account values ('A-101', 'Downtown', 500);
insert into account values ('A-102', 'Perryridge', 400);
insert into account values ('A-201', 'Brighton', 900);
insert into account values ('A-215', 'Mianus', 700);
insert into account values ('A-217', 'Brighton', 750);
insert into account values ('A-222', 'Redwood', 700);
insert into account values ('A-305', 'Round Hill', 350);



-- Loan 04
drop table loan;
create table loan (
    loan_number varchar2(10),
    branch_name varchar2(15),
    amount number(15),
    constraint loan_number_pk primary key (loan_number),
    constraint branch_name_loan_fk foreign key (branch_name) references branch (branch_name)
);
insert into loan values ('L-11', 'Round Hill', 900);
insert into loan values ('L-14', 'Downtown', 1500);
insert into loan values ('L-15', 'Perryridge', 1500);
insert into loan values ('L-16', 'Perryridge', 1300);
insert into loan values ('L-17', 'Downtown', 1000);
insert into loan values ('L-23', 'Redwood', 2000);
insert into loan values ('L-93', 'Mianus', 500);



-- Depositor 05
drop table depositor;
create table depositor (
    customer_name varchar2(15),
    account_number varchar2(10),
    constraint customer_account_pk primary key (customer_name, account_number),
    constraint customer_name_fk foreign key (customer_name) references customer (customer_name),
    constraint account_number_fk foreign key (account_number) references account (account_number)
);
insert into depositor values ('Hayes', 'A-102');
insert into depositor values ('Johnson', 'A-101');
insert into depositor values ('Johnson', 'A-201');
insert into depositor values ('Jones', 'A-217');
insert into depositor values ('Lindsay', 'A-222');
insert into depositor values ('Smith', 'A-215');
insert into depositor values ('Turner', 'A-305');



-- Borrower 06
drop table borrower;
create table borrower (
    customer_name varchar2(15),
    loan_number varchar2(10),
    constraint customer_loan_pk primary key (customer_name, loan_number),
    constraint customer_name_borrow_fk foreign key (customer_name) references customer (customer_name),
    constraint loan_number_fk foreign key (loan_number) references loan (loan_number)
);
insert into borrower values ('Adams', 'L-16');
insert into borrower values ('Curry', 'L-93');
insert into borrower values ('Hayes', 'L-15');
insert into borrower values ('Jackson', 'L-14');
insert into borrower values ('Jones', 'L-17');
insert into borrower values ('Smith', 'L-11');
insert into borrower values ('Smith', 'L-23');
insert into borrower values ('Williams', 'L-17');



commit;