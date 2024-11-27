-- Insert mock data into Product table
INSERT INTO Product (Name, Description, Price)
VALUES
    ('Laptop', 'High-performance laptop', 1200.00),
    ('Smartphone', 'Latest model smartphone', 799.99),
    ('Headphones', 'Noise-cancelling headphones', 199.99),
    ('Smartwatch', 'Feature-rich smartwatch', 299.99),
    ('Tablet', 'Lightweight and portable tablet', 499.99);

-- Insert mock data into Customer table
INSERT INTO Customer (FirstName, LastName, Email, PhoneNumber, Address)
VALUES
    ('John', 'Doe', 'john.doe@example.com', '123-456-7890', '123 Main St'),
    ('Jane', 'Smith', 'jane.smith@example.com', '987-654-3210', '456 Elm St'),
    ('Bob', 'Johnson', 'bob.johnson@example.com', '555-555-5555', '789 Maple St'),
    ('Alice', 'Williams', 'alice.williams@example.com', '222-333-4444', '321 Oak St'),
    ('Charlie', 'Brown', 'charlie.brown@example.com', '111-222-3333', '654 Pine St');

-- Insert mock data into Shop table
INSERT INTO Shop (Name, Address, PhoneNumber)
VALUES
    ('Downtown Electronics', '100 Market St', '123-456-7890'),
    ('Tech Hub', '200 Tech Blvd', '987-654-3210'),
    ('Gadget Central', '300 Gadget Rd', '555-555-5555');

-- Insert mock data into Inventory table
INSERT INTO Inventory (ProductID, ShopID, Quantity)
VALUES
    (1, 1, 10),
    (2, 1, 15),
    (3, 1, 20),
    (4, 2, 5),
    (5, 2, 8),
    (1, 3, 3),
    (2, 3, 4),
    (3, 3, 6);

-- Insert mock data into Sale table
INSERT INTO Sale (CustomerID, ShopID, TotalAmount)
VALUES
    (1, 1, 1599.98),
    (2, 2, 999.98),
    (3, 3, 299.99);

-- Insert mock data into SaleItem table
INSERT INTO SaleItem (SaleID, ProductID, Quantity, UnitPrice)
VALUES
    (1, 1, 1, 1200.00),
    (1, 3, 2, 199.99),
    (2, 2, 1, 799.99),
    (2, 4, 1, 299.99),
    (3, 4, 1, 299.99);
