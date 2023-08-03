package cn.catguild.system.infrastructure.id.impl;

import cn.catguild.system.infrastructure.id.IdGenerationService;
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

    @Override
    public String next() {
        return String.valueOf(idGenerator.nextId());
    }

}
