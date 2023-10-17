package cn.catguild.business.erp.application.dto;

import lombok.Getter;

/**
 * @author lionel
 * @date 2022/9/23 10:00
 */
@Getter
public enum TimeScale {

    /**
     * 当前周
     **/
    WEEK,

    /**
     * 上周
     **/
    LAST_WEEK,

    /**
     * 当前月
     **/
    MONTH,

    /**
     * 上月
     **/
    LAST_MONTH,

    /**
     * 当前年
     **/
    YEAR,

    /**
     * 上年
     **/
    LAST_YEAR

}
