package pool;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

import java.io.Serializable;

public class PooledMysqlClientFactory extends BasePooledObjectFactory<MysqlProxy> implements Serializable {
    private String jdbcUrl;
    private String jdbcUser;
    private String jdbcPassword;
    PooledMysqlClientFactory(String jdbcUrl,String jdbcUser,String jdbcPassword){
        this.jdbcUrl=jdbcUrl;
        this.jdbcUser=jdbcUser;
        this.jdbcPassword=jdbcPassword;
    }
    public MysqlProxy create() throws Exception {
        return new MysqlProxy(jdbcUrl,jdbcUser,jdbcPassword);
    }

    public PooledObject<MysqlProxy> wrap(MysqlProxy mysqlProxy) {
        return new DefaultPooledObject(mysqlProxy);
    }

    @Override
    public void destroyObject(PooledObject<MysqlProxy> p) throws Exception {
        p.getObject().shutdown();
        super.destroyObject(p);
    }
}
