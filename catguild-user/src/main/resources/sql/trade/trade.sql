-- 月度
create table `trade_stock_month`
(
    `id`        bigint         not null AUTO_INCREMENT,
    `code`      varchar(10)    not null COMMENT '代码',
    `open`      decimal(18, 4) null COMMENT '开盘价',
    `high`      decimal(18, 4) null COMMENT '最高价',
    `low`       decimal(18, 4) null COMMENT '最低价',
    `close`     decimal(18, 4) null COMMENT '收盘价',
    `adj_close` decimal(18, 4) null COMMENT '调整后的收盘价(复权和分配股息后的价格)',
    `volume`    decimal(18, 4) null COMMENT '成交量',
    `date`      date       not null,
    `status`    varchar(10)    not null COMMENT '数据状态',
    PRIMARY KEY (`id`)
);

-- 周
CREATE TABLE `trade_stock_week`
(
    `id`        bigint         not null AUTO_INCREMENT,
    `code`      varchar(10)    not null COMMENT '代码',
    `open`      decimal(18, 4) null COMMENT '开盘价',
    `high`      decimal(18, 4) null COMMENT '最高价',
    `low`       decimal(18, 4) null COMMENT '最低价',
    `close`     decimal(18, 4) null COMMENT '收盘价',
    `adj_close` decimal(18, 4) null COMMENT '调整后的收盘价(复权和分配股息后的价格)',
    `volume`    decimal(18, 4) null COMMENT '成交量',
    `date`      date       not null,
    `status`    varchar(10)    not null COMMENT '数据状态',
    PRIMARY KEY (`id`)
);

-- 日度
CREATE TABLE `trade_stock_day`
(
    `id`        bigint         not null AUTO_INCREMENT,
    `code`      varchar(10)    not null COMMENT '代码',
    `open`      decimal(18, 4) null COMMENT '开盘价',
    `high`      decimal(18, 4) null COMMENT '最高价',
    `low`       decimal(18, 4) null COMMENT '最低价',
    `close`     decimal(18, 4) null COMMENT '收盘价',
    `adj_close` decimal(18, 4) null COMMENT '调整后的收盘价(复权和分配股息后的价格)',
    `volume`    decimal(18, 4) null COMMENT '成交量',
    `date`      date       not null,
    `status`    varchar(10)    not null COMMENT '数据状态',
    PRIMARY KEY (`id`)
);
