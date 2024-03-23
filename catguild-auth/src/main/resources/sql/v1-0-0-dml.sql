--  注册租户
INSERT INTO "public"."auth_tenant" ("id", "name", "email", "remarks", "status", "c_by", "c_time", "de_by", "de_time", "lm_by", "lm_time", "uid", "domain_name")
VALUES (100, '运维空间', 'xxxx@qq.com', '用于运维的顶级租户', 'ACTIVE', NULL, '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, 100, 'localhost,127.0.0.1');

--  注册用户
INSERT INTO "public"."auth_user" ("id", "name", "tenant_id", "authority_type", "status", "c_by", "c_time", "de_by", "de_time", "lm_by", "lm_time")
VALUES (1, '运维空间@超级管理员', 100, 'SUPER_ADMINISTRATOR', 'ACTIVE', NULL, '2023-01-01 00:00:00', NULL, NULL, NULL, NULL);

--  注册登录账号（默认密码：password）
INSERT INTO "public"."auth_account" ("id", "password", "user_id", "username", "disabled", "tenant_id", "c_by", "c_time", "de_by", "de_time", "lm_by", "lm_time")
VALUES (1, '{bcrypt}$2a$10$cv4TCoayYCReqE1RuAy60uqvXbntz96Yo/VfzRtqQ7h//QfbG23N.', 1, 'catguild', 'NO', 100, NULL, '2023-01-01 00:00:00', NULL, NULL, NULL, NULL);