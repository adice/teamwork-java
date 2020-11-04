INSERT INTO `sta_status_dictionary` (status_name, status_info) VALUES ('在职','员工在职工作');
INSERT INTO `sta_status_dictionary` (status_name, status_info) VALUES ('退休','员工退休');
INSERT INTO `sta_status_dictionary` (status_name, status_info) VALUES ('革职','员工被革职');
INSERT INTO `sta_status_dictionary` (status_name, status_info) VALUES ('休假','员工前往休假');
COMMIT;

INSERT INTO `aut_authority` (authority_name,authority_info,authority_status,authority_creator) VALUES ('创建部门','可以创建子部门',1,'01000000000000');
COMMIT;

INSERT INTO `aut_role` (role_name,role_status,role_creator,department_id) VALUES ('校长',1,'01000000000000',1);
COMMIT;

INSERT INTO `dep_department` (department_name,department_info,department_status,department_originator,department_principal) VALUES ('河北师范大学','学校简介',1,'01000000000000','01000000000000');
COMMIT;

INSERT iNTO `sta_staff` (id,name,password,status,staff_detail) VALUES ('01000000000000','校长','123456',1,'01000000000000');
COMMIT;

INSERT iNTO `sta_staff_detail` (staff_id,staff_name,staff_sex,staff_address) VALUES ('01000000000000','校长','男','河北石家庄');
COMMIT;

INSERT iNTO `rel_staff_role` (staff_id,role_id) VALUES ('01000000000000',1);
COMMIT;

INSERT iNTO `rel_staff_department` (staff_id,department_id) VALUES ('01000000000000',1);
COMMIT;