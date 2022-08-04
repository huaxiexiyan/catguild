CREATE TABLE `user`  (
    `id` bigint NOT NULL,
    `name` varchar(50) NULL,
    `sex` varchar(10) NULL,
    `age` smallint NULL,
    `level` smallint NULL,
    `id_card` varchar(50) NULL,
    `status` varchar(20) NULL,
    `created_time` bigint NULL,
    `last_modified_time` bigint NULL,
    `deleted_time` bigint NULL,
    PRIMARY KEY (`id`)
);
