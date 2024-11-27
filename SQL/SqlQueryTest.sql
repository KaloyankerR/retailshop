-- 1. Creating the tables
CREATE TABLE Customers (
    CustomerID INT PRIMARY KEY IDENTITY,
    CustomerName NVARCHAR(100) NOT NULL
);

CREATE TABLE Products (
    ProductID INT PRIMARY KEY IDENTITY,
    ProductName NVARCHAR(100) NOT NULL,
    StockQuantity INT NOT NULL
);

CREATE TABLE Sales (
    SaleID INT PRIMARY KEY IDENTITY,
    CustomerID INT FOREIGN KEY REFERENCES Customers(CustomerID),
    ProductID INT FOREIGN KEY REFERENCES Products(ProductID),
    SaleDate DATE NOT NULL,
    Quantity INT NOT NULL,
    TotalPrice DECIMAL(10, 2) NOT NULL
);


-- 2. Inserting Dummy Data in the columns
INSERT INTO Customers (CustomerName) VALUES
('Alice Johnson'), ('Bob Smith'), ('Charles Brown'),
('Diana Prince'), ('Eve White');

INSERT INTO Products (ProductName, StockQuantity) VALUES
('Laptop', 50), ('Smartphone', 100), ('Headphones', 75),
('Tablet', 30), ('Smartwatch', 60);

INSERT INTO Sales (CustomerID, ProductID, SaleDate, Quantity, TotalPrice) VALUES
(1, 1, '2024-11-01', 1, 1200.00),
(2, 2, '2024-11-02', 2, 800.00),
(3, 3, '2024-11-03', 1, 200.00),
(4, 4, '2024-11-04', 1, 400.00),
(5, 5, '2024-11-05', 3, 900.00);


-- 3. Queries
-- a.	All sales per customer: To identify high-value customers.
SELECT 
    C.CustomerName, 
    P.ProductName, 
    S.Quantity, 
    S.TotalPrice, 
    S.SaleDate
FROM Sales S
JOIN Customers C ON S.CustomerID = C.CustomerID
JOIN Products P ON S.ProductID = P.ProductID
ORDER BY C.CustomerName;

-- b. Total Sales Per Customer
SELECT 
    C.CustomerName, 
    SUM(S.TotalPrice) AS TotalSales
FROM Sales S
JOIN Customers C ON S.CustomerID = C.CustomerID
GROUP BY C.CustomerName
ORDER BY TotalSales DESC;

-- c. Total Sales Per Customer
-- Per Month
SELECT 
    C.CustomerName, 
    YEAR(S.SaleDate) AS SaleYear,
    MONTH(S.SaleDate) AS SaleMonth,
    SUM(S.TotalPrice) AS TotalSales
FROM Sales S
JOIN Customers C ON S.CustomerID = C.CustomerID
GROUP BY C.CustomerName, YEAR(S.SaleDate), MONTH(S.SaleDate)
ORDER BY C.CustomerName, SaleYear, SaleMonth;

-- Per Quarter
SELECT 
    C.CustomerName, 
    YEAR(S.SaleDate) AS SaleYear,
    DATEPART(QUARTER, S.SaleDate) AS SaleQuarter,
    SUM(S.TotalPrice) AS TotalSales
FROM Sales S
JOIN Customers C ON S.CustomerID = C.CustomerID
GROUP BY C.CustomerName, YEAR(S.SaleDate), DATEPART(QUARTER, S.SaleDate)
ORDER BY C.CustomerName, SaleYear, SaleQuarter;

-- Per Year
SELECT 
    C.CustomerName, 
    YEAR(S.SaleDate) AS SaleYear,
    SUM(S.TotalPrice) AS TotalSales
FROM Sales S
JOIN Customers C ON S.CustomerID = C.CustomerID
GROUP BY C.CustomerName, YEAR(S.SaleDate)
ORDER BY C.CustomerName, SaleYear;

-- d. Comparison of Products Sold vs. Stocked
SELECT 
    P.ProductName,
    P.StockQuantity,
    COALESCE(SUM(S.Quantity), 0) AS SoldQuantity,
    P.StockQuantity - COALESCE(SUM(S.Quantity), 0) AS RemainingStock
FROM Products P
LEFT JOIN Sales S ON P.ProductID = S.ProductID
GROUP BY P.ProductName, P.StockQuantity
ORDER BY P.ProductName;
