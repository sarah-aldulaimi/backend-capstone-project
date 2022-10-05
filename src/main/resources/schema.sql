--drop table location;
create table location(
    ID INT AUTO_INCREMENT,
    Name VARCHAR(50) NOT NULL,
    CONSTRAINT PK_Location PRIMARY KEY (ID)
);

--drop table role;
create table role(
    ID INT AUTO_INCREMENT,
    Name VARCHAR(50) NOT NULL,
    Description VARCHAR(100) NOT NULL,
    CONSTRAINT PK_Role PRIMARY KEY (ID)
);

--drop table users;
create table users(
    ID INT AUTO_INCREMENT,
    FirstName VARCHAR(25) NOT NULL,
    LastName VARCHAR(25) NOT NULL,
    Age INT NOT NULL,
    Email VARCHAR(25) NOT NULL,
    Mobile VARCHAR(10) NOT NULL,
    Password VARCHAR(15) NOT NULL,
    LocationID INT,
    CONSTRAINT PK_User PRIMARY KEY (ID),
    CONSTRAINT FK_User_Location FOREIGN KEY (LocationID) REFERENCES Location(ID)
);

--drop table user_role;
create table user_role(
    userID int,
    RoleID int,
    CONSTRAINT PK_user_role PRIMARY KEY (UserID, RoleID),
    CONSTRAINT FK_user_role_User FOREIGN KEY (UserID) REFERENCES users(ID),
    CONSTRAINT FK_user_role_Role FOREIGN KEY (RoleID) REFERENCES ROLE(ID)
);

--drop table category;
create table category(
    ID INT AUTO_INCREMENT,
    Name VARCHAR(50) NOT NULL,
    Description VARCHAR(100) NOT NULL,
    CONSTRAINT PK_categories PRIMARY KEY(ID)
);

--drop table product;
create table product(
    ID INT AUTO_INCREMENT,
    Name VARCHAR(50) NOT NULL,
    Description VARCHAR(100) NOT NULL,
    PRICE DECIMAL(13,2) NOT NULL,
    CategoryID INT,
    CONSTRAINT PK_product PRIMARY KEY(ID),
    CONSTRAINT FK_product_category FOREIGN KEY (ID) REFERENCES category(ID)
);

--drop table orders;
create table orders(
    ID INT AUTO_INCREMENT,
    UserID INT NOT NULL,
    OrderNumber INT NOT NULL,
    ProductID INT NOT NULL,
    ProductCount INT NOT NULL,
    TotalCost DECIMAL(13,2) NOT NULL,
    Status VARCHAR(10) NOT NULL,
    CONSTRAINT PK_order PRIMARY KEY (id),
    CONSTRAINT FK_order_product FOREIGN KEY (ProductID) REFERENCES product(ID),
    CONSTRAINT fk_order_user FOREIGN KEY (UserID) REFERENCES users(ID)
);