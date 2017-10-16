package databaseUnit;

import java.sql.Connection;

//数据库连接类
public class DBConUnit {
    private static Connection connection=null;
    public static Connection getConnection(){
        return connection;
    }
}
