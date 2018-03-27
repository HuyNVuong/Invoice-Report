	
	DROP TABLE IF EXISTS YearMembership;
	DROP TABLE IF EXISTS DayMembership;
	DROP TABLE IF EXISTS EquipmentRental;
	DROP TABLE IF EXISTS ParkingPasses;
	DROP TABLE IF EXISTS Email;
    DROP TABLE IF EXISTS Membership;
    DROP TABLE IF EXISTS Service;
	DROP TABLE IF EXISTS Products;
    DROP TABLE IF EXISTS Invoice;
    DROP TABLE IF EXISTS Members;
    DROP TABLE IF EXISTS Persons;
    DROP TABLE IF EXISTS Address;
# Create Address Table that using in Perons, Members and Products
CREATE TABLE `Address` (
  `AddressID` int(11) NOT NULL AUTO_INCREMENT,
  `Street` varchar(45) NOT NULL,
  `City` varchar(45) NOT NULL,
  `State` varchar(25) NOT NULL,
  `Zip` varchar(15) NOT NULL,
  `Country` varchar(45) NOT NULL,
  PRIMARY KEY (`AddressID`)
) ENGINE=InnoDB AUTO_INCREMENT= 14 DEFAULT CHARSET=utf8;



# Create Members Table that stores a Member information
CREATE TABLE `Members` (
  `MemberID` int(11) NOT NULL AUTO_INCREMENT,
  `MemberCode` varchar(45) NOT NULL,
  `MemberType` varchar(10) NOT NULL,
  `MemberContact` varchar(45) NOT NULL,
  `MemberName` varchar(45) NOT NULL,
  `MemberAddressID` int(11) NOT NULL,
  PRIMARY KEY (`MemberID`),
  KEY `AddressID(11)_idx` (`MemberAddressID`),
  CONSTRAINT `MemberAddressID` FOREIGN KEY (`MemberAddressID`) REFERENCES `Address` (`AddressID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;


#Create Persons table that stores a Person information
CREATE TABLE `Persons` (
  `PersonID` int(11) NOT NULL AUTO_INCREMENT,
  `PersonCode` varchar(15) NOT NULL,
  `PersonFirstName` varchar(45) NOT NULL,
  `PersonLastName` varchar(45) NOT NULL,
  `PersonAddressID` int(11) NOT NULL,
  PRIMARY KEY (`PersonID`),
  KEY `AddressID_idx` (`PersonAddressID`),
  CONSTRAINT `PersonAddressID` FOREIGN KEY (`PersonAddressID`) REFERENCES `Address` (`AddressID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

# Create an Email table that create multiple email for 1 person
CREATE TABLE `Email` (
  `EmailID` int(11) NOT NULL AUTO_INCREMENT,
  `Email` varchar(200) NOT NULL,
  `PersonID` int(11) NOT NULL,
  PRIMARY KEY (`EmailID`),
  KEY `PersonID_idx` (`PersonID`),
  CONSTRAINT `PersonID` FOREIGN KEY (`PersonID`) REFERENCES `Persons` (`PersonID`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

# Create Invoice table that reads summarize of a person
CREATE TABLE `Invoice` (
  `InvoiceID` int(11) NOT NULL AUTO_INCREMENT,
  `InvoiceCode` varchar(15) NOT NULL,
  `InvoiceMemberID` int(11) NOT NULL,
  `InvoicePersonID` int(11) NOT NULL,
  `InvoiceDate` varchar(45) NOT NULL,
  PRIMARY KEY (`InvoiceID`),
  KEY `InvoiceMemberID_idx` (`InvoiceMemberID`),
  KEY `InvoicePersonID_idx` (`InvoicePersonID`),
  CONSTRAINT `InvoiceMemberID` FOREIGN KEY (`InvoiceMemberID`) REFERENCES `Members` (`MemberID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `InvoicePersonID` FOREIGN KEY (`InvoicePersonID`) REFERENCES `Persons` (`PersonID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

#Create a Products table that store a Products information
CREATE TABLE `Products` (
  `ProductID` int(11) NOT NULL AUTO_INCREMENT,
  `ProductCode` varchar(15) NOT NULL,
  `ProductType` varchar(15) NOT NULL,
  PRIMARY KEY (`ProductID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# Create a join table of Invoice and Products, made of Membership and Service

# Create Membership and Service table that includes in Products
# Membership
CREATE TABLE `Membership` (
  `MembershipID` int(11) NOT NULL AUTO_INCREMENT,
  `MembershipProductID` int(11) NOT NULL,
  `MembershipInvoiceID` int(11) NOT NULL,
  `Quantity` int(11) NOT NULL,
  PRIMARY KEY (`MembershipID`),
  KEY `MembershipProductID_idx` (`MembershipProductID`),
  KEY `MembershipInvoiceID_idx` (`MembershipInvoiceID`),
  CONSTRAINT `MembershipInvoiceID` FOREIGN KEY (`MembershipInvoiceID`) REFERENCES `Invoice` (`InvoiceID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `MembershipProductID` FOREIGN KEY (`MembershipProductID`) REFERENCES `Products` (`ProductID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# Service
CREATE TABLE `Service` (
  `ServiceID` int(11) NOT NULL AUTO_INCREMENT,
  `ServiceProductID` int(11) NOT NULL,
  `ServiceInvoiceID` int(11) NOT NULL,
  `Quantity` int(11) NOT NULL,
  `ProductCodeAttach` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`ServiceID`),
  KEY `ServiceProductID_idx` (`ServiceProductID`),
  KEY `ServiceInvoiceID_idx` (`ServiceInvoiceID`),
  CONSTRAINT `ServiceInvoiceID` FOREIGN KEY (`ServiceInvoiceID`) REFERENCES `Invoice` (`InvoiceID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `ServiceProductID` FOREIGN KEY (`ServiceProductID`) REFERENCES `Products` (`ProductID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


# Membership / YearMembership
CREATE TABLE `YearMembership` (
  `YearMembershipID` int(11) NOT NULL AUTO_INCREMENT,
  `StartDate` varchar(45) NOT NULL,
  `EndDate` varchar(45) NOT NULL,
  `Name` varchar(45) NOT NULL,
  `Price` float NOT NULL,
  `YearProductID` int(11) NOT NULL,
  `YearAddressID` int(11) NOT NULL,
  PRIMARY KEY (`YearMembershipID`),
  KEY `ProductID_idx` (`YearProductID`),
  KEY `AddressID_idx` (`YearAddressID`),
  CONSTRAINT `YearAddressID` FOREIGN KEY (`YearAddressID`) REFERENCES `Address` (`AddressID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `YearProductID` FOREIGN KEY (`YearProductID`) REFERENCES `Products` (`ProductID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;



# Membership / DayMembership
 CREATE TABLE `DayMembership` (
  `DayMembershipID` int(11) NOT NULL AUTO_INCREMENT,
  `StartDate` varchar(45) NOT NULL,
  `Cost` float NOT NULL,
  `DayAddressID` int(11) NOT NULL,
  `DayProductID` int(11) NOT NULL,
  PRIMARY KEY (`DayMembershipID`),
  KEY `ProductID_idx` (`DayProductID`),
  KEY `ProductAddressID_idx` (`DayAddressID`),
  CONSTRAINT `DayAddressID` FOREIGN KEY (`DayAddressID`) REFERENCES `Address` (`AddressID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `DayProductID` FOREIGN KEY (`DayProductID`) REFERENCES `Products` (`ProductID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;



# Service / EquipmentRental
CREATE TABLE `EquipmentRental` (
  `EquipmentRentalID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) NOT NULL,
  `Cost` float NOT NULL,
  `RentalProductID` int(11) NOT NULL,
  PRIMARY KEY (`EquipmentRentalID`),
  KEY `RentalProductID_idx` (`RentalProductID`),
  CONSTRAINT `RentalProductID` FOREIGN KEY (`RentalProductID`) REFERENCES `Products` (`ProductID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;




# Service / ParkingPasses
CREATE TABLE `ParkingPasses` (
  `ParkingPassesID` int(11) NOT NULL AUTO_INCREMENT,
  `Fee` float NOT NULL,
  `ParkingProductID` int(11) NOT NULL,
  PRIMARY KEY (`ParkingPassesID`),
  KEY `ParkingProductID_idx` (`ParkingProductID`),
  CONSTRAINT `ParkingProductID` FOREIGN KEY (`ParkingProductID`) REFERENCES `Products` (`ProductID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;




# Exclude if is not a appropirate table


/* Queries for old tables that is needed to be change
# Queries that add new Address for every new entity / Bonus: 20+ Address added and implemented throughout all entities
ALTER TABLE Address AUTO_INCREMENT = 1;
INSERT INTO Address (Street, City, State, Zip, Country) 
VALUE ('259 Concorde Suites', 'Lincoln','NE', '68588-0115', 'USA');
INSERT INTO Address (Street, City, State, Zip, Country)
VALUE ('1223 Oldfather Hall', 'Lincoln','NE', '68503', 'USA');
INSERT INTO Address (Street, City, State, Zip, Country) 
VALUE ('123 Venture Way', 'Culver City','CA', '90230', 'USA');
INSERT INTO Address (Street, City, State, Zip, Country)
VALUE ('9800 Savage Rd', 'Fort Meade','MD', '20750', 'USA');
INSERT INTO Address (Street, City, State, Zip, Country) 
VALUE ('1 Blue Jays Way', 'Toronto','ON', 'M5V 1J1', 'Canada');
INSERT INTO Address (Street, City, State, Zip, Country) 
VALUE ('1060 West Addison', 'Chicago','IL', '60613', 'USA');
INSERT INTO Address (Street, City, State, Zip, Country)
VALUE ('123 N 1st Street', 'Omaha', 'NE', '68116', 'USA');
INSERT INTO Address (Street, City, State, Zip, Country) 
VALUE ('321 Bronx Street', 'New York','NY', '10005-0012', 'USA');
INSERT INTO Address (Street, City, State, Zip, Country) 
VALUE ('1 E 161st St', 'Bronx', 'NY', '10451', 'USA');
INSERT INTO Address (Street, City, State, Zip, Country)
VALUE ('700 E Grand Ave', 'Chicago','IL', '60616', 'USA');
INSERT INTO Address (Street, City, State, Zip, Country) 
VALUE ('Campos El290', 'Mexico City','FD', '10103', 'Mexico');
INSERT INTO Address (Street, City, State, Zip, Country)
VALUE ('755 Willard Drive', 'Ashwaubenon', 'WI', '54304', 'USA');
INSERT INTO Address (Street, City, State, Zip, Country) 
VALUE ('179 West Lane', 'Lincoln', 'NE', '68588-0115', 'USA');
INSERT INTO Address (Street, City, State, Zip, Country) 
VALUE ('3555 South 140th Plaza', 'Omaha', 'NE', '68144', 'USA');
INSERT INTO Address (Street, City, State, Zip, Country) 
VALUE ('831 Main Street', 'Albuquerque', 'NM', '18932-110', 'USA');
INSERT INTO Address (Street, City, State, Zip, Country) 
VALUE ('123 Friendly Street', 'Ottawa', 'ON', 'KIA 0G9', 'Canada');
INSERT INTO Address (Street, City, State, Zip, Country) 
VALUE ('993 S 8th Street', 'Omaha', 'NE', '68116', 'USA');
INSERT INTO Address (Street, City, State, Zip, Country) 
VALUE ('932 East Marble Rd', 'Lincoln', 'NE', '68588-114', 'USA');
INSERT INTO Address (Street, City, State, Zip, Country) 
VALUE ('9012 West Point', 'Lincoln', 'NE', '68508', 'USA');
INSERT INTO Address (Street, City, State, Zip, Country) 
VALUE ('893 Angry Street', 'Chicago', 'IL', '60613', 'USA');
INSERT INTO Address (Street, City, State, Zip, Country) 
VALUE ('245 Sout Main', 'Toronto', 'ON', 'M5V 2T6', 'Canada');
INSERT INTO Address (Street, City, State, Zip, Country) 
VALUE ('3 South Lane', 'Saskethewan', 'WI', '53239', 'USA');

# Queries that add new Member into Member table based on addressID
ALTER TABLE Members AUTO_INCREMENT = 1;
INSERT INTO Members (MemberCode, MemberType, MemberContact, MemberName, MemberAddressID) 
VALUE ('M001', 'G', '231', 'Clark Consultants', 1);
INSERT INTO Members (MemberCode, MemberType, MemberContact, MemberName, MemberAddressID) 
VALUE ('M002', 'S', '944c', 'CAS International Fellows', 2);
INSERT INTO Members (MemberCode, MemberType, MemberContact, MemberName, MemberAddressID) 
VALUE ('M003', 'S', '306a', 'VALUEless Club', 3);
INSERT INTO Members (MemberCode, MemberType, MemberContact, MemberName, MemberAddressID) 
VALUE ('M004', 'G', '321f', 'Stony Brook', 4);
INSERT INTO Members (MemberCode, MemberType, MemberContact, MemberName, MemberAddressID) 
VALUE ('M005', 'S', '2342', 'Albuquerque PD', 15);
INSERT INTO Members (MemberCode, MemberType, MemberContact, MemberName, MemberAddressID) 
VALUE ('M006', 'G', '03st', 'S&T Attorneys-at-Law', 19);

# Queries that add new Person into Person table based on addressID
ALTER TABLE Persons AUTO_INCREMENT = 1;
INSERT INTO Persons (PersonCode, PersonFirstName, PersonLastName, PersonAddressID) 
VALUE ('231','Baker', 'Tom', 5);
INSERT INTO Persons (PersonCode, PersonFirstName, PersonLastName, PersonAddressID) 
VALUE ('944c','Castro', 'Starlin', 6);
INSERT INTO Persons (PersonCode, PersonFirstName, PersonLastName, PersonAddressID)
VALUE ('306a','Sampson', 'Brock', 7);
INSERT INTO Persons (PersonCode, PersonFirstName, PersonLastName, PersonAddressID) 
VALUE ('321f','Fox', 'Bud', 8);
INSERT INTO Persons (PersonCode, PersonFirstName, PersonLastName, PersonAddressID) 
VALUE ('nwdoc1','Eccleston', 'Chris', 9);
INSERT INTO Persons (PersonCode, PersonFirstName, PersonLastName, PersonAddressID) 
VALUE ('2ndbestd','Tennant', 'David', 10);
INSERT INTO Persons (PersonCode, PersonFirstName, PersonLastName, PersonAddressID) 
VALUE ('6doc','Hurndall', 'Richard', 11);
INSERT INTO Persons (PersonCode, PersonFirstName, PersonLastName, PersonAddressID) 
VALUE ('2342','Spalding', 'Jeff', 16);
INSERT INTO Persons (PersonCode, PersonFirstName, PersonLastName, PersonAddressID) 
VALUE ('831j','Geogre', 'Sampson', 17);
INSERT INTO Persons (PersonCode, PersonFirstName, PersonLastName, PersonAddressID) 
VALUE ('144e','Jacobson', 'Alan', 20);
INSERT INTO Persons (PersonCode, PersonFirstName, PersonLastName, PersonAddressID) 
VALUE ('92e2','Baker', 'Thomas', 21);

# Queries that add a new Email into Person that has one or more email
ALTER TABLE Email AUTO_INCREMENT = 1;
INSERT INTO Email (Email, PersonID) VALUE ('famousdoc@who.com', 1);
INSERT INTO Email (Email, PersonID) VALUE ('tbaker@cse.unl.edu', 1);
INSERT INTO Email (Email, PersonID) VALUE ('mostfamous@whovian.com', 1);
INSERT INTO Email (Email, PersonID) VALUE ('scastro@cubs.com', 2);
INSERT INTO Email (Email, PersonID) VALUE ('starlin_castro13@gmail.com', 2);
INSERT INTO Email (Email, PersonID) VALUE ('brock_f_sampson@gmail.com', 3);
INSERT INTO Email (Email, PersonID) VALUE ('bsampson@venture.com', 3);
INSERT INTO Email (Email, PersonID) VALUE ('bfox@gmail.com', 4);
INSERT INTO Email (Email, PersonID) VALUE ('csheen@crazy.net', 4);
INSERT INTO Email (Email, PersonID) VALUE ('newguy@whovian', 5);
INSERT INTO Email (Email, PersonID) VALUE ('actor@shakespeare.com', 6);
INSERT INTO Email (Email, PersonID) VALUE ('tdavid@unl.edu', 6);
INSERT INTO Email (Email, PersonID) VALUE ('rhurnhall@cse.unl.edu', 7);
INSERT INTO Email (Email, PersonID) VALUE ('richard@unl.edu', 7);
INSERT INTO Email (Email, PersonID) VALUE ('pset@whobird.edu', 11);

# Queries that add all Invoice of a Member
ALTER TABLE Invoice AUTO_INCREMENT = 1;
INSERT INTO Invoice (InvoiceCode, InvoiceMemberID, InvoicePersonID, InvoiceDate) VALUE ('INV001', 1, 5, '2016/09/03');
INSERT INTO Invoice (InvoiceCode, InvoiceMemberID, InvoicePersonID, InvoiceDate) VALUE ('INV002', 2, 6, '2016/11/10');
INSERT INTO Invoice (InvoiceCode, InvoiceMemberID, InvoicePersonID, InvoiceDate) VALUE ('INV003', 3, 7, '2016/10/16');
INSERT INTO Invoice (InvoiceCode, InvoiceMemberID, InvoicePersonID, InvoiceDate) VALUE ('INV004', 4, 9, '2018/01/17');
INSERT INTO Invoice (InvoiceCode, InvoiceMemberID, InvoicePersonID, InvoiceDate) VALUE ('INV005', 5, 11, '2017/12/21');

# Queries for adding InvoiceProducts into Invoice\
ALTER TABLE Products AUTO_INCREMENT = 1;
INSERT INTO Products (ProductCode, ProductType) VALUE ('fp12', 'D');
INSERT INTO Products (ProductCode, ProductType) VALUE ('3289', 'P');
INSERT INTO Products (ProductCode, ProductType) VALUE ('ff23', 'R');
INSERT INTO Products (ProductCode, ProductType) VALUE ('b29e', 'R');
INSERT INTO Products (ProductCode, ProductType) VALUE ('1239', 'R');
INSERT INTO Products (ProductCode, ProductType) VALUE ('90fa', 'R');
INSERT INTO Products (ProductCode, ProductType) VALUE ('32f4', 'R');
INSERT INTO Products (ProductCode, ProductType) VALUE ('782g', 'D');
INSERT INTO Products (ProductCode, ProductType) VALUE ('90fb', 'P');
INSERT INTO Products (ProductCode, ProductType) VALUE ('32f4', 'R');
INSERT INTO Products (ProductCode, ProductType) VALUE ('b801', 'Y');
INSERT INTO Products (ProductCode, ProductType) VALUE ('jt98', 'P');
INSERT INTO Products (ProductCode, ProductType) VALUE ('b801', 'Y');
INSERT INTO Products (ProductCode, ProductType) VALUE ('6r22', 'D');
INSERT INTO Products (ProductCode, ProductType) VALUE ('p344', 'R');
INSERT INTO Products (ProductCode, ProductType) VALUE ('ty23', 'P');

	# Queries for adding YearMembership if the Product Type is 'Y'
    ALTER TABLE YearMembership AUTO_INCREMENT = 1;
	INSERT INTO YearMembership (StartDate, EndDate, `Name`, Price, ProductID, ProductAddressID, Quantity) 
    VALUE ('2017/01/13', '2018/01/12', 'Gold Package', '450.00', 5, 12, 23);
    INSERT INTO YearMembership (StartDate, EndDate, `Name`, Price, ProductID, ProductAddressID, Quantity) 
    VALUE ('2017/03/15', '2018/03/16', 'Platinum Package', '420.00', 12, 17, 2);
    INSERT INTO YearMembership (StartDate, EndDate, `Name`, Price, ProductID, ProductAddressID, Quantity) 
    VALUE ('2017/03/15', '2018/03/16', 'Platinum Package', '420.00', 14, 17, 3);
    
    # Queries for adding DayMembership if the Product Type is 'D'
    ALTER TABLE DayMembership AUTO_INCREMENT = 1;
    INSERT INTO DayMembership (StartDate, Cost, DayAddressID, DayProductID, Quantity) 
    VALUE ('2016/10/21 13:10', 21.50, 13, 1, 2);
    INSERT INTO DayMembership (StartDate, Cost, DayAddressID, DayProductID, Quantity) 
    VALUE ('2018/01/16 15:30', 11.00, 11, 6, 31);
    INSERT INTO DayMembership (StartDate, Cost, DayAddressID, DayProductID, Quantity) 
    VALUE ('2018/07/12 22:05', 4.99, 22, 14, 5);

    # Queries for adding Equipment Rental if the Product Type is 'R'
    ALTER TABLE EquipmentRental AUTO_INCREMENT = 1;
    INSERT INTO EquipmentRental (`Name`, Cost, RentalProductID, Quantity, ProductAttach)
    VALUE ('Boxing Glove-Large', 4.99, 4, 4, 'fp12');
    INSERT INTO EquipmentRental (`Name`, Cost, RentalProductID, Quantity, ProductAttach)
    VALUE ('Tennis Racket', 5.50, 8, 17, 'b29e');
    INSERT INTO EquipmentRental (`Name`, Cost, RentalProductID, Quantity, ProductAttach)
    VALUE ('Tennis Racket', 5.50, 10, 3, 'none');
	INSERT INTO EquipmentRental (`Name`, Cost, RentalProductID, Quantity, ProductAttach)
    VALUE ('Sweat Pants', 42.50, 15, 4, 'none');
    
    # Queries for adding Parking Passes if the Product Type is 'P'
    ALTER TABLE ParkingPasses AUTO_INCREMENT = 1;
    INSERT INTO ParkingPasses (Fee, Quantity, ParkingProductID, ParkingAttach) VALUE (55.00, 1, 3, 'fp12');
    INSERT INTO ParkingPasses (Fee, Quantity, ParkingProductID, ParkingAttach) VALUE (25.00, 30, 7, 'b29e');
	INSERT INTO ParkingPasses (Fee, Quantity, ParkingProductID, ParkingAttach) VALUE (20.00, 3, 9, 'none');
    INSERT INTO ParkingPasses (Fee, Quantity, ParkingProductID, ParkingAttach) VALUE (17.00, 40, 12, 'b801');
    INSERT INTO ParkingPasses (Fee, Quantity, ParkingProductID, ParkingAttach) VALUE (17.00, 15, 16, 'none');
# Printing out the result and make sure they are working

*/


