package pool;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import pool.MysqlProxy;
import utils.ConfigurationManager;
import utils.Constant;

public class CreateMysqlPool {

    private  GenericObjectPool<MysqlProxy> genericObjectPool;
    private static  CreateMysqlPool createMysqlPool;

    public static CreateMysqlPool getInstance(){
        if (createMysqlPool== null){
            createMysqlPool=new CreateMysqlPool();
        }
        return createMysqlPool;
    }
    public GenericObjectPool<MysqlProxy> getPoolObject(){
        return this.genericObjectPool;
    }
    private CreateMysqlPool(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            apply();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private   void apply(){

        if(this.genericObjectPool==null){
            synchronized (this) {
                //获取数据库的连接参数
                String jdbcUrl = ConfigurationManager.config.getString(Constant.JDBC_URL);
                String jdbcUser = ConfigurationManager.config.getString(Constant.JDBC_USER);
                String jdbcPassword = ConfigurationManager.config.getString(Constant.JDBC_PASSWORD);
                int size = ConfigurationManager.config.getInt(Constant.JDBC_DATASOURCE_SIZE);

                PooledMysqlClientFactory pooledMysqlClientFactory = new PooledMysqlClientFactory(jdbcUrl, jdbcUser, jdbcPassword);
                GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
                genericObjectPoolConfig.setMaxIdle(size);
                genericObjectPoolConfig.setMaxTotal(size);

                this.genericObjectPool = new GenericObjectPool<MysqlProxy>(pooledMysqlClientFactory, genericObjectPoolConfig);
            }
        }
    }
}
