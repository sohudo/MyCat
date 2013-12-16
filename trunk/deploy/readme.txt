1 check Java Runtime Env is OK 
2 create datbase db1,db2,db3 in local mysql server and make sure mysql password of root is 123456
3 goto bin directory and run startup.bat
4 in command line ,run mysql client command  mysql -utest -ptest -hlocalhost -P8066 -DTESTDB
5 execute sql in mysql client

6 create company : create table company(id int not null primary key,name varchar(100));
7 insert data to company 
     insert into company(id,name) values(1,'hp');
     insert into company(id,name) values(2,'ibm');
     insert into company(id,name) values(3,'oracle');
   you will see those records are inserted to three sharding data nodes :db1¡¢db2 and db3 
   execute sql: 'explain insert into company(id,name) values(1,'hp');' you will see three route matched as following:
		 +-----------+---------------------------------------------+
		| DATA_NODE | SQL                                         |
		+-----------+---------------------------------------------+
		| dn1       | insert into company(id,name) values(1,'hp') |
		| dn2       | insert into company(id,name) values(1,'hp') |
		| dn3       | insert into company(id,name) values(1,'hp') |
		+-----------+---------------------------------------------+
   
1371



   because company is defined as a global table   
   
    
8 create customer: create table customer(id int not null primary key,name varchar(100),company_id int not null,sharding_id int not null);
9 insert data to customer 
         insert into customer (id,name,company_id,sharding_id )values(1,'wang',1,10000);  //stored in db1
         insert into customer (id,name,company_id,sharding_id )values(2,'xue',2,10010);  //stored in db2
         insert into customer (id,name,company_id,sharding_id )values(3,'feng',3,10000); //stored in db1
       you can execute sql : 'explain insert into customer (id,name,company_id,sharding_id )values(1,'wang',1,10000)' to see 
       
       run join sql as following 
       select * from company,customer where company.id=customer.company_id;
       
       you will find three records are returned ,we support join which span multi data node !  the key is 'global table' 
10 create child table order and insert records
        create table orders (id int not null primary key ,customer_id int not null,sataus int ,note varchar(100) );
        insert into orders(id,customer_id) values(1,1); //stored in db1 because customer table with id=1 stored in db1   
        insert into orders(id,customer_id) values(2,2); //stored in db2 because customer table with id=1 stored in db2    
        explain insert into orders(id,customer_id) values(2,2); 
        select customer.name ,orders.* from customer ,orders where customer.id=orders.customer_id; 
        you can find full join supported!
        
schema defined in conf/schema.xml

when you want't create a table ,should first add a table partition define in schema.xml file 
such as <table name="demo1" dataNode="dn1_M1,dn2_M1" rule="sharding-by-intfile" />
which means demo1 is stored at dn1_M1 and dn2_M1 datanode ,sharding by rule of name sharding-by-intfile,
all rules defined in rule.xml ,sharding-by-intfile is a rule which sharding to multi nodes by column 'sharind_id'
then you run DLL SQL to create table demo1 : 
 create table demo1(id int not null primary key,name varchar(20),sharding_id int not null);

table is created ,and you can run explain sql to find the background datanodes of this table :
explain select * from table demo1
explain select * from table demo1 where sharding_id=10000
explain select * from table demo1 where sharding_id=10010

select id from company where id=(select company_id from customer where id=3);
insert into orders(id,name) values(1,'wang');