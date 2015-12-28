DROP TABLE IF EXISTS `fadesite`.`activity`;
CREATE TABLE  `fadesite`.`activity` (
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `fadesite`.`announcement`;
CREATE TABLE  `fadesite`.`announcement` (
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `fadesite`.`announcementtype`;
CREATE TABLE  `fadesite`.`announcementtype` (
  `AnnouncementTypeID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  PRIMARY KEY (`AnnouncementTypeID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `fadesite`.`announcementupdate`;
CREATE TABLE  `fadesite`.`announcementupdate` (
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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `fadesite`.`degree`;
CREATE TABLE  `fadesite`.`degree` (
  `DegreeID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `DegreeName` varchar(45) NOT NULL,
  PRIMARY KEY (`DegreeID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `fadesite`.`event`;
CREATE TABLE  `fadesite`.`event` (
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `fadesite`.`eventannouncement`;
CREATE TABLE  `fadesite`.`eventannouncement` (
  `EventAnnouncementID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `AnnouncementID` int(10) unsigned NOT NULL,
  `EventID` int(10) unsigned NOT NULL,
  `isDeleted` tinyint(1) NOT NULL,
  PRIMARY KEY (`EventAnnouncementID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `fadesite`.`eventinvitation`;
CREATE TABLE  `fadesite`.`eventinvitation` (
  `EventInviteID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `InviterID` int(10) unsigned NOT NULL,
  `InviteeID` int(10) unsigned NOT NULL,
  `EventID` int(10) unsigned NOT NULL,
  `isDeleted` tinyint(1) NOT NULL,
  `InviteAccepted` tinyint(1) NOT NULL,
  PRIMARY KEY (`EventInviteID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `fadesite`.`eventupdate`;
CREATE TABLE  `fadesite`.`eventupdate` (
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `fadesite`.`group`;
CREATE TABLE  `fadesite`.`group` (
  `GroupID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) NOT NULL,
  `Description` varchar(200) NOT NULL,
  `IsDeleted` tinyint(1) NOT NULL,
  PRIMARY KEY (`GroupID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `fadesite`.`groupannounce`;
CREATE TABLE  `fadesite`.`groupannounce` (
  `GroupAnnounceID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `AnnounceID` varchar(45) NOT NULL,
  `GroupID` varchar(45) NOT NULL,
  `IsDeleted` tinyint(1) NOT NULL,
  PRIMARY KEY (`GroupAnnounceID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `fadesite`.`groupevent`;
CREATE TABLE  `fadesite`.`groupevent` (
  `GroupEventID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `EventID` varchar(45) NOT NULL,
  `GroupID` varchar(45) NOT NULL,
  `isDeleted` varchar(45) NOT NULL,
  PRIMARY KEY (`GroupEventID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `fadesite`.`groupinvitation`;
CREATE TABLE  `fadesite`.`groupinvitation` (
  `GroupInviteID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `GroupID` int(10) unsigned NOT NULL,
  `InviterID` int(10) unsigned NOT NULL,
  `InviteeID` int(10) unsigned NOT NULL,
  `IsDeleted` tinyint(1) NOT NULL,
  `InviteAccepted` tinyint(1) NOT NULL,
  PRIMARY KEY (`GroupInviteID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `fadesite`.`message`;
CREATE TABLE  `fadesite`.`message` (
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

DROP TABLE IF EXISTS `fadesite`.`user`;
CREATE TABLE  `fadesite`.`user` (
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

DROP TABLE IF EXISTS `fadesite`.`useractivation`;
CREATE TABLE  `fadesite`.`useractivation` (
  `UserActivationID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Token` varchar(45) NOT NULL,
  `IsDeleted` tinyint(1) NOT NULL DEFAULT '0',
  `UserID` int(10) unsigned NOT NULL,
  PRIMARY KEY (`UserActivationID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `fadesite`.`userban`;
CREATE TABLE  `fadesite`.`userban` (
  `UserBanID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `UserID` int(10) unsigned NOT NULL,
  `StartDate` datetime NOT NULL,
  `ExpireDate` datetime NOT NULL,
  `Description` varchar(200) NOT NULL,
  `IsDeleted` tinyint(1) NOT NULL,
  PRIMARY KEY (`UserBanID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `fadesite`.`userdegree`;
CREATE TABLE  `fadesite`.`userdegree` (
  `UserDegreeID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `UserID` int(10) unsigned NOT NULL,
  `DegreeID` int(10) unsigned NOT NULL,
  `GraduatingDate` datetime NOT NULL,
  `IsDeleted` tinyint(1) NOT NULL,
  PRIMARY KEY (`UserDegreeID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `fadesite`.`userevent`;
CREATE TABLE  `fadesite`.`userevent` (
  `UserEventID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `UserID` int(10) unsigned NOT NULL,
  `EventID` int(10) unsigned NOT NULL,
  `IsDeleted` tinyint(1) NOT NULL,
  PRIMARY KEY (`UserEventID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `fadesite`.`usergroup`;
CREATE TABLE  `fadesite`.`usergroup` (
  `UserGroupID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `UserID` int(10) unsigned NOT NULL,
  `GroupID` int(10) unsigned NOT NULL,
  `isDeleted` tinyint(1) NOT NULL,
  PRIMARY KEY (`UserGroupID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `fadesite`.`usertemporarypassword`;
CREATE TABLE  `fadesite`.`usertemporarypassword` (
  `UserTemporaryPasswordID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `UserID` int(10) unsigned NOT NULL,
  `Password` varchar(45) NOT NULL,
  `DateCreated` datetime NOT NULL,
  `IsDeleted` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`UserTemporaryPasswordID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `fadesite`.`usertype`;
CREATE TABLE  `fadesite`.`usertype` (
  `UserTypeID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) NOT NULL,
  PRIMARY KEY (`UserTypeID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;