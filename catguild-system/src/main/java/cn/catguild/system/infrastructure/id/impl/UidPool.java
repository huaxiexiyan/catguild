package cn.catguild.system.infrastructure.id.impl;

import com.google.common.collect.Queues;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author xiyan
 * @date 2023/8/11 10:53
 */
public class UidPool {

    public static final int MAX = 100;

    protected static final LinkedBlockingQueue<Integer>  POOL = Queues.newLinkedBlockingQueue(MAX);

}
