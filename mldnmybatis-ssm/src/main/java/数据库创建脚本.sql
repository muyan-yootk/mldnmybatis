DROP DATABASE IF EXISTS mldn ;
CREATE DATABASE mldn CHARACTER SET UTF8 ;
USE mldn ;
CREATE TABLE emp(
	empno		BIGINT,
	name		VARCHAR(50) ,
	job			VARCHAR(50) ,
	salary		DOUBLE ,
	photo		VARCHAR(200) ,
	CONSTRAINT pk_empno PRIMARY KEY(empno)
) ;