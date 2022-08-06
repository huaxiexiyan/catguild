CREATE TABLE `account`  (
    `id` bigint NOT NULL,
    `username` varchar(50) NULL,
    `password` varchar(255) NULL,
    `telephone` varchar(255) NULL,
    `email` varchar(255) NULL,
    `created_time` bigint NULL,
    `last_modified_time` bigint NULL,
    `deleted_time` bigint NULL,
    PRIMARY KEY (`id`)
);
