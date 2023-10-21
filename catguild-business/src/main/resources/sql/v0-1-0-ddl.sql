-- 创建 app授权类型实体 表
DROP TABLE IF EXISTS "public"."business_automatic_app_auth_type";
CREATE TABLE "public"."business_automatic_app_auth_type"
(
    "id"            int8        NOT NULL,
    "tenant_id"     int8        NOT NULL,
    "name"          varchar(50) NOT NULL,
    "code"          varchar(20) NOT NULL,
    "active_status" varchar(10) NOT NULL DEFAULT 'ACTIVE',

    "c_by"          int8        NOT NULL,
    "c_time"        timestamp            DEFAULT CURRENT_TIMESTAMP,
    "lm_by"         int8,
    "lm_time"       timestamp,
    "de_by"         int8,
    "de_time"       timestamp,
    PRIMARY KEY ("id")
);
COMMENT ON TABLE "public"."business_automatic_app_auth_type" IS 'app授权类型实体表';
COMMENT ON COLUMN "public"."business_automatic_app_auth_type"."id" IS '主键';
COMMENT ON COLUMN "public"."business_automatic_app_auth_type"."tenant_id" IS '租户id';
COMMENT ON COLUMN "public"."business_automatic_app_auth_type"."name" IS 'app名称';
COMMENT ON COLUMN "public"."business_automatic_app_auth_type"."code" IS '类型标识code';
COMMENT ON COLUMN "public"."business_automatic_app_auth_type"."active_status" IS '活跃状态';

COMMENT ON COLUMN "public"."business_automatic_app_auth_type"."c_by" IS '创建人';
COMMENT ON COLUMN "public"."business_automatic_app_auth_type"."c_time" IS '创建时间';
COMMENT ON COLUMN "public"."business_automatic_app_auth_type"."lm_by" IS '最后修改人';
COMMENT ON COLUMN "public"."business_automatic_app_auth_type"."lm_time" IS '最后修改时间';
COMMENT ON COLUMN "public"."business_automatic_app_auth_type"."de_by" IS '删除人';
COMMENT ON COLUMN "public"."business_automatic_app_auth_type"."de_time" IS '删除时间';

-- 创建 app授权配置实体表
DROP TABLE IF EXISTS "public"."business_automatic_app_auth_config";
CREATE TABLE "public"."business_automatic_app_auth_config"
(
    "id"                             int8        NOT NULL,
    "tenant_id"                      int8        NOT NULL,
    "app_auth_type_id"               int8        NOT NULL,
    "access_token"                   varchar(500),
    "refresh_token"                  varchar(500),
    "expires_in"                     int4,
    "last_update_access_token_time"  timestamp,
    "last_update_refresh_token_time" timestamp,
    "status"                         varchar(30),
    "active_status"                  varchar(10) NOT NULL DEFAULT 'ACTIVE',

    "c_by"                           int8        NOT NULL,
    "c_time"                         timestamp            DEFAULT CURRENT_TIMESTAMP,
    "lm_by"                          int8,
    "lm_time"                        timestamp,
    "de_by"                          int8,
    "de_time"                        timestamp,
    PRIMARY KEY ("id")
);
COMMENT ON TABLE "public"."business_automatic_app_auth_config" IS 'app授权配置实体表';
COMMENT ON COLUMN "public"."business_automatic_app_auth_config"."id" IS '主键';
COMMENT ON COLUMN "public"."business_automatic_app_auth_config"."tenant_id" IS '租户id';
COMMENT ON COLUMN "public"."business_automatic_app_auth_config"."app_auth_type_id" IS 'app类型id';
COMMENT ON COLUMN "public"."business_automatic_app_auth_config"."access_token" IS 'access_token';
COMMENT ON COLUMN "public"."business_automatic_app_auth_config"."refresh_token" IS 'refresh_token';
COMMENT ON COLUMN "public"."business_automatic_app_auth_config"."expires_in" IS 'access_token过期时间';
COMMENT ON COLUMN "public"."business_automatic_app_auth_config"."last_update_access_token_time" IS 'access_token最后更新时间';
COMMENT ON COLUMN "public"."business_automatic_app_auth_config"."last_update_refresh_token_time" IS 'refresh_token最后更新时间';
COMMENT ON COLUMN "public"."business_automatic_app_auth_config"."status" IS '授权状态';
COMMENT ON COLUMN "public"."business_automatic_app_auth_config"."active_status" IS '活跃状态';

COMMENT ON COLUMN "public"."business_automatic_app_auth_config"."c_by" IS '创建人';
COMMENT ON COLUMN "public"."business_automatic_app_auth_config"."c_time" IS '创建时间';
COMMENT ON COLUMN "public"."business_automatic_app_auth_config"."lm_by" IS '最后修改人';
COMMENT ON COLUMN "public"."business_automatic_app_auth_config"."lm_time" IS '最后修改时间';
COMMENT ON COLUMN "public"."business_automatic_app_auth_config"."de_by" IS '删除人';
COMMENT ON COLUMN "public"."business_automatic_app_auth_config"."de_time" IS '删除时间';


