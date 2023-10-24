-- 创建 app实体 表
DROP TABLE IF EXISTS "public"."system_app";
CREATE TABLE "public"."system_app"
(
    "id"            int8         NOT NULL,
    "parent_id"     int8         NOT NULL DEFAULT 0,
    "path"          varchar(100) NOT NULL,
    "name"          varchar(50)  NOT NULL,
    "describe"      varchar(200),
    "active_status" varchar(10)  NOT NULL DEFAULT 'ACTIVE',

    "c_by"          int8         NOT NULL,
    "c_time"        timestamp             DEFAULT CURRENT_TIMESTAMP,
    "lm_by"         int8,
    "lm_time"       timestamp,
    "de_by"         int8,
    "de_time"       timestamp,
    PRIMARY KEY ("id")
);
COMMENT ON TABLE "public"."system_app" IS 'app实体表';
COMMENT ON COLUMN "public"."system_app"."id" IS '主键';
COMMENT ON COLUMN "public"."system_app"."parent_id" IS '上层应用id';
COMMENT ON COLUMN "public"."system_app"."path" IS 'id路径包括自己，逗号分隔';
COMMENT ON COLUMN "public"."system_app"."name" IS '应用名称';
COMMENT ON COLUMN "public"."system_app"."describe" IS '描述';
COMMENT ON COLUMN "public"."system_app"."active_status" IS '活跃状态';

COMMENT ON COLUMN "public"."system_app"."c_by" IS '创建人';
COMMENT ON COLUMN "public"."system_app"."c_time" IS '创建时间';
COMMENT ON COLUMN "public"."system_app"."lm_by" IS '最后修改人';
COMMENT ON COLUMN "public"."system_app"."lm_time" IS '最后修改时间';
COMMENT ON COLUMN "public"."system_app"."de_by" IS '删除人';
COMMENT ON COLUMN "public"."system_app"."de_time" IS '删除时间';

-- 创建 app菜单关系表
DROP TABLE IF EXISTS "public"."system_app_menu";
CREATE TABLE "public"."system_app_menu"
(
    "id"      int8 NOT NULL,
    "appId"   int8 NOT NULL,
    "menuId"  int8 NOT NULL,

    "c_by"    int8 NOT NULL,
    "c_time"  timestamp DEFAULT CURRENT_TIMESTAMP,
    "lm_by"   int8,
    "lm_time" timestamp,
    "de_by"   int8,
    "de_time" timestamp,
    PRIMARY KEY ("id")
);
COMMENT ON TABLE "public"."system_app_menu" IS 'app菜单关系表';
COMMENT ON COLUMN "public"."system_app_menu"."id" IS '主键';
COMMENT ON COLUMN "public"."system_app_menu"."appId" IS 'app的id';
COMMENT ON COLUMN "public"."system_app_menu"."menuId" IS '菜单id';

COMMENT ON COLUMN "public"."system_app_menu"."c_by" IS '创建人';
COMMENT ON COLUMN "public"."system_app_menu"."c_time" IS '创建时间';
COMMENT ON COLUMN "public"."system_app_menu"."lm_by" IS '最后修改人';
COMMENT ON COLUMN "public"."system_app_menu"."lm_time" IS '最后修改时间';
COMMENT ON COLUMN "public"."system_app_menu"."de_by" IS '删除人';
COMMENT ON COLUMN "public"."system_app_menu"."de_time" IS '删除时间';