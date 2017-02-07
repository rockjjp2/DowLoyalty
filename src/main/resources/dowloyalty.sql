/*
Navicat MySQL Data Transfer

Source Server         : MySql
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : dowloyalty

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-02-07 17:31:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ChineseName` varchar(50) DEFAULT NULL,
  `IsActive` bit(1) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='????Ա?;

-- ----------------------------
-- Table structure for exchangerecord
-- ----------------------------
DROP TABLE IF EXISTS `exchangerecord`;
CREATE TABLE `exchangerecord` (
  `ID` int(11) NOT NULL,
  `RetailerID` int(11) NOT NULL,
  `GoodsID` int(11) NOT NULL,
  `Amount` int(11) NOT NULL,
  `SubmitTime` datetime NOT NULL,
  `SendOutTime` datetime DEFAULT NULL,
  `CompleteTime` datetime DEFAULT NULL,
  `Status` int(11) NOT NULL,
  `ExchangePoints` int(11) NOT NULL COMMENT '?һ??ܻ?????',
  PRIMARY KEY (`ID`),
  KEY `RetailerID_idx` (`RetailerID`),
  KEY `GoodsID_idx` (`GoodsID`),
  CONSTRAINT `GoodsID_fk2` FOREIGN KEY (`GoodsID`) REFERENCES `goods` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `RetailerID_fk1` FOREIGN KEY (`RetailerID`) REFERENCES `retailer` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='???ֶһ???¼?;

-- ----------------------------
-- Table structure for exchangeshop
-- ----------------------------
DROP TABLE IF EXISTS `exchangeshop`;
CREATE TABLE `exchangeshop` (
  `ID` int(11) NOT NULL,
  `ProjectID` int(11) NOT NULL,
  `GoodsID` int(11) NOT NULL,
  `ExchangePoints` int(11) NOT NULL,
  `IsActive` bit(1) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `GoodsID_idx` (`GoodsID`),
  KEY `FK_shopfkgoods_idx` (`ProjectID`) USING BTREE,
  CONSTRAINT `FK_shopfkgoods` FOREIGN KEY (`ProjectID`) REFERENCES `project` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `GoodsID` FOREIGN KEY (`GoodsID`) REFERENCES `goods` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='?????̳Ǳ;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `ID` int(11) NOT NULL,
  `Name` varchar(50) NOT NULL,
  `Description` varchar(255) DEFAULT NULL,
  `ImagePath` varchar(50) NOT NULL,
  `IsActive` bit(1) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='??Ʒ?;

-- ----------------------------
-- Table structure for points
-- ----------------------------
DROP TABLE IF EXISTS `points`;
CREATE TABLE `points` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ProjectID` int(11) NOT NULL,
  `ProductID` int(11) NOT NULL,
  `SalesAmount` int(11) NOT NULL,
  `Points` int(11) NOT NULL,
  `IsActive` bit(1) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `ProjectID_idx` (`ProjectID`),
  KEY `ProductID_idx` (`ProductID`) USING BTREE,
  CONSTRAINT `ProductID` FOREIGN KEY (`ProjectID`) REFERENCES `product` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `ProjectID` FOREIGN KEY (`ProjectID`) REFERENCES `project` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='???ֱ;

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `ID` int(11) NOT NULL,
  `SFDCCode` int(11) DEFAULT NULL,
  `ProductFamilyID` int(11) NOT NULL,
  `Name` varchar(50) NOT NULL,
  `Description` varchar(50) NOT NULL,
  `LastModifiedDate` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `IsActive` bit(1) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `ProductFamilyID_idx` (`ProductFamilyID`) USING BTREE,
  CONSTRAINT `ProductFamilyID` FOREIGN KEY (`ProductFamilyID`) REFERENCES `productfamily` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='??Ʒ?????;

-- ----------------------------
-- Table structure for productcategory
-- ----------------------------
DROP TABLE IF EXISTS `productcategory`;
CREATE TABLE `productcategory` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(50) CHARACTER SET utf8 NOT NULL,
  `Description` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `IsActive` bit(1) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for productfamily
-- ----------------------------
DROP TABLE IF EXISTS `productfamily`;
CREATE TABLE `productfamily` (
  `ID` int(11) NOT NULL,
  `Name` varchar(50) NOT NULL,
  `Description` varchar(200) DEFAULT NULL,
  `IsActive` bit(1) NOT NULL,
  `CategroyID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `CategroyID` (`CategroyID`),
  CONSTRAINT `CategroyID` FOREIGN KEY (`CategroyID`) REFERENCES `productcategory` (`ID`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for project
-- ----------------------------
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project` (
  `ID` int(11) NOT NULL,
  `Name` varchar(50) NOT NULL,
  `ProvinceID` int(11) NOT NULL,
  `PlacardPath` varchar(50) NOT NULL COMMENT '????·??',
  `AssistantID` int(11) DEFAULT NULL,
  `StartDate` datetime NOT NULL COMMENT '???ʼʱ??',
  `EndDate` datetime NOT NULL COMMENT '?????ʱ??',
  `AdminID` int(11) NOT NULL,
  `IsVisible` bit(1) NOT NULL COMMENT 'ǰ̨?????ɼ???',
  `IsActive` bit(1) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `ProvinceID_idx` (`ProvinceID`),
  KEY `AssistantID_idx` (`AssistantID`),
  KEY `AdminID_idx` (`AdminID`),
  CONSTRAINT `AdminID` FOREIGN KEY (`AdminID`) REFERENCES `admin` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `AssistantID` FOREIGN KEY (`AssistantID`) REFERENCES `promoter` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `ProvinceID` FOREIGN KEY (`ProvinceID`) REFERENCES `province` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='??;

-- ----------------------------
-- Table structure for promoter
-- ----------------------------
DROP TABLE IF EXISTS `promoter`;
CREATE TABLE `promoter` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ChineseName` varchar(50) DEFAULT NULL,
  `UserId` varchar(50) NOT NULL,
  `IsActive` bit(1) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1002 DEFAULT CHARSET=utf8 COMMENT='?ƹ?Ա?;

-- ----------------------------
-- Table structure for province
-- ----------------------------
DROP TABLE IF EXISTS `province`;
CREATE TABLE `province` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(50) NOT NULL,
  `IsActive` bit(1) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='ʡ?ݱ;

-- ----------------------------
-- Table structure for retailer
-- ----------------------------
DROP TABLE IF EXISTS `retailer`;
CREATE TABLE `retailer` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ChineseName` varchar(50) NOT NULL,
  `ProvinceID` int(11) NOT NULL,
  `Mobile` varchar(50) DEFAULT NULL,
  `OpenID` varchar(50) DEFAULT NULL,
  `Email` varchar(50) DEFAULT NULL,
  `LastUpdataData` datetime DEFAULT NULL,
  `SFDCCode` varchar(50) DEFAULT NULL,
  `LoginCode` varchar(50) DEFAULT NULL,
  `IsActive` bit(1) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `ProvinceID_idx` (`ProvinceID`),
  CONSTRAINT `Province_fk` FOREIGN KEY (`ProvinceID`) REFERENCES `province` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1002 DEFAULT CHARSET=utf8 COMMENT='?????̱;

-- ----------------------------
-- Table structure for rprojectpromoter
-- ----------------------------
DROP TABLE IF EXISTS `rprojectpromoter`;
CREATE TABLE `rprojectpromoter` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ProjectID` int(11) NOT NULL,
  `PromoterID` int(11) NOT NULL,
  `IsActive` bit(1) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `ProjectID_idx` (`ProjectID`),
  KEY `PromoterID_idx` (`PromoterID`),
  CONSTRAINT `Project_ID` FOREIGN KEY (`ProjectID`) REFERENCES `project` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `PromoterID` FOREIGN KEY (`PromoterID`) REFERENCES `promoter` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='?-?ƹ?Ա?????;

-- ----------------------------
-- Table structure for rprojectretailer
-- ----------------------------
DROP TABLE IF EXISTS `rprojectretailer`;
CREATE TABLE `rprojectretailer` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ProjectID` int(11) NOT NULL,
  `RetailerID` int(11) NOT NULL,
  `IsActive` bit(1) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `ProjectID_fk1_idx` (`ProjectID`),
  KEY `RetailerID_fk2_idx` (`RetailerID`),
  CONSTRAINT `ProjectID_fk1` FOREIGN KEY (`ProjectID`) REFERENCES `project` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `RetailerID_fk2` FOREIGN KEY (`RetailerID`) REFERENCES `retailer` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='?-?????̹????;

-- ----------------------------
-- Table structure for rpromoterprovince
-- ----------------------------
DROP TABLE IF EXISTS `rpromoterprovince`;
CREATE TABLE `rpromoterprovince` (
  `ID` int(11) NOT NULL,
  `PromoterID` int(11) NOT NULL,
  `ProvinceID` int(11) NOT NULL,
  `IsActive` bit(1) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `PromoterID_idx` (`PromoterID`),
  KEY `ProvinceID_idx` (`ProvinceID`),
  CONSTRAINT `Promoter_ID` FOREIGN KEY (`PromoterID`) REFERENCES `promoter` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `Province_ID` FOREIGN KEY (`ProvinceID`) REFERENCES `province` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='?ƹ?Ա-ʡ?ݹ????;

-- ----------------------------
-- Table structure for salerecord
-- ----------------------------
DROP TABLE IF EXISTS `salerecord`;
CREATE TABLE `salerecord` (
  `ID` int(11) NOT NULL,
  `RetailerID` int(11) NOT NULL,
  `ProductID` int(11) NOT NULL,
  `TotalPrice` float NOT NULL,
  `ImporterID` int(11) NOT NULL,
  `SubmitDate` datetime NOT NULL,
  `ProjectID` int(11) NOT NULL,
  `Points` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `RetailerID_idx` (`RetailerID`),
  KEY `ProductSepcificationID_idx` (`ProductID`),
  KEY `ImporterID_idx` (`ImporterID`),
  KEY `ProjectID_idx` (`ProjectID`),
  CONSTRAINT `ImporterID_fk3` FOREIGN KEY (`ImporterID`) REFERENCES `promoter` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `ProductSepcificationID_fk2` FOREIGN KEY (`ProductID`) REFERENCES `product` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `ProjectID_fk4` FOREIGN KEY (`ProjectID`) REFERENCES `project` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `Retailer_fk1` FOREIGN KEY (`RetailerID`) REFERENCES `retailer` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='???ۼ?¼?;
SET FOREIGN_KEY_CHECKS=1;
