SHOW DATABASES;
USE BUSMANAGEMENTSYSTEM;
DROP TABLE BOOKING;
	CREATE TABLE booking(
		id INT PRIMARY KEY auto_increment,
		busNumber INT NOT NULL ,
		name VARCHAR(50) NOT NULL,
		date DATE    
	);

	INSERT INTO booking (busNumber,name,date)
	VALUES (1,"Vignesh",'2022-01-15'),
		(1,"KATZ",'2022-01-15'),
		(3,"Vignesh",'2022-01-15');

	SELECT * FROM booking;