# Will be subjected to change due to the fact that Database has been changed

#1. A query to retrieve the major fields for every person
	SELECT * FROM Persons;

#2. A query to add an email to a specific persons
	# Inserting email into a Person
	INSERT INTO Email (Email, PersonID) value ('aNewlyAddedEmail@invoiceQueries.com', 2);
    # Verify that it is added
	SELECT PersonFirstName as 'First Name', PersonLastName as 'Last Name', e.Email as 'Email' from Persons
	as p join Email as e on p.PersonID = e.PersonID where p.PersonID = 2;

#3. A query to change the address of a fitness in a Year-long-membership record
	UPDATE Address set Street =  '68 Lo Duc', City = 'Hanoi', State = 'VN', Country = 'VietNam'
    where AddressID = 12;
    # Verifying
	SELECT * FROM YearMembership as y right join Address as a 
    on y.ProductAddressID = a.AddressID where YearMembershipID = 1;
    
#4.A query ( or series of queries) to remove a given Year-long membership record
	SELECT * FROM YearMembership;
    # Delete the chosen DayMembership based on ID
    DELETE FROM YearMembership where YearMembershipID = 2;
    # Printing our result to verify
    SELECT * FROM YearMembership;

#5. A query to get all products in a particular Invoice
	SELECT InvoiceProductCode as 'Product Code' From InvoiceProducts as p 
	join Invoice as i on p.InvoiceID = i.InvoiceID where i.InvoiceID = 1;

#6. A query to get all invoice of a particular member
	SELECT InvoiceCode as 'Invoice Code', InvoiceDate as 'Date bought', p.InvoiceProductCode as 'Product Code' from Invoice
	as i join Members as m on i.InvoiceMemberID = m.MemberID join InvoiceProducts as p on p.InvoiceID = i.InvoiceID where MemberID = 2;

#7. A query that "adds" a particular product to a particular invoice
	# Adding Products
    INSERT INTO InvoiceProducts (InvoiceProductCode, InvoiceProductType, InvoiceID) value ('2456', 'Y', 2);
    # Verifying
    SELECT * FROM InvoiceProducts as p Right join Invoice as i on i.InvoiceID = p.InvoiceID 
    where i.InvoiceID = 2;
    
#8. A query to find the total of all per-unit costs of all Year-long Membership
	SELECT y.YearMembershipID as 'ID', y.`Name` as 'Name', 
    y.Price as 'Price', y.Quantity as 'Quantity',
	SUM(y.Quantity * y.Price) AS 'Total Cost'
    FROM YearMembership as y join YearMembership
    WHERE y.YearMembershipID = 1
	GROUP BY y.YearMembershipID;

#9. A query to find the toal number of Year-long-membership sold on a particular date.
	SELECT d.StartDate as 'Day Sold',  d.Quantity as 'Total Day-long Membership sold' FROM DayMembership as d
    where d.StartDate like "%2016/10/21%";
    
#10. A query to find the toal number of invoices for every personal trainer
	SELECT COUNT(i.InvoiceCode) as 'Total number of invoices' from Invoice as i join Members as m
    on m.MemberID = i.InvoiceMemberID join Persons as p where m.MemberContact like p.PersonCode;

#11. A query to find the total number of invoices for a particular Year-long-membership
	# There are 2 Invoice that has b801 Year-long-membership
	SELECT COUNT(*) as 'Total number of Invoice' from Invoice as i join InvoiceProducts as p
    on i.InvoiceID = p.InvoiceID join YearMembership as y on p.InvoiceProductType = 'Y' where 
    y.YearMembershipID = 2;
    
#12. A query to find the total revenue generated on a particular date from all Year-long-member ship
	SELECT y.StartDate as 'Day of sale',
	SUM(y.Quantity * y.Price) AS 'Total Sale ($)'
    FROM YearMembership as y join YearMembership
    WHERE y.StartDate like "%2017/03/15%";
    
#13. A query to find the total quanties sold of each category of services in all the existing invoices
	SELECT SUM(r.Quantity) as 'Total Equipment Rental Sold', SUM(p.Quantity) as 'Total Parking Passes Sold', 
    SUM(r.Quantity + p.Quantity) AS 'Total Service Sold'
    FROM EquipmentRental as r join ParkingPasses as p;
    
#14. A query find any invoice that includes multiple istance of the same membership
	SELECT i.InvoiceCode, p.InvoiceProductCode, p.InvoiceProductID
    FROM Invoice as i join InvoiceProducts as p on
    i.InvoiceID = p.InvoiceID group by i.InvoiceID = p.InvoiceProductCode
    HAVING COUNT(*) > 1;
	
#15. A query to find any and all invoices where the personal trainer is the same as the primary contact of the invoice member
	SELECT p.PersonID as 'Person ID', m.MemberID as 'MemberID' from Persons
    as p join Members as m where p.PersonCode like m.MemberContact;

# Bonus. 1
	# Show all existing Products
    SELECT * FROM InvoiceProducts;
    # Eliminate duplicated Products by using keyword 'DISTINCT' from printing
    SELECT DISTINCT i.InvoiceProductCode as 'Distinct Product Code' 
    FROM InvoiceProducts as i group by i.InvoiceProductCode;
    # Prevent duplicate Products from adding in
    ALTER TABLE InvoiceProducts ADD UNIQUE INDEX(InvoiceProductCode, InvoiceProductType, InvoiceID);

# Bonus. 2
	# Created 23 different Addresses
    SELECT * FROM Address;
    # Group state record using Optimization
    SELECT * FROM Address WHERE State = "NY";
    SELECT * FROM Address WHERE State = "NE";
    # Group state recrod using Wildcard
	SELECT * FROM Address WHERE State like "%NY%";
    SELECT * FROM Address WHERE State like "%NE%";
    # Optimization queries using group by
    SELECT State, COUNT(State) FROM Address  WHERE State = "NE"
    GROUP BY State;
    
# Bonus. 3
	# Business rules to make sure no services bought less than 5$ or more than 1000$ using 'CHECK'
    ALTER TABLE ParkingPasses 
	ADD CONSTRAINT Fee CHECK (Fee BETWEEN 1000 AND 5);
    ALTER TABLE EquipmentRental 
	ADD CONSTRAINT Cost CHECK (Cost BETWEEN 1000 AND 5);
    # 'INDEX' for indexing table and retrieve information much faster (already implemented from creating table in InvoiceDB)
    # 'DEFAULT' for every place that does not have a Zip
    ALTER TABLE Address
    ALTER COLUMN Zip SET DEFAULT 'none';
    
    

    