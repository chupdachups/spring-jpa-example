-- -----------------------------------------------------
-- Table `ACCOUNT`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ACCOUNT`
(
  `EMAIL`					VARCHAR(255) NOT NULL,
  `FIRST_NAME`				VARCHAR(255) NOT NULL,
  `LAST_NAME`				VARCHAR(255) NOT NULL,
  `PASSWORD`				VARCHAR(255) NOT NULL,
  `ADDRESS1`				VARCHAR(255) NOT NULL,
  `ZIP`						VARCHAR(255) NOT NULL,
  `CREATED_AT`				TIMESTAMP    NULL,
  `UPDATED_AT`				TIMESTAMP    NULL,
  `LATEST_LOGIN_AT`     	TIMESTAMP    NULL,
  PRIMARY KEY (`EMAIL`)
);

-- -----------------------------------------------------
-- Table `USER_ROLE`
-- -----------------------------------------------------
--CREATE TABLE IF NOT EXISTS `USER_ROLE`
--(
--  `ROLE_ID`             VARCHAR(255) NOT NULL,
--  `USER_ID`             VARCHAR(255) NOT NULL,
--  `ROLE`                VARCHAR(255) NOT NULL,
--  `AUTHORIZED_AT`       TIMESTAMP    NULL,
--  PRIMARY KEY (`ROLE_ID`)
--);