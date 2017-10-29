package databaseUnit;



import java.sql.Connection;
import java.sql.DriverManager;

//数据库连接类
public class DBConUnit {
    private static volatile Connection connect = null;
    private DBConUnit(){};
    public static Connection getInstance(){
        if(connect==null)
            synchronized (DBConUnit.class)
            {
                if (connect==null)
                {
                    try {
                        Class.forName("com.mysql.jdbc.Driver");     //加载MYSQL JDBC驱动程序
                        //Class.forName("org.gjt.mm.mysql.Driver");
                        System.out.println("Success loading Mysql Driver!");
                    }
                    catch (Exception e) {
                        System.out.print("Error loading Mysql Driver!");
                        e.printStackTrace();
                    }
                    try {
                        connect = DriverManager.getConnection(
                                "jdbc:mysql://localhost:8700/userdata","root","1063382688");
                        //连接URL为   jdbc:mysql//服务器地址/数据库名  ，后面的2个参数分别是登陆用户名和密码
                        System.out.println("Success connect Mysql server!");
                    }
                    catch (Exception e) {
                        System.out.print("get data error!");
                        e.printStackTrace();
                    }
                    return connect;
                }

            }
        return connect;
    }
    /*
    public static void main(String args[]) {
        try {
            Class.forName("com.mysql.jdbc.Driver");     //加载MYSQL JDBC驱动程序
            //Class.forName("org.gjt.mm.mysql.Driver");
            System.out.println("Success loading Mysql Driver!");
        }
        catch (Exception e) {
            System.out.print("Error loading Mysql Driver!");
            e.printStackTrace();
        }
        try {
            connect = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/userdata","root","1063382688");
            //连接URL为   jdbc:mysql//服务器地址/数据库名  ，后面的2个参数分别是登陆用户名和密码
            System.out.println("Success connect Mysql server!");
        }
        catch (Exception e) {
            System.out.print("get data error!");
            e.printStackTrace();
        }
    }*/
    /*
    public static Connection getConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");     //加载MYSQL JDBC驱动程序
            //Class.forName("org.gjt.mm.mysql.Driver");
            System.out.println("Success loading Mysql Driver!");
        }
        catch (Exception e) {
            System.out.print("Error loading Mysql Driver!");
            e.printStackTrace();
        }
        try {
             connect = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/userdata","root","1063382688");
            //连接URL为   jdbc:mysql//服务器地址/数据库名  ，后面的2个参数分别是登陆用户名和密码
            System.out.println("Success connect Mysql server!");
        }
        catch (Exception e) {
            System.out.print("get data error!");
            e.printStackTrace();
        }
        return connect;
    }*/
}
