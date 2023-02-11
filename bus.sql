CREATE DATABASE busManagementSystem;
USE busManagementSystem;

CREATE TABLE bus(
	id INT PRIMARY KEY,
    ac BOOLEAN,
    capacity INT NOT NULL
);

INSERT INTO bus (id,ac,capacity)
VALUES (1,false,10),
		(2,TRUE,25),
        (3,TRUE,30);

SELECT * FROM BUS;

UPDATE bus SET capacity = 2 WHERE ID = 1;

drop table booking;

CREATE TABLE booking(
	id INT PRIMARY KEY,
    busNumber INT NOT NULL ,
    name VARCHAR(50) NOT NULL,
    date DATE    
);

INSERT INTO booking (id,busNumber,name,date)
VALUES (1,1,"Vignesh",'2022-01-15'),
	(2,1,"KATZ",'2022-01-15'),
    (3,3,"Vignesh",'2022-01-15');

SELECT * FROM booking;

select count(id) from booking 
where busNumber = 1 and date = '2022-01-15';
DELIMITER $$
CREATE PROCEDURE getTotalBookings(IN input INT,In d date)
BEGIN
  SELECT Count(id) FROM booking where busNumber = input and date = d ;
END $$
DELIMITER ;
select count(*) from booking where busNumber = 1 and date = '2022-01-15';
call getTotalBookings(1,'2022-10-15');