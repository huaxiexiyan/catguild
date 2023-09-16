package cn.catguild.system.infrastructure.id.impl;

import cn.catguild.system.infrastructure.id.IdGenerationService;
import cn.catguild.system.infrastructure.id.domain.Uid;
import cn.catguild.system.infrastructure.id.repository.UidRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 初始化
 *
 * @author xiyan
 * @date 2023/8/11 10:48
 */
@Slf4j
@AllArgsConstructor
@Component
public class IdGenerationApplicationRunner implements ApplicationRunner {

    private final UidRepository UIDRepository;
    private final IdGenerationService idGenerationService;

    private static List<Integer> generateNumbers(int min, int max) {
        log.info("初始化uid=【{}-{}】", min, max);
        List<Integer> numbers = new ArrayList<>();
        for (int i = min; i <= max; i++) {
            numbers.add(i);
        }
        return numbers;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 检查数据库中是否有原始存根
        Integer count = UIDRepository.countByDeTimeIsNull();
        if (count != null && count > 0) {
            log.info("数据库原始已经初始化过了，现在填充队列");
            UIDRepository.findNextFixedUid().ifPresent(
                    us -> us.forEach(u -> UidPool.POOL.offer(u.getUid())));
        } else {
            initDBUid();
        }
    }

    private void initDBUid() {
        // 初始化原始数据
        Uid topUid = UIDRepository.findTopByOrderByUidDesc();
        int min, max = 0;
        if (topUid == null) {
            min = 1000;
            max = 9999;
        } else {
            Integer uid = topUid.getUid();
            min = uid + 1;
            max = uid * 10 + 9;
        }
        List<Integer> uids = generateNumbers(min, max);
        Collections.shuffle(uids);

        LocalDateTime now = LocalDateTime.now();
        uids.forEach(uid -> {
            Uid userUid = new Uid();
            userUid.setId(idGenerationService.nextId());
            userUid.setUid(uid);
            userUid.setCTime(now);
            UIDRepository.saveAndFlush(userUid);
        });
    }

}
