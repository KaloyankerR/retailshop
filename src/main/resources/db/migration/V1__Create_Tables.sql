-- Create Product Table
CREATE TABLE Product (
                         ProductID INT IDENTITY(1,1) PRIMARY KEY,
                         Name NVARCHAR(100) NOT NULL,
                         Description NVARCHAR(255),
                         Price DECIMAL(10,2) NOT NULL
);

-- Create Customer Table
CREATE TABLE Customer (
                          CustomerID INT IDENTITY(1,1) PRIMARY KEY,
                          FirstName NVARCHAR(50) NOT NULL,
                          LastName NVARCHAR(50) NOT NULL,
                          Email NVARCHAR(100) UNIQUE NOT NULL,
                          PhoneNumber NVARCHAR(20),
                          Address NVARCHAR(255)
);

-- Create Shop Table
CREATE TABLE Shop (
                      ShopID INT IDENTITY(1,1) PRIMARY KEY,
                      Name NVARCHAR(100) NOT NULL,
                      Address NVARCHAR(255),
                      PhoneNumber NVARCHAR(20)
);

-- Create Inventory Table
CREATE TABLE Inventory (
                           InventoryID INT IDENTITY(1,1) PRIMARY KEY,
                           ProductID INT NOT NULL,
                           ShopID INT NOT NULL,
                           Quantity INT NOT NULL,
                           CONSTRAINT FK_Inventory_Product FOREIGN KEY (ProductID) REFERENCES Product(ProductID),
                           CONSTRAINT FK_Inventory_Shop FOREIGN KEY (ShopID) REFERENCES Shop(ShopID)
);

-- Create Sale Table
CREATE TABLE Sale (
                      SaleID INT IDENTITY(1,1) PRIMARY KEY,
                      CustomerID INT NOT NULL,
                      ShopID INT NOT NULL,
                      SaleDate DATETIME NOT NULL DEFAULT GETDATE(),
                      TotalAmount DECIMAL(10,2) NOT NULL,
                      CONSTRAINT FK_Sale_Customer FOREIGN KEY (CustomerID) REFERENCES Customer(CustomerID),
                      CONSTRAINT FK_Sale_Shop FOREIGN KEY (ShopID) REFERENCES Shop(ShopID)
);

-- Create SaleItem Table
CREATE TABLE SaleItem (
                          SaleItemID INT IDENTITY(1,1) PRIMARY KEY,
                          SaleID INT NOT NULL,
                          ProductID INT NOT NULL,
                          Quantity INT NOT NULL,
                          UnitPrice DECIMAL(10,2) NOT NULL,
                          TotalPrice AS (Quantity * UnitPrice) PERSISTED,
                          CONSTRAINT FK_SaleItem_Sale FOREIGN KEY (SaleID) REFERENCES Sale(SaleID),
                          CONSTRAINT FK_SaleItem_Product FOREIGN KEY (ProductID) REFERENCES Product(ProductID)
);
