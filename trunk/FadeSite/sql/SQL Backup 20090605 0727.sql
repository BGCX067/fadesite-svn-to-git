-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.1.34-community


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema fadesite
--

CREATE DATABASE IF NOT EXISTS fadesite;
USE fadesite;

--
-- Definition of table `activity`
--

DROP TABLE IF EXISTS `activity`;
CREATE TABLE `activity` (
  `ActivityID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `UserID` int(10) unsigned NOT NULL,
  `StartDate` datetime NOT NULL,
  `EndDate` datetime NOT NULL,
  `Name` varchar(45) NOT NULL,
  `Location` varchar(45) NOT NULL,
  `Organisation` varchar(45) NOT NULL,
  `Description` varchar(200) NOT NULL,
  `Position` varchar(45) NOT NULL,
  `IsDeleted` tinyint(1) NOT NULL,
  `Project` varchar(45) NOT NULL,
  PRIMARY KEY (`ActivityID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `activity`
--

/*!40000 ALTER TABLE `activity` DISABLE KEYS */;
INSERT INTO `activity` (`ActivityID`,`UserID`,`StartDate`,`EndDate`,`Name`,`Location`,`Organisation`,`Description`,`Position`,`IsDeleted`,`Project`) VALUES 
 (1,5,'2009-01-01 00:00:00','2009-06-30 00:00:00','Study','UNSW','UNSW','Best days of my life.','Student',0,'Lots'),
 (2,5,'2008-12-04 00:00:00','2009-06-26 00:00:00','Job #1','Sydney','HiT','FIRST JOB EVER','Lackey',0,''),
 (3,4,'2009-09-05 00:00:00','2009-09-05 00:00:00','','a','a','','a',0,''),
 (4,4,'2009-06-03 00:00:00','2009-06-03 00:00:00','','a','b','','c',0,''),
 (5,5,'1987-02-16 00:00:00','2009-06-05 00:00:00','Activity 3','Bexly North','Wong Family','Hardest Job in my life.','Brother',0,'Siblings');
/*!40000 ALTER TABLE `activity` ENABLE KEYS */;


--
-- Definition of table `announcement`
--

DROP TABLE IF EXISTS `announcement`;
CREATE TABLE `announcement` (
  `AnnounceID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `StartDate` datetime NOT NULL,
  `ExpireDate` datetime NOT NULL,
  `CreatedByUser` int(10) unsigned NOT NULL,
  `UpdatedByUser` int(10) unsigned NOT NULL,
  `CreateDate` datetime NOT NULL,
  `UpdateDate` datetime NOT NULL,
  `Title` varchar(45) NOT NULL,
  `Description` varchar(200) NOT NULL,
  `IsApproved` tinyint(1) NOT NULL,
  `AnnounceType` int(10) unsigned NOT NULL,
  `IsDeleted` tinyint(1) NOT NULL,
  PRIMARY KEY (`AnnounceID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `announcement`
--

/*!40000 ALTER TABLE `announcement` DISABLE KEYS */;
INSERT INTO `announcement` (`AnnounceID`,`StartDate`,`ExpireDate`,`CreatedByUser`,`UpdatedByUser`,`CreateDate`,`UpdateDate`,`Title`,`Description`,`IsApproved`,`AnnounceType`,`IsDeleted`) VALUES 
 (4,'2009-06-17 00:00:00','2009-06-23 00:00:00',5,5,'2009-06-04 00:00:00','2009-06-05 00:00:00','Announcement to You','Get some sleep.',1,1,0),
 (5,'2009-06-05 00:00:00','2009-06-05 00:00:00',5,5,'2009-06-05 00:00:00','2009-06-05 00:00:00','Party','PARTY AT MY HOUSE<br />\r\nTODAY!!!!<br />\r\nBE THERE OR BE SQUARE\r\n\r\ntest',1,1,0),
 (6,'2009-06-02 00:00:00','2009-06-11 00:00:00',5,5,'2009-06-05 00:00:00','2009-06-05 00:00:00','test','test',0,1,1),
 (7,'2009-06-10 00:00:00','2009-06-10 00:00:00',5,5,'2009-06-05 00:00:00','2009-06-05 00:00:00','LOVE THIS GROUP','Best group in the world.',1,1,0),
 (8,'2009-06-05 00:00:00','2009-06-06 00:00:00',5,5,'2009-06-05 00:00:00','2009-06-05 00:00:00','Attention','You are the best damn event ever.',1,1,0),
 (9,'2009-06-01 00:00:00','2009-06-02 00:00:00',5,5,'2009-06-05 00:00:00','2009-06-05 00:00:00','old announce','Listed last?',1,1,0);
/*!40000 ALTER TABLE `announcement` ENABLE KEYS */;


--
-- Definition of table `announcementtype`
--

DROP TABLE IF EXISTS `announcementtype`;
CREATE TABLE `announcementtype` (
  `AnnouncementTypeID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  PRIMARY KEY (`AnnouncementTypeID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `announcementtype`
--

/*!40000 ALTER TABLE `announcementtype` DISABLE KEYS */;
/*!40000 ALTER TABLE `announcementtype` ENABLE KEYS */;


--
-- Definition of table `announcementupdate`
--

DROP TABLE IF EXISTS `announcementupdate`;
CREATE TABLE `announcementupdate` (
  `AnnounceUpdateID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `AnnouncementType` int(10) unsigned NOT NULL,
  `Description` varchar(200) NOT NULL,
  `UpdateDate` datetime NOT NULL,
  `UpdateUser` int(10) unsigned NOT NULL,
  `Title` varchar(45) NOT NULL,
  `IsApproved` tinyint(1) NOT NULL,
  `isDeleted` tinyint(1) NOT NULL,
  `ExpireDate` datetime NOT NULL,
  `StartDate` datetime NOT NULL,
  `AnnounceID` int(10) unsigned NOT NULL,
  PRIMARY KEY (`AnnounceUpdateID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `announcementupdate`
--

/*!40000 ALTER TABLE `announcementupdate` DISABLE KEYS */;
INSERT INTO `announcementupdate` (`AnnounceUpdateID`,`AnnouncementType`,`Description`,`UpdateDate`,`UpdateUser`,`Title`,`IsApproved`,`isDeleted`,`ExpireDate`,`StartDate`,`AnnounceID`) VALUES 
 (1,1,'Update\r\n','2009-06-05 00:00:00',5,'test',0,1,'2009-06-23 00:00:00','2009-06-17 00:00:00',4),
 (2,1,'PARTY AT MY HOUSE<br />\r\nTODAY!!!!<br />\r\nBE THERE OR BE SQUARE\r\n\r\ntest','2009-06-05 00:00:00',5,'Part',1,1,'2009-06-05 00:00:00','2009-06-05 00:00:00',5),
 (3,1,'Don\'t miss out.\r\nJoin now!','2009-06-05 00:00:00',5,'Event.1',1,1,'2009-06-23 00:00:00','2009-06-17 00:00:00',4),
 (4,1,'Get some sleep.','2009-06-05 00:00:00',5,'Announcement to You',1,1,'2009-06-23 00:00:00','2009-06-17 00:00:00',4),
 (5,1,'PARTY AT MY HOUSE<br />\r\nTODAY!!!!<br />\r\nBE THERE OR BE SQUARE\r\n\r\ntest','2009-06-05 00:00:00',5,'Party',1,1,'2009-06-05 00:00:00','2009-06-05 00:00:00',5),
 (6,1,'Listed last?','2009-06-05 00:00:00',5,'old announce',1,1,'2009-06-02 00:00:00','2009-06-01 00:00:00',9);
/*!40000 ALTER TABLE `announcementupdate` ENABLE KEYS */;


--
-- Definition of table `degree`
--

DROP TABLE IF EXISTS `degree`;
CREATE TABLE `degree` (
  `DegreeID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `DegreeName` varchar(45) NOT NULL,
  PRIMARY KEY (`DegreeID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `degree`
--

/*!40000 ALTER TABLE `degree` DISABLE KEYS */;
INSERT INTO `degree` (`DegreeID`,`DegreeName`) VALUES 
 (1,'Degree of Acuteness'),
 (2,'Bachelor'),
 (3,'Masters'),
 (4,'Doctorate'),
 (5,'Diploma'),
 (6,'Graduate'),
 (7,'Obtuse'),
 (8,'Right Angle');
/*!40000 ALTER TABLE `degree` ENABLE KEYS */;


--
-- Definition of table `event`
--

DROP TABLE IF EXISTS `event`;
CREATE TABLE `event` (
  `EventID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `IsDeleted` tinyint(1) NOT NULL,
  `IsApproved` tinyint(1) NOT NULL,
  `StartDate` datetime NOT NULL,
  `ExpireDate` datetime NOT NULL,
  `CreatedByUser` int(10) unsigned NOT NULL,
  `UpdatedByUser` int(10) unsigned NOT NULL,
  `CreateDate` datetime NOT NULL,
  `UpdateDate` datetime NOT NULL,
  `Title` varchar(45) NOT NULL,
  `Description` varchar(200) NOT NULL,
  `Location` varchar(45) NOT NULL,
  PRIMARY KEY (`EventID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `event`
--

/*!40000 ALTER TABLE `event` DISABLE KEYS */;
INSERT INTO `event` (`EventID`,`IsDeleted`,`IsApproved`,`StartDate`,`ExpireDate`,`CreatedByUser`,`UpdatedByUser`,`CreateDate`,`UpdateDate`,`Title`,`Description`,`Location`) VALUES 
 (1,0,1,'2009-06-05 00:00:00','2009-06-05 00:00:00',5,5,'2009-06-05 00:00:00','2009-06-05 00:00:00','CS9321 Assignment DUE DATE','Assignment Presentation!\r\ntest','UNSW'),
 (2,0,1,'2009-06-06 00:00:00','2009-06-07 00:00:00',5,5,'2009-06-05 00:00:00','2009-06-05 00:00:00','Party Time','PARTY, ALL WEEKEND','My Place');
/*!40000 ALTER TABLE `event` ENABLE KEYS */;


--
-- Definition of table `eventannouncement`
--

DROP TABLE IF EXISTS `eventannouncement`;
CREATE TABLE `eventannouncement` (
  `EventAnnouncementID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `AnnouncementID` int(10) unsigned NOT NULL,
  `EventID` int(10) unsigned NOT NULL,
  `isDeleted` tinyint(1) NOT NULL,
  PRIMARY KEY (`EventAnnouncementID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `eventannouncement`
--

/*!40000 ALTER TABLE `eventannouncement` DISABLE KEYS */;
INSERT INTO `eventannouncement` (`EventAnnouncementID`,`AnnouncementID`,`EventID`,`isDeleted`) VALUES 
 (1,8,1,0);
/*!40000 ALTER TABLE `eventannouncement` ENABLE KEYS */;


--
-- Definition of table `eventinvitation`
--

DROP TABLE IF EXISTS `eventinvitation`;
CREATE TABLE `eventinvitation` (
  `EventInviteID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `InviterID` int(10) unsigned NOT NULL,
  `InviteeID` int(10) unsigned NOT NULL,
  `EventID` int(10) unsigned NOT NULL,
  `isDeleted` tinyint(1) NOT NULL,
  `InviteAccepted` tinyint(1) NOT NULL,
  PRIMARY KEY (`EventInviteID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `eventinvitation`
--

/*!40000 ALTER TABLE `eventinvitation` DISABLE KEYS */;
INSERT INTO `eventinvitation` (`EventInviteID`,`InviterID`,`InviteeID`,`EventID`,`isDeleted`,`InviteAccepted`) VALUES 
 (1,5,1,1,0,0);
/*!40000 ALTER TABLE `eventinvitation` ENABLE KEYS */;


--
-- Definition of table `eventupdate`
--

DROP TABLE IF EXISTS `eventupdate`;
CREATE TABLE `eventupdate` (
  `EventUpdateID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `IsDeleted` tinyint(1) NOT NULL,
  `IsApproved` tinyint(1) NOT NULL,
  `StartDate` datetime NOT NULL,
  `ExpireDate` datetime NOT NULL,
  `UpdatedByUser` int(10) unsigned NOT NULL,
  `UpdateDate` datetime NOT NULL,
  `Title` varchar(45) NOT NULL,
  `Description` varchar(200) NOT NULL,
  `EventID` int(10) unsigned NOT NULL,
  `Location` varchar(45) NOT NULL,
  PRIMARY KEY (`EventUpdateID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `eventupdate`
--

/*!40000 ALTER TABLE `eventupdate` DISABLE KEYS */;
INSERT INTO `eventupdate` (`EventUpdateID`,`IsDeleted`,`IsApproved`,`StartDate`,`ExpireDate`,`UpdatedByUser`,`UpdateDate`,`Title`,`Description`,`EventID`,`Location`) VALUES 
 (1,1,1,'2009-06-05 00:00:00','2009-06-05 00:00:00',5,'2009-06-05 00:00:00','CS9321 Assignment DUE DATE','Assignment Presentation!\r\ntest',1,'UNSW'),
 (2,1,0,'2009-06-06 00:00:00','2009-06-07 00:00:00',5,'2009-06-05 00:00:00','Party Time','PARTY, ALL WEEKEND\r\n\r\nBYO',2,'My Place');
/*!40000 ALTER TABLE `eventupdate` ENABLE KEYS */;


--
-- Definition of table `group`
--

DROP TABLE IF EXISTS `group`;
CREATE TABLE `group` (
  `GroupID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) NOT NULL,
  `Description` varchar(200) NOT NULL,
  `IsDeleted` tinyint(1) NOT NULL,
  PRIMARY KEY (`GroupID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `group`
--

/*!40000 ALTER TABLE `group` DISABLE KEYS */;
INSERT INTO `group` (`GroupID`,`Name`,`Description`,`IsDeleted`) VALUES 
 (1,'The Best','Only the best may join.',0),
 (2,'The Nerds','1337 haxx0rs',0),
 (3,'The Jocks','SPORTS RULE!!!!',0),
 (4,'Biggest Group','IN ALL OF EVERYWHERE',0);
/*!40000 ALTER TABLE `group` ENABLE KEYS */;


--
-- Definition of table `groupannounce`
--

DROP TABLE IF EXISTS `groupannounce`;
CREATE TABLE `groupannounce` (
  `GroupAnnounceID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `AnnounceID` varchar(45) NOT NULL,
  `GroupID` varchar(45) NOT NULL,
  `IsDeleted` tinyint(1) NOT NULL,
  PRIMARY KEY (`GroupAnnounceID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `groupannounce`
--

/*!40000 ALTER TABLE `groupannounce` DISABLE KEYS */;
INSERT INTO `groupannounce` (`GroupAnnounceID`,`AnnounceID`,`GroupID`,`IsDeleted`) VALUES 
 (1,'7','1',0);
/*!40000 ALTER TABLE `groupannounce` ENABLE KEYS */;


--
-- Definition of table `groupevent`
--

DROP TABLE IF EXISTS `groupevent`;
CREATE TABLE `groupevent` (
  `GroupEventID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `EventID` varchar(45) NOT NULL,
  `GroupID` varchar(45) NOT NULL,
  `isDeleted` varchar(45) NOT NULL,
  PRIMARY KEY (`GroupEventID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `groupevent`
--

/*!40000 ALTER TABLE `groupevent` DISABLE KEYS */;
/*!40000 ALTER TABLE `groupevent` ENABLE KEYS */;


--
-- Definition of table `groupinvitation`
--

DROP TABLE IF EXISTS `groupinvitation`;
CREATE TABLE `groupinvitation` (
  `GroupInviteID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `GroupID` int(10) unsigned NOT NULL,
  `InviterID` int(10) unsigned NOT NULL,
  `InviteeID` int(10) unsigned NOT NULL,
  `IsDeleted` tinyint(1) NOT NULL,
  `InviteAccepted` tinyint(1) NOT NULL,
  PRIMARY KEY (`GroupInviteID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `groupinvitation`
--

/*!40000 ALTER TABLE `groupinvitation` DISABLE KEYS */;
INSERT INTO `groupinvitation` (`GroupInviteID`,`GroupID`,`InviterID`,`InviteeID`,`IsDeleted`,`InviteAccepted`) VALUES 
 (1,4,5,1,1,0),
 (2,4,5,2,0,0),
 (3,4,5,3,0,0),
 (4,4,5,4,0,0);
/*!40000 ALTER TABLE `groupinvitation` ENABLE KEYS */;


--
-- Definition of table `message`
--

DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `MessageID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `SendeeID` int(10) unsigned NOT NULL,
  `SenderID` int(10) unsigned NOT NULL,
  `Contents` varchar(2000) NOT NULL,
  `IsRead` tinyint(1) NOT NULL,
  `IsDeleted` tinyint(1) NOT NULL,
  `DeletedBySender` tinyint(1) NOT NULL,
  `DeletedBySendee` tinyint(1) NOT NULL,
  `Title` varchar(45) NOT NULL,
  PRIMARY KEY (`MessageID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `message`
--

/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` (`MessageID`,`SendeeID`,`SenderID`,`Contents`,`IsRead`,`IsDeleted`,`DeletedBySender`,`DeletedBySendee`,`Title`) VALUES 
 (1,5,5,'test',1,0,1,1,'test'),
 (2,5,5,'SEXY',1,0,1,1,'HI '),
 (3,4,5,'Dear Friend.\r\n\r\nAs you read this, I don\'t want you to feel sorry for me, because, I believe everyone will die someday.\r\n\r\nMy name is Peter Lawson,a merchant in Dubai, in the U.A.E.I have been diagnosed with Esophageal Cancer which was discovered very late,due to my laxity in carrying for my health. It has defiled all forms of medicine, and right now I have only about a few months to live, according to medical experts.',0,0,0,0,'DEAR FRIEND'),
 (4,5,4,'PLEASE',1,0,0,0,'READ THIS'),
 (5,5,4,'AGAIN',1,0,0,0,'READ THIS #2'),
 (6,5,4,'THIRD TIME LUCKY',1,0,0,0,'READ THIS #3'),
 (7,5,4,'REVERSE THINKING THING',0,0,0,0,'DONT READ THIS'),
 (8,5,4,'BORED NOW',0,0,0,0,'Test');
/*!40000 ALTER TABLE `message` ENABLE KEYS */;


--
-- Definition of table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `UserID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Firstname` varchar(45) DEFAULT NULL,
  `Surname` varchar(45) DEFAULT NULL,
  `Username` varchar(45) NOT NULL,
  `DateOfBirth` datetime NOT NULL,
  `ReferenceID` int(10) unsigned NOT NULL,
  `Location` varchar(45) DEFAULT NULL,
  `Gender` varchar(45) NOT NULL,
  `Password` varchar(45) NOT NULL,
  `EmailAddress` varchar(45) NOT NULL,
  `IsActivated` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `IsDeleted` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `UserTypeID` int(10) unsigned NOT NULL DEFAULT '0',
  `Address` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`UserID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`UserID`,`Firstname`,`Surname`,`Username`,`DateOfBirth`,`ReferenceID`,`Location`,`Gender`,`Password`,`EmailAddress`,`IsActivated`,`IsDeleted`,`UserTypeID`,`Address`) VALUES 
 (1,'Arman','Mirko','AMirko','2005-05-16 00:00:00',899846351,'Perth','Male','ARMENROCKS','arman@armenia.am',1,0,3,'Armenia'),
 (2,'Cathan','Alfred','Elementary','1999-09-19 00:00:00',13581119,'Canberra','Female','Watson','Dear@Watson.My',1,0,2,'Scotland Yard'),
 (3,'Sandy','Ramon','ITDEST','1555-05-15 00:00:00',555755555,'Adelaide','Female','Discount','sandy@itdest.com',1,0,1,'World Square'),
 (4,'Murtagh','Vinko','SayMyName','1000-01-01 00:00:00',0,'Melbourne','Male','lolwut','mrgaghrgh@mur.loc',1,0,1,'The Sea'),
 (5,'Eric Tze Wai','Wong','Eric','2009-06-25 00:00:00',3159862,'Sydney','Male','test123','mail.eric.wong@gmail.com',1,0,1,'Here');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;


--
-- Definition of table `useractivation`
--

DROP TABLE IF EXISTS `useractivation`;
CREATE TABLE `useractivation` (
  `UserActivationID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Token` varchar(45) NOT NULL,
  `IsDeleted` tinyint(1) NOT NULL DEFAULT '0',
  `UserID` int(10) unsigned NOT NULL,
  PRIMARY KEY (`UserActivationID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `useractivation`
--

/*!40000 ALTER TABLE `useractivation` DISABLE KEYS */;
INSERT INTO `useractivation` (`UserActivationID`,`Token`,`IsDeleted`,`UserID`) VALUES 
 (9,'joa13g0rn89780a1cnt2esoc52',1,4),
 (10,'c3krieiub3d1lc7t79gpocv5tu',1,5);
/*!40000 ALTER TABLE `useractivation` ENABLE KEYS */;


--
-- Definition of table `userban`
--

DROP TABLE IF EXISTS `userban`;
CREATE TABLE `userban` (
  `UserBanID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `UserID` int(10) unsigned NOT NULL,
  `StartDate` datetime NOT NULL,
  `ExpireDate` datetime NOT NULL,
  `Description` varchar(200) NOT NULL,
  `IsDeleted` tinyint(1) NOT NULL,
  PRIMARY KEY (`UserBanID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `userban`
--

/*!40000 ALTER TABLE `userban` DISABLE KEYS */;
INSERT INTO `userban` (`UserBanID`,`UserID`,`StartDate`,`ExpireDate`,`Description`,`IsDeleted`) VALUES 
 (1,5,'2009-06-01 00:00:00','2009-06-02 00:00:00','YOU SUCK',0),
 (2,3,'2009-06-01 00:00:00','2009-06-30 00:00:00','Product Placement',1);
/*!40000 ALTER TABLE `userban` ENABLE KEYS */;


--
-- Definition of table `userdegree`
--

DROP TABLE IF EXISTS `userdegree`;
CREATE TABLE `userdegree` (
  `UserDegreeID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `UserID` int(10) unsigned NOT NULL,
  `DegreeID` int(10) unsigned NOT NULL,
  `GraduatingDate` datetime NOT NULL,
  `IsDeleted` tinyint(1) NOT NULL,
  PRIMARY KEY (`UserDegreeID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `userdegree`
--

/*!40000 ALTER TABLE `userdegree` DISABLE KEYS */;
INSERT INTO `userdegree` (`UserDegreeID`,`UserID`,`DegreeID`,`GraduatingDate`,`IsDeleted`) VALUES 
 (2,5,1,'2009-06-03 00:00:00',0),
 (3,5,2,'2008-12-09 00:00:00',0),
 (4,1,1,'2000-01-01 00:00:00',0),
 (5,2,2,'2001-02-02 00:00:00',0),
 (6,3,3,'2003-03-03 00:00:00',0),
 (7,4,4,'2004-04-04 00:00:00',0);
/*!40000 ALTER TABLE `userdegree` ENABLE KEYS */;


--
-- Definition of table `userevent`
--

DROP TABLE IF EXISTS `userevent`;
CREATE TABLE `userevent` (
  `UserEventID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `UserID` int(10) unsigned NOT NULL,
  `EventID` int(10) unsigned NOT NULL,
  `IsDeleted` tinyint(1) NOT NULL,
  PRIMARY KEY (`UserEventID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `userevent`
--

/*!40000 ALTER TABLE `userevent` DISABLE KEYS */;
INSERT INTO `userevent` (`UserEventID`,`UserID`,`EventID`,`IsDeleted`) VALUES 
 (1,5,1,0),
 (2,5,2,1);
/*!40000 ALTER TABLE `userevent` ENABLE KEYS */;


--
-- Definition of table `usergroup`
--

DROP TABLE IF EXISTS `usergroup`;
CREATE TABLE `usergroup` (
  `UserGroupID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `UserID` int(10) unsigned NOT NULL,
  `GroupID` int(10) unsigned NOT NULL,
  `isDeleted` tinyint(1) NOT NULL,
  PRIMARY KEY (`UserGroupID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `usergroup`
--

/*!40000 ALTER TABLE `usergroup` DISABLE KEYS */;
INSERT INTO `usergroup` (`UserGroupID`,`UserID`,`GroupID`,`isDeleted`) VALUES 
 (1,1,1,0),
 (2,2,1,0),
 (3,1,2,0),
 (4,5,3,0),
 (5,5,1,1),
 (6,5,2,0),
 (7,5,4,0),
 (8,4,4,0),
 (9,5,1,0);
/*!40000 ALTER TABLE `usergroup` ENABLE KEYS */;


--
-- Definition of table `usertemporarypassword`
--

DROP TABLE IF EXISTS `usertemporarypassword`;
CREATE TABLE `usertemporarypassword` (
  `UserTemporaryPasswordID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `UserID` int(10) unsigned NOT NULL,
  `Password` varchar(45) NOT NULL,
  `DateCreated` datetime NOT NULL,
  `IsDeleted` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`UserTemporaryPasswordID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `usertemporarypassword`
--

/*!40000 ALTER TABLE `usertemporarypassword` DISABLE KEYS */;
INSERT INTO `usertemporarypassword` (`UserTemporaryPasswordID`,`UserID`,`Password`,`DateCreated`,`IsDeleted`) VALUES 
 (1,4,'339r78982ugh8lgd31huo55n60','2009-06-04 00:00:00',1);
/*!40000 ALTER TABLE `usertemporarypassword` ENABLE KEYS */;


--
-- Definition of table `usertype`
--

DROP TABLE IF EXISTS `usertype`;
CREATE TABLE `usertype` (
  `UserTypeID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) NOT NULL,
  PRIMARY KEY (`UserTypeID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `usertype`
--

/*!40000 ALTER TABLE `usertype` DISABLE KEYS */;
/*!40000 ALTER TABLE `usertype` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
