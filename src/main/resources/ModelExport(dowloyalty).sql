CREATE TABLE `account` (
`id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
`url` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
`name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
`shippingState` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
`phone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
`lastModiFieddate` datetime NOT NULL,
`IsActive` bit(1) NOT NULL DEFAULT b'1',
`creatTime` datetime NULL DEFAULT 'CURRENT_TIMESTAMP'
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci;

CREATE TABLE `admin` (
`ID` int(11) NOT NULL,
`ChineseName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
`UserId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
`IsActive` bit(1) NOT NULL DEFAULT b'0',
PRIMARY KEY (`ID`) 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci;

CREATE TABLE `exchangerecord` (
`ID` int(11) NOT NULL,
`RetailerID` int(11) NOT NULL,
`GoodsID` int(11) NOT NULL,
`ProjectID` int(11) NULL DEFAULT NULL,
`Amount` int(11) NOT NULL,
`SubmitTime` datetime NOT NULL,
`SendOutTime` datetime NULL DEFAULT NULL,
`CompleteTime` datetime NULL DEFAULT NULL,
`Status` int(11) NOT NULL,
`ExchangePoints` int(11) NOT NULL,
PRIMARY KEY (`ID`) ,
INDEX `RetailerID_idx` (`RetailerID`),
INDEX `GoodsID_idx` (`GoodsID`),
INDEX `pk_project` (`ProjectID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci;

CREATE TABLE `exchangeshop` (
`ID` int(11) NOT NULL,
`ProjectID` int(11) NOT NULL,
`GoodsID` int(11) NOT NULL,
`ExchangePoints` int(11) NOT NULL,
`IsActive` bit(1) NOT NULL DEFAULT b'0',
PRIMARY KEY (`ID`) ,
INDEX `GoodsID_idx` (`GoodsID`),
INDEX `FK_shopfkgoods_idx` (`ProjectID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci;

CREATE TABLE `goods` (
`ID` int(11) NOT NULL,
`Name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
`Description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
`ImagePath` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
`IsActive` bit(1) NOT NULL DEFAULT b'0',
PRIMARY KEY (`ID`) 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci;

CREATE TABLE `points` (
`ID` int(11) NOT NULL,
`ProjectID` int(11) NOT NULL,
`ProductID` int(11) NOT NULL,
`SalesAmount` int(11) NOT NULL,
`Points` int(11) NOT NULL,
`IsActive` bit(1) NOT NULL DEFAULT b'0',
PRIMARY KEY (`ID`) ,
INDEX `ProjectID_idx` (`ProjectID`),
INDEX `ProductID_idx` (`ProductID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci;

CREATE TABLE `pointslevel` (
`Id` int(11) NOT NULL,
`ProjectID` int(11) NOT NULL,
`Name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
`Points` int(11) NOT NULL,
`IsActive` bit(1) NOT NULL DEFAULT b'0',
PRIMARY KEY (`Id`) ,
INDEX `ProjectID` (`ProjectID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci;

CREATE TABLE `product` (
`ID` int(11) NOT NULL,
`SFDCCode` int(11) NULL DEFAULT NULL,
`ProductFamilyID` int(11) NOT NULL,
`Name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
`Description` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
`LastModifiedDate` datetime NULL DEFAULT NULL,
`IsActive` bit(1) NOT NULL DEFAULT b'0',
PRIMARY KEY (`ID`) ,
INDEX `ProductFamilyID_idx` (`ProductFamilyID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci;

CREATE TABLE `productcategory` (
`ID` int(11) NOT NULL,
`Name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
`Description` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
`IsActive` bit(1) NOT NULL DEFAULT b'0',
PRIMARY KEY (`ID`) 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_bin;

CREATE TABLE `productfamily` (
`ID` int(11) NOT NULL,
`Name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
`Description` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
`IsActive` bit(1) NOT NULL DEFAULT b'0',
`CategroyID` int(11) NOT NULL,
PRIMARY KEY (`ID`) ,
INDEX `CategroyID` (`CategroyID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci;

CREATE TABLE `project` (
`ID` int(11) NOT NULL,
`Name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
`ProvinceID` int(11) NOT NULL,
`PlacardPath` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
`BackgroundPath` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
`AssistantID` int(11) NULL DEFAULT NULL,
`StartDate` datetime NOT NULL,
`EndDate` datetime NOT NULL,
`AdminID` int(11) NOT NULL,
`IsVisible` bit(1) NOT NULL DEFAULT b'0',
`IsActive` bit(1) NOT NULL DEFAULT b'0',
`Description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
PRIMARY KEY (`ID`) ,
INDEX `ProvinceID_idx` (`ProvinceID`),
INDEX `AssistantID_idx` (`AssistantID`),
INDEX `AdminID_idx` (`AdminID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci;

CREATE TABLE `promoter` (
`ID` int(11) NOT NULL,
`ChineseName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
`UserId` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
`IsActive` bit(1) NOT NULL DEFAULT b'0',
PRIMARY KEY (`ID`) 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci;

CREATE TABLE `province` (
`ID` int(11) NOT NULL,
`Name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
`enName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
`IsActive` bit(1) NOT NULL DEFAULT b'0',
PRIMARY KEY (`ID`) 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci;

CREATE TABLE `retailer` (
`ID` int(11) NOT NULL,
`ChineseName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
`ProvinceID` int(11) NOT NULL,
`Mobile` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
`OpenID` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
`Email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
`LastUpdataData` datetime NULL DEFAULT NULL,
`SFDCCode` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
`LoginCode` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
`IsActive` bit(1) NOT NULL DEFAULT b'0',
PRIMARY KEY (`ID`) ,
INDEX `ProvinceID_idx` (`ProvinceID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci;

CREATE TABLE `rprojectpromoter` (
`ID` int(11) NOT NULL,
`ProjectID` int(11) NOT NULL,
`PromoterID` int(11) NOT NULL,
`IsActive` bit(1) NOT NULL DEFAULT b'0',
PRIMARY KEY (`ID`) ,
INDEX `ProjectID_idx` (`ProjectID`),
INDEX `PromoterID_idx` (`PromoterID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci;

CREATE TABLE `rprojectretailer` (
`ID` int(11) NOT NULL,
`ProjectID` int(11) NOT NULL,
`RetailerID` int(11) NOT NULL,
`IsActive` bit(1) NOT NULL DEFAULT b'0',
PRIMARY KEY (`ID`) ,
INDEX `ProjectID_fk1_idx` (`ProjectID`),
INDEX `RetailerID_fk2_idx` (`RetailerID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci;

CREATE TABLE `rpromoterprovince` (
`ID` int(11) NOT NULL,
`PromoterID` int(11) NOT NULL,
`ProvinceID` int(11) NOT NULL,
`IsActive` bit(1) NOT NULL DEFAULT b'0',
PRIMARY KEY (`ID`) ,
INDEX `PromoterID_idx` (`PromoterID`),
INDEX `ProvinceID_idx` (`ProvinceID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci;

CREATE TABLE `salerecord` (
`ID` int(11) NOT NULL,
`RetailerID` int(11) NOT NULL,
`ProductID` int(11) NOT NULL,
`TotalPrice` float NOT NULL,
`ImporterID` int(11) NOT NULL,
`SubmitDate` datetime NOT NULL,
`ProjectID` int(11) NULL DEFAULT NULL,
`Points` int(11) NULL DEFAULT NULL,
`Amount` int(11) NOT NULL,
`Status` bit(1) NOT NULL DEFAULT b'0',
`OppId` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
PRIMARY KEY (`ID`) ,
INDEX `RetailerID_idx` (`RetailerID`),
INDEX `ProductSepcificationID_idx` (`ProductID`),
INDEX `ImporterID_idx` (`ImporterID`),
INDEX `ProjectID_idx` (`ProjectID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci;


ALTER TABLE `exchangerecord` ADD CONSTRAINT `GoodsID_fk2` FOREIGN KEY (`GoodsID`) REFERENCES `goods` (`ID`);
ALTER TABLE `exchangerecord` ADD CONSTRAINT `RetailerID_fk1` FOREIGN KEY (`RetailerID`) REFERENCES `retailer` (`ID`);
ALTER TABLE `exchangerecord` ADD CONSTRAINT `pk_project` FOREIGN KEY (`ProjectID`) REFERENCES `project` (`ID`);
ALTER TABLE `exchangeshop` ADD CONSTRAINT `FK_shopfkgoods` FOREIGN KEY (`ProjectID`) REFERENCES `project` (`ID`);
ALTER TABLE `exchangeshop` ADD CONSTRAINT `GoodsID` FOREIGN KEY (`GoodsID`) REFERENCES `goods` (`ID`);
ALTER TABLE `points` ADD CONSTRAINT `ProductID` FOREIGN KEY (`ProductID`) REFERENCES `product` (`ID`);
ALTER TABLE `points` ADD CONSTRAINT `ProjectID` FOREIGN KEY (`ProjectID`) REFERENCES `project` (`ID`);
ALTER TABLE `pointslevel` ADD CONSTRAINT `pointslevel_ibfk_1` FOREIGN KEY (`ProjectID`) REFERENCES `project` (`ID`);
ALTER TABLE `product` ADD CONSTRAINT `ProductFamilyID` FOREIGN KEY (`ProductFamilyID`) REFERENCES `productfamily` (`ID`);
ALTER TABLE `productfamily` ADD CONSTRAINT `CategroyID` FOREIGN KEY (`CategroyID`) REFERENCES `productcategory` (`ID`);
ALTER TABLE `project` ADD CONSTRAINT `AdminID` FOREIGN KEY (`AdminID`) REFERENCES `admin` (`ID`);
ALTER TABLE `project` ADD CONSTRAINT `AssistantID` FOREIGN KEY (`AssistantID`) REFERENCES `promoter` (`ID`);
ALTER TABLE `project` ADD CONSTRAINT `ProvinceID` FOREIGN KEY (`ProvinceID`) REFERENCES `province` (`ID`);
ALTER TABLE `retailer` ADD CONSTRAINT `Province_fk` FOREIGN KEY (`ProvinceID`) REFERENCES `province` (`ID`);
ALTER TABLE `rprojectpromoter` ADD CONSTRAINT `Project_ID` FOREIGN KEY (`ProjectID`) REFERENCES `project` (`ID`);
ALTER TABLE `rprojectpromoter` ADD CONSTRAINT `PromoterID` FOREIGN KEY (`PromoterID`) REFERENCES `promoter` (`ID`);
ALTER TABLE `rprojectretailer` ADD CONSTRAINT `ProjectID_fk1` FOREIGN KEY (`ProjectID`) REFERENCES `project` (`ID`);
ALTER TABLE `rprojectretailer` ADD CONSTRAINT `RetailerID_fk2` FOREIGN KEY (`RetailerID`) REFERENCES `retailer` (`ID`);
ALTER TABLE `rpromoterprovince` ADD CONSTRAINT `Promoter_ID` FOREIGN KEY (`PromoterID`) REFERENCES `promoter` (`ID`);
ALTER TABLE `rpromoterprovince` ADD CONSTRAINT `Province_ID` FOREIGN KEY (`ProvinceID`) REFERENCES `province` (`ID`);
ALTER TABLE `salerecord` ADD CONSTRAINT `ImporterID_fk3` FOREIGN KEY (`ImporterID`) REFERENCES `promoter` (`ID`);
ALTER TABLE `salerecord` ADD CONSTRAINT `ProductSepcificationID_fk2` FOREIGN KEY (`ProductID`) REFERENCES `product` (`ID`);
ALTER TABLE `salerecord` ADD CONSTRAINT `ProjectID_fk4` FOREIGN KEY (`ProjectID`) REFERENCES `project` (`ID`);
ALTER TABLE `salerecord` ADD CONSTRAINT `Retailer_fk1` FOREIGN KEY (`RetailerID`) REFERENCES `retailer` (`ID`);

