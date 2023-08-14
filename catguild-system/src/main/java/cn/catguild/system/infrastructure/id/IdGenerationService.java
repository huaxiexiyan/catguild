package cn.catguild.system.infrastructure.id;

/**
 * 全局id生成服务
 *
 * @author xiyan
 * @date 2023/8/1 15:24
 */
public interface IdGenerationService {

    /**
     * 获取下一个主键id
     *
     * @return
     */
    Long nextId();

    /**
     * 获取下一个uid
     *
     * @return
     */
    Integer nextUID();

}
