package cn.catguild.system.infrastructure.id.impl;

import cn.catguild.system.infrastructure.id.IdGenerationService;
import cn.catguild.system.infrastructure.id.repository.UserUIDRepository;
import cn.catguild.system.infrastructure.id.strategy.IdGenerator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author xiyan
 * @date 2023/8/1 15:25
 */
@AllArgsConstructor
@Service
public class IdGenerationServiceImpl implements IdGenerationService {

    private IdGenerator idGenerator;

    private final UserUIDRepository userUIDRepository;

    @Override
    public Long nextId() {
        return idGenerator.nextId();
    }

    /**
     * 先从1-4位的uid开始
     * 用完则提升位数
     *
     * @return
     */
    @Override
    public Integer nextUID() {
        // 生成 1-4位数字，从 1000 - 9999
        // 任意取 100 个放入队列中，然后从队列中取
        // 取完，再取100个放入队列中
        Integer poll = UIDPool.POOL.poll();
        if (poll == null){
            // 全部取完了，需要重新写入新的
            userUIDRepository.findNextFixedUid().ifPresent(
                    us -> us.forEach(u -> UIDPool.POOL.offer(u.getUid())));
            poll = UIDPool.POOL.poll();
        }
        userUIDRepository.removeByUid(poll);
        return poll;
    }

}
