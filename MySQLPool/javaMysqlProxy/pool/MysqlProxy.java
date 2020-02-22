package pool;

import java.sql.*;
import java.util.ArrayList;

public class MysqlProxy {

    private Connection mysqlConnection;

    MysqlProxy(String jdbcUrl,String jdbcUser,String jdbcPassword){
        try {
            this.mysqlConnection = DriverManager.getConnection(jdbcUrl,jdbcUser,jdbcPassword);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     *  执行增、删、改操作
     * @param sql
     * @param params
     * @return
     */
    public int executeUpdate(String sql,Object[] params){
        int result=0;
        try {
            //关闭自动体提交
            this.mysqlConnection.setAutoCommit(false);
            PreparedStatement preparedStatement=this.mysqlConnection.prepareStatement(sql);
            if(params !=null && params.length>0){
               for(int i=0;i<params.length;i++){
                   preparedStatement.setObject(i+1,params[i]);
               }
            }
            result=preparedStatement.executeUpdate();
            mysqlConnection.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  result;
    }

    /**
     * 执行查询操作
     * @param sql
     * @param params
     * @return
     */
    public ResultSet exectuteQuery(String sql,Object[] params){
        ResultSet result=null;
        try {
           PreparedStatement preparedStatement=this.mysqlConnection.prepareStatement(sql);
            if(params !=null && params.length>0){
                for(int i=0;i<params.length;i++){
                    preparedStatement.setObject(i+1,params[i]);
                }
            }
            result=preparedStatement.executeQuery();
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }


    public int[] executeBatch(String sql,Object[][] params){
        int[] result=new int[params.length];
        try {
            PreparedStatement preparedStatement=this.mysqlConnection.prepareStatement(sql);
            if(params !=null && params.length>0){
               for ( int j=0;j<params.length;j++){
                   for(int i=0;i<params[j].length;i++){
                       preparedStatement.setObject(i+1,params[j][i]);
                   }
               }
            }
           result = preparedStatement.executeBatch();
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public  void shutdown(){
        try {
            mysqlConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
