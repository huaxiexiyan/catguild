DROP TABLE IF EXISTS cat_account;
CREATE TABLE cat_account
(
    id           bigint            NOT NULL,
    guild_id     bigint            NOT NULL,
    username     text NOT NULL,
    password     text NOT NULL,
    status       text NOT NULL,
    cat_user_id  bigint            NOT NULL,
    created_time bigint            NOT NULL,
    updated_time bigint            NOT NULL,
    UNIQUE (guild_id, username),
    PRIMARY KEY (id)
);
COMMENT ON COLUMN "cat_account"."id" IS '主键';
COMMENT ON COLUMN "cat_account"."guild_id" IS '公会id';
COMMENT ON COLUMN "cat_account"."username" IS '登录凭证名';
COMMENT ON COLUMN "cat_account"."password" IS '登录密码';
COMMENT ON COLUMN "cat_account"."status" IS '账号状态';
COMMENT ON COLUMN "cat_account"."cat_user_id" IS '用户id，账号用户1-1';
COMMENT ON COLUMN "cat_account"."created_time" IS '创建时间戳';
COMMENT ON COLUMN "cat_account"."updated_time" IS '修改时间戳';
COMMENT ON TABLE "cat_account" IS '登录账号凭证表';

DROP TABLE IF EXISTS cat_user;
CREATE TABLE cat_user
(
    id           bigint            NOT NULL,
    guild_id     bigint            NOT NULL,
    name         text NOT NULL,
    identity     text[] NOT NULL,
    created_time bigint            NOT NULL,
    updated_time bigint            NOT NULL,
    PRIMARY KEY (id)
);
COMMENT ON COLUMN "cat_user"."id" IS '主键';
COMMENT ON COLUMN "cat_user"."guild_id" IS '公会id';
COMMENT ON COLUMN "cat_user"."name" IS '用户名，没填自动生成';
COMMENT ON COLUMN "cat_user"."identity" IS '身份数组【adventurer、guild_staff、principal】';
COMMENT ON COLUMN "cat_user"."created_time" IS '创建时间戳';
COMMENT ON COLUMN "cat_user"."updated_time" IS '修改时间戳';
COMMENT ON TABLE "cat_user" IS '用户基本信息表';

DROP TABLE IF EXISTS adventurer;
CREATE TABLE adventurer
(
    cat_user_id  bigint            NOT NULL,
    guild_id     bigint            NOT NULL,
    name         text NOT NULL,
    id_card      text NOT NULL,
    created_time bigint            NOT NULL,
    updated_time bigint            NOT NULL,
    PRIMARY KEY (cat_user_id)
);
COMMENT ON COLUMN "adventurer"."cat_user_id" IS '主键';
COMMENT ON COLUMN "adventurer"."guild_id" IS '公会id';
COMMENT ON COLUMN "adventurer"."name" IS '用户名';
COMMENT ON COLUMN "adventurer"."id_card" IS '自动生成的冒险家唯一身份ID';
COMMENT ON COLUMN "adventurer"."created_time" IS '创建时间戳';
COMMENT ON COLUMN "adventurer"."updated_time" IS '修改时间戳';
COMMENT ON TABLE "adventurer" IS '用户-冒险家表';

DROP TABLE IF EXISTS guild_staff;
CREATE TABLE guild_staff
(
    cat_user_id  bigint            NOT NULL,
    guild_id     bigint            NOT NULL,
    name         text NOT NULL,
    created_time bigint            NOT NULL,
    updated_time bigint            NOT NULL,
    PRIMARY KEY (cat_user_id)
);
COMMENT ON COLUMN "guild_staff"."cat_user_id" IS '主键';
COMMENT ON COLUMN "guild_staff"."guild_id" IS '公会id';
COMMENT ON COLUMN "guild_staff"."name" IS '用户名';
COMMENT ON COLUMN "guild_staff"."created_time" IS '创建时间戳';
COMMENT ON COLUMN "guild_staff"."updated_time" IS '修改时间戳';
COMMENT ON TABLE "guild_staff" IS '用户-公会职员表';

DROP TABLE IF EXISTS principal;
CREATE TABLE principal
(
    cat_user_id  bigint            NOT NULL,
    guild_id     bigint            NOT NULL,
    name         text NOT NULL,
    created_time bigint            NOT NULL,
    updated_time bigint            NOT NULL,
    PRIMARY KEY (cat_user_id)
);
COMMENT ON COLUMN "principal"."cat_user_id" IS '主键';
COMMENT ON COLUMN "principal"."guild_id" IS '公会id';
COMMENT ON COLUMN "principal"."name" IS '用户名';
COMMENT ON COLUMN "principal"."created_time" IS '创建时间戳';
COMMENT ON COLUMN "principal"."updated_time" IS '修改时间戳';
COMMENT ON TABLE "principal" IS '用户-委托人表';

DROP TABLE IF EXISTS guild;
CREATE TABLE guild
(
    id           bigint            NOT NULL,
    name         text NOT NULL,
    created_time bigint            NOT NULL,
    updated_time bigint            NOT NULL,
    PRIMARY KEY (id)
);
COMMENT ON COLUMN "guild"."id" IS '主键';
COMMENT ON COLUMN "guild"."name" IS '用户名';
COMMENT ON COLUMN "guild"."created_time" IS '创建时间戳';
COMMENT ON COLUMN "guild"."updated_time" IS '修改时间戳';
COMMENT ON TABLE "guild" IS '公会信息表';
