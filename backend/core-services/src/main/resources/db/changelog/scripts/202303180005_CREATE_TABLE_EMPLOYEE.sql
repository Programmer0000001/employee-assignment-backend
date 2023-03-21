--liquibase formatted sql
--changeset  oudayrao.ittoo:202303180005_CREATE_TABLE_EMPLOYEE

--Following convention yearmonthdatetime_DESCRIPTION

--comment: create table employee
CREATE TABLE employee
(
    user_id      BIGINT AUTO_INCREMENT,
    first_name   VARCHAR(200) NULL,
    last_name    VARCHAR(200) NULL,
    bu_code      VARCHAR(200) NULL,
    bu_name      VARCHAR(200) NULL,
    region_code  VARCHAR(200) NULL,
    region_name  VARCHAR(200) NULL,
    created_date TIMESTAMP,
    CONSTRAINT pk_user_id PRIMARY KEY (user_id)
);