package io.kerns.uid;

import org.springframework.util.Assert;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xiaohei
 * @create 2020-06-23 下午4:26
 **/
public class IdMarketImpl implements IdMarket {
    private long workId;


    private ConcurrentHashMap<Integer, IdWorker> workerHolder = new ConcurrentHashMap<>();


    IdMarketImpl(long workId) {
        this.workId = workId;
    }

    @Override
    public long nextId(Integer bizType) {
        Assert.notNull(bizType,"biz type is  null");
        if (workerHolder.containsKey(bizType)) {
            IdWorker idWorker=workerHolder.get(bizType);
            Assert.notNull(idWorker,"id worker not null");
            return idWorker.nextId();
        }
        IdWorker idWorker = new SnowflakeIdWorker(workId, bizType);
        workerHolder.put(bizType, idWorker);
        return idWorker.nextId();
    }
}
