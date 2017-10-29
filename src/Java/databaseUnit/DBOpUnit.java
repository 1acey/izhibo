package databaseUnit;

import dataModle.UserInfoModel;

import javax.jws.soap.SOAPBinding;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//数据库操作类
public class DBOpUnit {
    private static volatile DBOpUnit dbOpUnit=null;
    private DBOpUnit(){};
    public static DBOpUnit getInstance(){
        if(dbOpUnit==null)
            synchronized (DBOpUnit.class)
            {
                if (dbOpUnit==null)
                    dbOpUnit=new DBOpUnit();
            }
        return dbOpUnit;
    }
    public List<UserInfoModel> queryAllUser() throws SQLException{
        List<UserInfoModel> userInfoModelList=new ArrayList<UserInfoModel>();
        Connection connection= DBConUnit.getInstance();
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("select * from userdata");
        PreparedStatement preparedStatement=connection.prepareStatement(stringBuilder.toString());
        ResultSet resultSet=preparedStatement.executeQuery();
        UserInfoModel model=null;
        while (resultSet.next()){
            model=new UserInfoModel();
            model.setUserAccount(resultSet.getString("UserAccount"));
            model.setUserName(resultSet.getString("UserPassword"));
            model.setUserName(resultSet.getString("UserName"));
            userInfoModelList.add(model);
        }
        return userInfoModelList;
     }
     //建表方法
    public Boolean isTableExist(Connection connection)throws SQLException{
        String tableName="userdata";
        String stringBuilder1;
        stringBuilder1=("CREATE TABLE userdata(" +
                "UserAccount VARCHAR(255) not null," +
                "UserPassword VARCHAR(255) not null," +
                "UserName VARCHAR(255) not null)");
        DatabaseMetaData metaData=connection.getMetaData();
        ResultSet resultSet0=metaData.getTables(null,null,tableName,null);

        if (resultSet0.next()){
            return true;//表存在返回true
        }else {
            //不存在创建表userdata
            Statement stmt;
            stmt = connection.createStatement();
            int i = stmt.executeUpdate(stringBuilder1);
            if(i==0)
                return true;
            else
            {
                System.out.println("建表失败");
                return false;}

        }
    }
    //数据库存入数据，密码加密
    public boolean insertData(String useraccount0,String userpassword0,String username0) throws SQLException{
        Connection connection = DBConUnit.getInstance();
        //建表成功，插入数据
        if(isTableExist(connection)){
            StringBuilder stringBuilder0 = new StringBuilder();
            stringBuilder0.append("select UserAccount from userdata where UserAccount="+ useraccount0);
            PreparedStatement preparedStatement0 = connection.prepareStatement(stringBuilder0.toString());
            ResultSet resultSet = preparedStatement0.executeQuery();
            if(resultSet.next())//判断账号是否存在
            {
                System.out.println("已存在");
                return false;
            }
            else
            {

                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("insert into userdata(UserAccount,UserPassword,UserName) value(?,?,?)");
                PreparedStatement preparedStatement = connection.prepareStatement(stringBuilder.toString());
                preparedStatement.setString(1, useraccount0);
                preparedStatement.setString(2, userpassword0);
                preparedStatement.setString(3, username0);
                preparedStatement.executeUpdate();
                return true;
        }
        }
        else {
            return false;
        }
    }
    public boolean logincheck(String useraccount1,String userpassword1) throws SQLException{
        Connection connection = DBConUnit.getInstance();
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append(
                "select UserAccount,UserPassword from userdata where UserAccount="+ useraccount1 + " and UserPassword=" + userpassword1);
        PreparedStatement preparedStatement1 = connection.prepareStatement(stringBuilder1.toString());
        ResultSet resultSet = preparedStatement1.executeQuery();
        if(resultSet.next())
            return true;
        else
            return false;
    }
}
