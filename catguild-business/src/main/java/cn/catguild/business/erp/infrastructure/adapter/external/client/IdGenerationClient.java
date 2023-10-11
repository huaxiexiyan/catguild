package cn.catguild.business.erp.infrastructure.adapter.external.client;

/**
 * @author xiyan
 * @date 2023/8/3 16:05
 */
public interface IdGenerationClient {

    Long nextId();

    Integer nextUid();

}
