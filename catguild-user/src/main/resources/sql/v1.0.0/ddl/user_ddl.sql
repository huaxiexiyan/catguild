CREATE TABLE `cat_user`  (
    `id` bigint NOT NULL,
    `guild_id` bigint NULL,
    `account_id` bigint NULL,
    `name` varchar(50) NULL,
    `sex` varchar(10) NULL,
    `age` smallint NULL,
    `address` varchar(100) NULL,
    `level` smallint NULL,
    `id_card` varchar(50) NULL,
    `status` varchar(20) NULL,
    `created_time` bigint NULL,
    `last_modified_time` bigint NULL,
    `deleted_time` bigint NULL,
    PRIMARY KEY (`id`)
);
