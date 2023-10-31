-- 创建资源实体表
DROP TABLE IF EXISTS "public"."auth_resource";
CREATE TABLE "public"."auth_resource"
(
    "id"        int8        NOT NULL,
    "tenant_id" int8        NOT NULL,
    "app_id"    int8        NOT NULL,
    "ref_id"    int8        NOT NULL,
    "ref_type"  varchar(50) NOT NULL,
    "active_status" varchar(10) NOT NULL DEFAULT 'ACTIVE',

    "c_by"      int8        NOT NULL,
    "c_time"    timestamp DEFAULT CURRENT_TIMESTAMP,
    "lm_by"     int8,
    "lm_time"   timestamp,
    "de_by"     int8,
    "de_time"   timestamp,
    PRIMARY KEY ("id")
);
COMMENT ON TABLE "public"."auth_resource" IS '资源实体表';
COMMENT ON COLUMN "public"."auth_resource"."id" IS '主键';
COMMENT ON COLUMN "public"."auth_resource"."tenant_id" IS '租户id';
COMMENT ON COLUMN "public"."auth_resource"."app_id" IS '来源appId';
COMMENT ON COLUMN "public"."auth_resource"."ref_id" IS '资源实体id';
COMMENT ON COLUMN "public"."auth_resource"."ref_type" IS '资源类型';
COMMENT ON COLUMN "public"."auth_resource"."active_status" IS '活跃状态';

COMMENT ON COLUMN "public"."auth_resource"."c_by" IS '创建人';
COMMENT ON COLUMN "public"."auth_resource"."c_time" IS '创建时间';
COMMENT ON COLUMN "public"."auth_resource"."lm_by" IS '最后修改人';
COMMENT ON COLUMN "public"."auth_resource"."lm_time" IS '最后修改时间';
COMMENT ON COLUMN "public"."auth_resource"."de_by" IS '删除人';
COMMENT ON COLUMN "public"."auth_resource"."de_time" IS '删除时间';

-- 创建角色实体表
DROP TABLE IF EXISTS "public"."auth_role";
CREATE TABLE "public"."auth_role"
(
    "id"            int8        NOT NULL,
    "tenant_id"     int8        NOT NULL,
    "name"          varchar(50) NOT NULL,
    "app_id"        int8        NOT NULL,
    "code"          varchar(50),
    "active_status" varchar(10) NOT NULL DEFAULT 'ACTIVE',

    "c_by"          int8        NOT NULL,
    "c_time"        timestamp            DEFAULT CURRENT_TIMESTAMP,
    "lm_by"         int8,
    "lm_time"       timestamp,
    "de_by"         int8,
    "de_time"       timestamp,
    PRIMARY KEY ("id")
);
COMMENT ON TABLE "public"."auth_role" IS '角色实体表';
COMMENT ON COLUMN "public"."auth_role"."id" IS '主键';
COMMENT ON COLUMN "public"."auth_role"."tenant_id" IS '租户id';
COMMENT ON COLUMN "public"."auth_role"."name" IS '角色名称';
COMMENT ON COLUMN "public"."auth_role"."app_id" IS '来源appId';
COMMENT ON COLUMN "public"."auth_role"."code" IS '唯一code';
COMMENT ON COLUMN "public"."auth_role"."active_status" IS '活跃状态';

COMMENT ON COLUMN "public"."auth_role"."c_by" IS '创建人';
COMMENT ON COLUMN "public"."auth_role"."c_time" IS '创建时间';
COMMENT ON COLUMN "public"."auth_role"."lm_by" IS '最后修改人';
COMMENT ON COLUMN "public"."auth_role"."lm_time" IS '最后修改时间';
COMMENT ON COLUMN "public"."auth_role"."de_by" IS '删除人';
COMMENT ON COLUMN "public"."auth_role"."de_time" IS '删除时间';

-- 创建角色资源关联关系表
DROP TABLE IF EXISTS "public"."auth_role_resource";
CREATE TABLE "public"."auth_role_resource"
(
    "id"          int8 NOT NULL,
    "app_id"      int8 NOT NULL,
    "tenant_id"   int8 NOT NULL,
    "role_id"     int8 NOT NULL,
    "resource_id" int8 NOT NULL,

    "c_by"        int8 NOT NULL,
    "c_time"      timestamp DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY ("id")
);
COMMENT ON TABLE "public"."auth_role_resource" IS '角色资源关联关系表';
COMMENT ON COLUMN "public"."auth_role_resource"."id" IS '主键';
COMMENT ON COLUMN "public"."auth_role_resource"."tenant_id" IS '租户id';
COMMENT ON COLUMN "public"."auth_role_resource"."app_id" IS '来源appId';
COMMENT ON COLUMN "public"."auth_role_resource"."role_id" IS '角色id';
COMMENT ON COLUMN "public"."auth_role_resource"."resource_id" IS '资源id';

COMMENT ON COLUMN "public"."auth_role_resource"."c_by" IS '创建人';
COMMENT ON COLUMN "public"."auth_role_resource"."c_time" IS '创建时间';

-- 创建用户角色关联关系表
DROP TABLE IF EXISTS "public"."auth_user_role";
CREATE TABLE "public"."auth_user_role"
(
    "id"        int8 NOT NULL,
    "tenant_id" int8 NOT NULL,
    "app_id"    int8 NOT NULL,
    "user_id"   int8 NOT NULL,
    "role_id"   int8 NOT NULL,

    "c_by"      int8 NOT NULL,
    "c_time"    timestamp DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY ("id")
);
COMMENT ON TABLE "public"."auth_user_role" IS '用户角色关联关系表';
COMMENT ON COLUMN "public"."auth_user_role"."id" IS '主键';
COMMENT ON COLUMN "public"."auth_user_role"."tenant_id" IS '租户id';
COMMENT ON COLUMN "public"."auth_user_role"."app_id" IS '来源appId';
COMMENT ON COLUMN "public"."auth_user_role"."user_id" IS '用户id';
COMMENT ON COLUMN "public"."auth_user_role"."role_id" IS '角色id';

COMMENT ON COLUMN "public"."auth_user_role"."c_by" IS '创建人';
COMMENT ON COLUMN "public"."auth_user_role"."c_time" IS '创建时间';

-- 创建 租户实体表
DROP TABLE IF EXISTS "public"."auth_tenant";
CREATE TABLE "public"."auth_tenant"
(
    "id"            int8         NOT NULL,
    "uid"           int4         NOT NULL,
    "name"          varchar(100) NOT NULL,
    "email"         varchar(50),
    "remarks"       varchar(225),
    "active_status" varchar(10)  NOT NULL DEFAULT CURRENT_TIMESTAMP,
    "domain_name"   varchar(225),

    "c_by"          int8         NOT NULL,
    "c_time"        timestamp             DEFAULT CURRENT_TIMESTAMP,
    "lm_by"         int8,
    "lm_time"       timestamp,
    "de_by"         int8,
    "de_time"       timestamp,
    PRIMARY KEY ("id")
);
COMMENT ON TABLE "public"."auth_tenant" IS '角色资源关联关系表';
COMMENT ON COLUMN "public"."auth_tenant"."id" IS '主键';
COMMENT ON COLUMN "public"."auth_tenant"."uid" IS '唯一UID';
COMMENT ON COLUMN "public"."auth_tenant"."name" IS '租户名';
COMMENT ON COLUMN "public"."auth_tenant"."email" IS '注册邮箱';
COMMENT ON COLUMN "public"."auth_tenant"."remarks" IS '备注';
COMMENT ON COLUMN "public"."auth_tenant"."domain_name" IS '绑定的域名，多个逗号风格';
COMMENT ON COLUMN "public"."auth_tenant"."active_status" IS '活跃状态';

COMMENT ON COLUMN "public"."auth_tenant"."c_by" IS '创建人';
COMMENT ON COLUMN "public"."auth_tenant"."c_time" IS '创建时间';
COMMENT ON COLUMN "public"."auth_tenant"."lm_by" IS '最后修改人';
COMMENT ON COLUMN "public"."auth_tenant"."lm_time" IS '最后修改时间';
COMMENT ON COLUMN "public"."auth_tenant"."de_by" IS '删除人';
COMMENT ON COLUMN "public"."auth_tenant"."de_time" IS '删除时间';
-- 创建 租户app关联关系表
DROP TABLE IF EXISTS "public"."auth_tenant_app";
CREATE TABLE "public"."auth_tenant_app"
(
    "id"            int8        NOT NULL,
    "tenant_id"     int8        NOT NULL,
    "app_id"        int8        NOT NULL,
    "active_status" varchar(10) NOT NULL DEFAULT 'ACTIVE',

    "c_by"          int8        NOT NULL,
    "c_time"        timestamp            DEFAULT CURRENT_TIMESTAMP,
    "lm_by"         int8,
    "lm_time"       timestamp,
    "de_by"         int8,
    "de_time"       timestamp,
    PRIMARY KEY ("id")
);
COMMENT ON TABLE "public"."auth_tenant_app" IS '租户app关联关系表';
COMMENT ON COLUMN "public"."auth_tenant_app"."id" IS '主键';
COMMENT ON COLUMN "public"."auth_tenant_app"."tenant_id" IS '租户id';
COMMENT ON COLUMN "public"."auth_tenant_app"."app_id" IS '来源appId';
COMMENT ON COLUMN "public"."auth_tenant_app"."active_status" IS '活跃状态';

COMMENT ON COLUMN "public"."auth_tenant_app"."c_by" IS '创建人';
COMMENT ON COLUMN "public"."auth_tenant_app"."c_time" IS '创建时间';
COMMENT ON COLUMN "public"."auth_tenant_app"."lm_by" IS '最后修改人';
COMMENT ON COLUMN "public"."auth_tenant_app"."lm_time" IS '最后修改时间';
COMMENT ON COLUMN "public"."auth_tenant_app"."de_by" IS '删除人';
COMMENT ON COLUMN "public"."auth_tenant_app"."de_time" IS '删除时间';