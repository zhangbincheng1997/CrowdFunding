package com.redhat.crowdfunding.pool;

import com.redhat.crowdfunding.service.CrowdFundingService;
import com.redhat.crowdfunding.service.CrowdFundingServiceImpl;
import com.redhat.crowdfunding.util.Consts;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

/**
 * @author littleredhat
 */
public class CrowdFundingServicePool extends BasePooledObjectFactory<CrowdFundingService> {

    // 对象池
    private static GenericObjectPool<CrowdFundingService> pool = null;

    /**
     * 初始化对象池
     *
     * @return
     */
    static {
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        // 连接池最大连接数
        poolConfig.setMaxTotal(Consts.MAX_TOTAL);
        // 连接池最大空闲连接数
        poolConfig.setMaxIdle(Consts.MAX_IDLE);
        // 连接池最小空闲连接数
        poolConfig.setMinIdle(Consts.MIN_IDLE);
        // 获取连接时检查连接的可用性
        poolConfig.setTestOnBorrow(Consts.SET_TEST_ON_BORROW);
        // 归还连接时检查连接的可用性
        poolConfig.setTestOnReturn(Consts.SET_TEST_ON_RETURN);
        pool = new GenericObjectPool<CrowdFundingService>(new CrowdFundingServicePool(), poolConfig);
    }

    /**
     * 获取对象
     *
     * @return
     * @throws Exception
     */
    public static CrowdFundingService borrowObject() throws Exception {
        return pool.borrowObject();
    }

    /**
     * 归还对象
     *
     * @param obj
     */
    public static void returnObject(CrowdFundingService obj) {
        pool.returnObject(obj);
    }

    /**
     * 关闭对象池
     */
    public synchronized static void close() {
        if (pool != null && !pool.isClosed()) {
            pool.close();
            pool = null;
        }
    }

    @Override
    public CrowdFundingService create() throws Exception {
        return new CrowdFundingServiceImpl();
    }

    @Override
    public PooledObject<CrowdFundingService> wrap(CrowdFundingService value) {
        return new DefaultPooledObject<CrowdFundingService>(value);
    }
}