-- 菜单表
DROP TABLE IF EXISTS "public"."menu";
CREATE TABLE "public"."menu"
(
    "id"                int8         NOT NULL,
    "parent_id"         int8         ,
    "path"              varchar(255) NOT NULL,
    "name"              varchar(255),
    "component"         varchar(255) NOT NULL,
    "redirect"          varchar(255),
    "title"             varchar(255) NOT NULL,
    "icon"              varchar(255),
    "expanded"          int2,
    "order_no"          int4,
    "hidden"            int2,
    "hidden_breadcrumb" int2,
    "single"            int2,
    "frame_src"         varchar(255),
    "frame_blank"       int2,
    "keep_alive"        int2,

    "create_time"       timestamp DEFAULT CURRENT_TIMESTAMP,
    "create_by"         int8         NOT NULL,
    "last_modify_time"  timestamp,
    "last_modify_by"    int8,
    "is_deleted"        int2      DEFAULT 0,
    "deleted_by"        int8,
    PRIMARY KEY ("id")
);
COMMENT ON TABLE "public"."menu" IS '资源实体表';
COMMENT ON COLUMN "public"."menu"."id" IS '主键';
COMMENT ON COLUMN "public"."menu"."parent_id" IS '上级菜单，最多3层';
COMMENT ON COLUMN "public"."menu"."path" IS '是当前路由的路径，会与配置中的父级节点的 path 组成该页面路由的最终路径；如果需要跳转外部链接，可以将path设置为 http 协议开头的路径。';
COMMENT ON COLUMN "public"."menu"."name" IS '影响多标签 Tab 页的 keep-alive 的能力，如果要确保页面有 keep-alive 的能力，请保证该路由的name与对应页面（SFC)的name保持一致。';
COMMENT ON COLUMN "public"."menu"."component" IS '渲染该路由时使用的页面组件路径位置';
COMMENT ON COLUMN "public"."menu"."redirect" IS '重定向的路径';
COMMENT ON COLUMN "public"."menu"."title" IS '该路由在菜单上展示的标题';
COMMENT ON COLUMN "public"."menu"."icon" IS '该路由在菜单上展示的图标';
COMMENT ON COLUMN "public"."menu"."expanded" IS '决定该路由在菜单上是否默认展开';
COMMENT ON COLUMN "public"."menu"."order_no" IS '该路由在菜单上展示先后顺序，数字越小越靠前，默认为零';
COMMENT ON COLUMN "public"."menu"."hidden" IS '决定该路由是否在菜单上进行展示';
COMMENT ON COLUMN "public"."menu"."hidden_breadcrumb" IS '如果启用了面包屑，决定该路由是否在面包屑上进行展示';
COMMENT ON COLUMN "public"."menu"."single" IS '如果是多级菜单且只存在一个节点，想在菜单上只展示一级节点，可以使用该配置。请注意该配置需配置在父节点';
COMMENT ON COLUMN "public"."menu"."frame_src" IS '内嵌 iframe 的地址';
COMMENT ON COLUMN "public"."menu"."frame_blank" IS '内嵌 iframe 的地址是否以新窗口打开';
COMMENT ON COLUMN "public"."menu"."keep_alive" IS '可决定路由是否开启keep-alive，默认开启';

COMMENT ON COLUMN "public"."menu"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."menu"."create_by" IS '创建人';
COMMENT ON COLUMN "public"."menu"."last_modify_time" IS '最后修改时间';
COMMENT ON COLUMN "public"."menu"."last_modify_by" IS '最后修改人';
COMMENT ON COLUMN "public"."menu"."is_deleted" IS '是否删除，0是未删除，1是已删除';
COMMENT ON COLUMN "public"."menu"."deleted_by" IS '删除人';

-- 创建资源实体表
DROP TABLE IF EXISTS "public"."auth_resource";
CREATE TABLE "public"."auth_resource"
(
    "id"            int8        NOT NULL,
    "tenant_id"     int8        NOT NULL,
    "app_id"        int8        NOT NULL,
    "ref_id"        int8        NOT NULL,
    "ref_type"      varchar(50) NOT NULL,
    "active_status" varchar(10) NOT NULL DEFAULT 'ACTIVE',

    "c_by"          int8        NOT NULL,
    "c_time"        timestamp            DEFAULT CURRENT_TIMESTAMP,
    "lm_by"         int8,
    "lm_time"       timestamp,
    "de_by"         int8,
    "de_time"       timestamp,
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
