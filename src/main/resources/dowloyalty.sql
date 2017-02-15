/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50552
Source Host           : localhost:3306
Source Database       : dowloyalty

Target Server Type    : MYSQL
Target Server Version : 50552
File Encoding         : 65001

Date: 2017-02-15 15:34:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `admin`
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ChineseName` varchar(50) DEFAULT NULL,
  `IsActive` bit(1) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', 'eee', '');

-- ----------------------------
-- Table structure for `exchangerecord`
-- ----------------------------
DROP TABLE IF EXISTS `exchangerecord`;
CREATE TABLE `exchangerecord` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `RetailerID` int(11) NOT NULL,
  `GoodsID` int(11) NOT NULL,
  `Amount` int(11) NOT NULL,
  `SubmitTime` datetime NOT NULL,
  `SendOutTime` datetime DEFAULT NULL,
  `CompleteTime` datetime DEFAULT NULL,
  `Status` int(11) NOT NULL,
  `ExchangePoints` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `RetailerID_idx` (`RetailerID`),
  KEY `GoodsID_idx` (`GoodsID`),
  CONSTRAINT `GoodsID_fk2` FOREIGN KEY (`GoodsID`) REFERENCES `goods` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `RetailerID_fk1` FOREIGN KEY (`RetailerID`) REFERENCES `retailer` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of exchangerecord
-- ----------------------------
INSERT INTO `exchangerecord` VALUES ('1', '1', '1', '1', '2017-02-09 17:19:37', null, null, '0', '-10');
INSERT INTO `exchangerecord` VALUES ('2', '1', '1', '5', '2017-02-09 17:23:57', null, null, '0', '-50');
INSERT INTO `exchangerecord` VALUES ('3', '1', '1', '5', '2017-02-09 17:25:20', null, null, '0', '-50');
INSERT INTO `exchangerecord` VALUES ('4', '1', '1', '5', '2017-02-09 17:26:22', null, null, '0', '-50');
INSERT INTO `exchangerecord` VALUES ('5', '1', '1', '1', '2017-02-09 17:27:36', null, null, '0', '-10');
INSERT INTO `exchangerecord` VALUES ('6', '1', '1', '3', '2017-02-09 19:02:21', null, null, '0', '-30');
INSERT INTO `exchangerecord` VALUES ('7', '1', '4', '1', '2017-02-09 19:04:38', null, null, '0', '-20');
INSERT INTO `exchangerecord` VALUES ('8', '1', '2', '6', '2017-02-09 19:16:47', null, null, '0', '-138');
INSERT INTO `exchangerecord` VALUES ('9', '1', '4', '1', '2017-02-14 14:35:42', null, null, '0', '-20');
INSERT INTO `exchangerecord` VALUES ('10', '1', '1', '1', '2017-02-14 15:29:36', null, null, '0', '-10');

-- ----------------------------
-- Table structure for `exchangeshop`
-- ----------------------------
DROP TABLE IF EXISTS `exchangeshop`;
CREATE TABLE `exchangeshop` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ProjectID` int(11) NOT NULL,
  `GoodsID` int(11) NOT NULL,
  `ExchangePoints` int(11) NOT NULL,
  `IsActive` bit(1) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `GoodsID_idx` (`GoodsID`),
  KEY `FK_shopfkgoods_idx` (`ProjectID`) USING BTREE,
  CONSTRAINT `FK_shopfkgoods` FOREIGN KEY (`ProjectID`) REFERENCES `project` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `GoodsID` FOREIGN KEY (`GoodsID`) REFERENCES `goods` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of exchangeshop
-- ----------------------------
INSERT INTO `exchangeshop` VALUES ('1', '1', '1', '10', '');
INSERT INTO `exchangeshop` VALUES ('2', '1', '2', '23', '');
INSERT INTO `exchangeshop` VALUES ('3', '1', '3', '15', '');
INSERT INTO `exchangeshop` VALUES ('4', '1', '4', '20', '');
INSERT INTO `exchangeshop` VALUES ('5', '1', '5', '20', '');

-- ----------------------------
-- Table structure for `goods`
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(50) NOT NULL,
  `Description` varchar(255) DEFAULT NULL,
  `ImagePath` varchar(50) NOT NULL,
  `IsActive` bit(1) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES ('1', '1000元加油卡', '100元加油卡，共10张，可以在任何加油站使用', '/DowLoyalty/Resources/html/images/fuelCard.png', '');
INSERT INTO `goods` VALUES ('2', '海尔对开门冰箱', 'ssss', '/DowLoyalty/Resources/html/images/fridge.png', '');
INSERT INTO `goods` VALUES ('3', '海尔变频滚筒洗衣机', null, '/DowLoyalty/Resources/html/images/washMachine.png', '');
INSERT INTO `goods` VALUES ('4', '格力1.5匹节能空调', null, '/DowLoyalty/Resources/html/images/airCondition.png', '');
INSERT INTO `goods` VALUES ('5', 'dfdfd', '12312', '/DowLoyalty/Resources/html/images/fuelCard.png', '');

-- ----------------------------
-- Table structure for `points`
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of points
-- ----------------------------

-- ----------------------------
-- Table structure for `pointslevel`
-- ----------------------------
DROP TABLE IF EXISTS `pointslevel`;
CREATE TABLE `pointslevel` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `ProjectID` int(11) NOT NULL,
  `Name` varchar(50) NOT NULL,
  `Points` int(11) NOT NULL,
  `IsActive` bit(1) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `ProjectID` (`ProjectID`),
  CONSTRAINT `pointslevel_ibfk_1` FOREIGN KEY (`ProjectID`) REFERENCES `project` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pointslevel
-- ----------------------------
INSERT INTO `pointslevel` VALUES ('1', '1', '金牌', '300', '');
INSERT INTO `pointslevel` VALUES ('2', '1', '钻石', '400', '');
INSERT INTO `pointslevel` VALUES ('3', '1', '铜牌', '100', '');
INSERT INTO `pointslevel` VALUES ('4', '1', '银牌', '200', '');
INSERT INTO `pointslevel` VALUES ('5', '1', '普通', '0', '');

-- ----------------------------
-- Table structure for `product`
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SFDCCode` int(11) DEFAULT NULL,
  `ProductFamilyID` int(11) NOT NULL,
  `Name` varchar(50) NOT NULL,
  `Description` varchar(50) NOT NULL,
  `LastModifiedDate` datetime DEFAULT NULL,
  `IsActive` bit(1) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `ProductFamilyID_idx` (`ProductFamilyID`) USING BTREE,
  CONSTRAINT `ProductFamilyID` FOREIGN KEY (`ProductFamilyID`) REFERENCES `productfamily` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('1', '111', '1', 'sss', 'ssss', '2017-02-10 12:11:11', '');
INSERT INTO `product` VALUES ('2', '222', '1', '333', '444', '2017-02-16 13:30:44', '');
INSERT INTO `product` VALUES ('3', '333', '1', 'aaa', 'ddd', '2017-02-15 13:31:06', '');

-- ----------------------------
-- Table structure for `productcategory`
-- ----------------------------
DROP TABLE IF EXISTS `productcategory`;
CREATE TABLE `productcategory` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(50) CHARACTER SET utf8 NOT NULL,
  `Description` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `IsActive` bit(1) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of productcategory
-- ----------------------------
INSERT INTO `productcategory` VALUES ('1', 'rrr', 'qqq', '');

-- ----------------------------
-- Table structure for `productfamily`
-- ----------------------------
DROP TABLE IF EXISTS `productfamily`;
CREATE TABLE `productfamily` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(50) NOT NULL,
  `Description` varchar(200) DEFAULT NULL,
  `IsActive` bit(1) NOT NULL,
  `CategroyID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `CategroyID` (`CategroyID`),
  CONSTRAINT `CategroyID` FOREIGN KEY (`CategroyID`) REFERENCES `productcategory` (`ID`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of productfamily
-- ----------------------------
INSERT INTO `productfamily` VALUES ('1', 'ddd', 'aaa', '', '1');

-- ----------------------------
-- Table structure for `project`
-- ----------------------------
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(50) NOT NULL,
  `ProvinceID` int(11) NOT NULL,
  `PlacardPath` varchar(50) NOT NULL,
  `AssistantID` int(11) DEFAULT NULL,
  `StartDate` datetime NOT NULL,
  `EndDate` datetime NOT NULL,
  `AdminID` int(11) NOT NULL,
  `IsVisible` bit(1) NOT NULL,
  `IsActive` bit(1) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `ProvinceID_idx` (`ProvinceID`),
  KEY `AssistantID_idx` (`AssistantID`),
  KEY `AdminID_idx` (`AdminID`),
  CONSTRAINT `AdminID` FOREIGN KEY (`AdminID`) REFERENCES `admin` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `AssistantID` FOREIGN KEY (`AssistantID`) REFERENCES `promoter` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `ProvinceID` FOREIGN KEY (`ProvinceID`) REFERENCES `province` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of project
-- ----------------------------
INSERT INTO `project` VALUES ('1', '1', '1', '1', '1', '2017-02-08 15:02:48', '2017-02-09 15:02:52', '1', '', '');

-- ----------------------------
-- Table structure for `promoter`
-- ----------------------------
DROP TABLE IF EXISTS `promoter`;
CREATE TABLE `promoter` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ChineseName` varchar(50) DEFAULT NULL,
  `UserId` varchar(50) NOT NULL,
  `IsActive` bit(1) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of promoter
-- ----------------------------
INSERT INTO `promoter` VALUES ('1', 'www', '1', '');

-- ----------------------------
-- Table structure for `province`
-- ----------------------------
DROP TABLE IF EXISTS `province`;
CREATE TABLE `province` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(50) NOT NULL,
  `IsActive` bit(1) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of province
-- ----------------------------
INSERT INTO `province` VALUES ('1', 'ttt', '');

-- ----------------------------
-- Table structure for `retailer`
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of retailer
-- ----------------------------
INSERT INTO `retailer` VALUES ('1', 'rrr', '1', null, null, null, null, '333', null, '');
INSERT INTO `retailer` VALUES ('2', 'eee', '1', null, null, null, null, '444', null, '');
INSERT INTO `retailer` VALUES ('3', 'qqq', '1', null, null, null, null, '555', null, '');
INSERT INTO `retailer` VALUES ('4', 'ttt', '1', null, null, null, null, '666', null, '');

-- ----------------------------
-- Table structure for `rprojectpromoter`
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rprojectpromoter
-- ----------------------------

-- ----------------------------
-- Table structure for `rprojectretailer`
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rprojectretailer
-- ----------------------------
INSERT INTO `rprojectretailer` VALUES ('1', '1', '1', '');

-- ----------------------------
-- Table structure for `rpromoterprovince`
-- ----------------------------
DROP TABLE IF EXISTS `rpromoterprovince`;
CREATE TABLE `rpromoterprovince` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `PromoterID` int(11) NOT NULL,
  `ProvinceID` int(11) NOT NULL,
  `IsActive` bit(1) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `PromoterID_idx` (`PromoterID`),
  KEY `ProvinceID_idx` (`ProvinceID`),
  CONSTRAINT `Promoter_ID` FOREIGN KEY (`PromoterID`) REFERENCES `promoter` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `Province_ID` FOREIGN KEY (`ProvinceID`) REFERENCES `province` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rpromoterprovince
-- ----------------------------

-- ----------------------------
-- Table structure for `salerecord`
-- ----------------------------
DROP TABLE IF EXISTS `salerecord`;
CREATE TABLE `salerecord` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `RetailerID` int(11) NOT NULL,
  `ProductID` int(11) NOT NULL,
  `TotalPrice` float NOT NULL,
  `ImporterID` int(11) NOT NULL,
  `SubmitDate` datetime NOT NULL,
  `ProjectID` int(11) DEFAULT NULL,
  `Points` int(11) DEFAULT NULL,
  `Amount` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `RetailerID_idx` (`RetailerID`),
  KEY `ProductSepcificationID_idx` (`ProductID`),
  KEY `ImporterID_idx` (`ImporterID`),
  KEY `ProjectID_idx` (`ProjectID`),
  CONSTRAINT `ImporterID_fk3` FOREIGN KEY (`ImporterID`) REFERENCES `promoter` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `ProductSepcificationID_fk2` FOREIGN KEY (`ProductID`) REFERENCES `product` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `ProjectID_fk4` FOREIGN KEY (`ProjectID`) REFERENCES `project` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `Retailer_fk1` FOREIGN KEY (`RetailerID`) REFERENCES `retailer` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of salerecord
-- ----------------------------
INSERT INTO `salerecord` VALUES ('1', '1', '1', '50', '1', '2017-02-23 12:12:15', '1', '50', '1');
INSERT INTO `salerecord` VALUES ('2', '1', '1', '100', '1', '2017-01-12 13:12:55', '1', '20', '2');
INSERT INTO `salerecord` VALUES ('3', '2', '1', '100', '1', '2017-02-14 13:11:34', '1', '100', '2');
INSERT INTO `salerecord` VALUES ('4', '2', '1', '200', '1', '2017-02-14 13:12:02', '1', '200', '4');
INSERT INTO `salerecord` VALUES ('5', '3', '1', '200', '1', '2017-02-13 13:12:22', '1', '200', '4');
INSERT INTO `salerecord` VALUES ('6', '3', '1', '300', '1', '2017-02-16 13:12:44', '1', '300', '5');
INSERT INTO `salerecord` VALUES ('7', '4', '1', '200', '1', '2017-02-15 13:13:04', '1', '200', '4');
INSERT INTO `salerecord` VALUES ('8', '4', '1', '250', '1', '2017-02-13 13:13:25', '1', '250', '5');
