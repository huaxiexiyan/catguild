CREATE TABLE `adventurer`  (
    `id` bigint NOT NULL,
    `guild_id` varchar(50) NULL,
    `nickname` varchar(10) NULL,
    `level` varchar(50) NULL,
    `created_time` bigint NULL,
    `last_modified_time` bigint NULL,
    `deleted_time` bigint NULL,
    PRIMARY KEY (`id`)
);
