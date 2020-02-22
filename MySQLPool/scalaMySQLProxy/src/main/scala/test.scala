
object test {
  def main(args: Array[String]): Unit = {
    // 获取对象池单例对象
    val mySqlPool = CreateMySqlPool()
    // 从对象池中提取对象
    val client = mySqlPool.borrowObject()
    val sql="INSERT INTO test_pool (id,value) VALUES(?,?)"
    val params=Array("scala","test")
    client.executeUpdate(sql,params.toArray)
  }
}
