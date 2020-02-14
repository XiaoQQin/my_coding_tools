import org.apache.commons.pool2.impl.GenericObjectPool;
import pool.CreateMysqlPool;
import pool.MysqlProxy;

public class test {
    public static void main(String[] args) {

        try {
            GenericObjectPool<MysqlProxy> poolObject = CreateMysqlPool.getInstance().getPoolObject();
            MysqlProxy mysqlProxy = poolObject.borrowObject();
            String sql = "INSERT INTO test_pool VALUES(?)";

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
