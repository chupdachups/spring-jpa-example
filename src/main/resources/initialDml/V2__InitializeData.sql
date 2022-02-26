-- -----------------------------------------------------
-- Data `ACCOUNT`
-- -----------------------------------------------------
INSERT INTO ACCOUNT (ID, FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, ADDRESS1, ZIP, CREATED_AT, UPDATED_AT, LATEST_LOGIN_AT)
VALUES ('1', '호랑이', 'tiger','tiger@korea.com', 'lion', '주소1', '12345', NOW(), NOW(), null);
INSERT INTO ACCOUNT (ID, FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, ADDRESS1, ZIP, CREATED_AT, UPDATED_AT, LATEST_LOGIN_AT)
VALUES ('2', '홍길동', 'honggil', 'honggil@korea.com', 'father', '주소2', '12345', NOW(), NOW(), null);
INSERT INTO ACCOUNT (ID, FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, ADDRESS1, ZIP, CREATED_AT, UPDATED_AT, LATEST_LOGIN_AT)
VALUES ('3', '강감찬', 'gamchan', 'gamchan@korea.com', 'river','주소3', '12345', NOW(), NOW(), NULL);
INSERT INTO ACCOUNT (ID, FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, ADDRESS1, ZIP, CREATED_AT, UPDATED_AT, LATEST_LOGIN_AT)
VALUES ('4', '이순신', 'lee', 'general@korea.com', 'winner', '주소4', '12345', NOW(), NOW(), NULL);
INSERT INTO ACCOUNT (ID, FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, ADDRESS1, ZIP, CREATED_AT, UPDATED_AT, LATEST_LOGIN_AT)
VALUES ('5', '태백산', 'mtb', 'mountain@korea.com', 'high', '주소5', '12345', NOW(), NOW(), NULL);
-- -----------------------------------------------------
-- Data `ACCOUNT_ROLE`
-- -----------------------------------------------------
--INSERT INTO USER_ROLE (ROLE_ID, USER_ID, ROLE, AUTHORIZED_AT)
--VALUES ('1', '1', 'ADMINISTRATOR', NOW());
--INSERT INTO USER_ROLE (ROLE_ID, USER_ID, ROLE, AUTHORIZED_AT)
--VALUES ('2', '2', 'MANAGER', NOW());
--INSERT INTO USER_ROLE (ROLE_ID, USER_ID, ROLE, AUTHORIZED_AT)
--VALUES ('3', '3', 'USER', NOW());
--INSERT INTO USER_ROLE (ROLE_ID, USER_ID, ROLE, AUTHORIZED_AT)
--VALUES ('4', '4', 'USER', NOW());
--INSERT INTO USER_ROLE (ROLE_ID, USER_ID, ROLE, AUTHORIZED_AT)
--VALUES ('5', '5', 'USER', NOW());
--INSERT INTO USER_ROLE (ROLE_ID, USER_ID, ROLE, AUTHORIZED_AT)
--VALUES ('6', '1', 'USER', NOW());