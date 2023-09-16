package cn.catguild.system.infrastructure.id.impl;

import cn.catguild.system.infrastructure.id.IdGenerationService;
import cn.catguild.system.infrastructure.id.repository.UidRepository;
import cn.catguild.system.infrastructure.id.strategy.IdGenerator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author xiyan
 * @date 2023/8/1 15:25
 */
@AllArgsConstructor
@Service
public class IdGenerationServiceImpl implements IdGenerationService {

    private IdGenerator idGenerator;

    private final UidRepository UIDRepository;

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
        Integer poll = UidPool.POOL.poll();
        if (poll == null){
            // 全部取完了，需要重新写入新的
            UIDRepository.findNextFixedUid().ifPresent(
                    us -> us.forEach(u -> UidPool.POOL.offer(u.getUid())));
            poll = UidPool.POOL.poll();
        }
        UIDRepository.findByUid(poll).ifPresent(uid->{
            uid.setDeTime(LocalDateTime.now());
            UIDRepository.saveAndFlush(uid);
        });
        return poll;
    }

}
