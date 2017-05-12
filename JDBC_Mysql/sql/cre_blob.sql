DROP TABLE blobtest;

USE jdbc;

CREATE TABLE blobtest (
	name		varchar(30)		PRIMARY KEY, 
	photo		blob			NOT NULL
);


