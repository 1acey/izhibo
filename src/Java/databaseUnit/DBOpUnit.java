package databaseUnit;

import dataModle.AttentionAnchorModel;
import dataModle.UserInfoModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//数据库操作类
public class DBOpUnit {
    private static volatile DBOpUnit dbOpUnit = null;
    private DBOpUnit() {
    }

    public static DBOpUnit getInstance() {
        if (dbOpUnit == null)
            synchronized (DBOpUnit.class) {
                if (dbOpUnit == null)
                    dbOpUnit = new DBOpUnit();
            }
        return dbOpUnit;
    }

    public List<UserInfoModel> queryAllUser() throws SQLException {
        List<UserInfoModel> userInfoModelList = new ArrayList<UserInfoModel>();
        Connection connection = DBConUnit.getInstance();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select * from userdata");
        PreparedStatement preparedStatement = connection.prepareStatement(stringBuilder.toString());
        ResultSet resultSet = preparedStatement.executeQuery();
        UserInfoModel model = null;
        while (resultSet.next()) {
            model = new UserInfoModel();
            model.setUserAccount(resultSet.getString("UserAccount"));
            model.setUserName(resultSet.getString("UserPassword"));
            model.setUserName(resultSet.getString("UserName"));
            userInfoModelList.add(model);
        }
        return userInfoModelList;
    }

    //建表方法
    public Boolean isTableExist0(Connection connection) throws SQLException{
        String tableName = "userrelative";
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet resultSet0 = metaData.getTables(null, null, tableName, null);
        if (resultSet0.next()) {
            return true;//表存在返回true
        } else {
            //不存在创建表
            String stringBuilder1;
            stringBuilder1 = ("CREATE TABLE userrelative(" +
                    "UserAccount VARCHAR(20) not null," +
                    "UserUid VARCHAR(40) not null," +
                    "DeviceId VARCHAR(40) not null)");
            Statement stmt;
            stmt = connection.createStatement();
            int i = stmt.executeUpdate(stringBuilder1);
            if (i == 0)
                return true;
            else {
                System.out.println("建表失败");
                return false;
            }
        }
    }
    public Boolean isTableExist(Connection connection) throws SQLException {
        String tableName = "userdata";
        String stringBuilder1;
        stringBuilder1 = ("CREATE TABLE userdata(" +
                "UserAccount VARCHAR(20) not null," +
                "UserPassword VARCHAR(40) not null," +
                "UserName VARCHAR(40) not null)");
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet resultSet0 = metaData.getTables(null, null, tableName, null);

        if (resultSet0.next()) {
            return true;//表存在返回true
        } else {
            //不存在创建表userdata
            Statement stmt;
            stmt = connection.createStatement();
            int i = stmt.executeUpdate(stringBuilder1);
            if (i == 0)
                return true;
            else {
                System.out.println("建表失败");
                return false;
            }

        }
    }
    //数据库存入数据，密码加密
    public boolean insertData(String userAccount0, String userPassword0, String userName0) throws SQLException {
        Connection connection = DBConUnit.getInstance();
        //建表成功，插入数据
        if (isTableExist(connection)) {
            StringBuilder stringBuilder0 = new StringBuilder();
            stringBuilder0.append("select UserAccount from userdata where UserAccount=" + userAccount0);
            PreparedStatement preparedStatement0 = connection.prepareStatement(stringBuilder0.toString());
            ResultSet resultSet = preparedStatement0.executeQuery();
            if (resultSet.next())//判断账号是否存在
            {
                System.out.println("已存在");
                return false;
            } else {

                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("insert into userdata(UserAccount,UserPassword,UserName) value(?,?,?)");
                PreparedStatement preparedStatement = connection.prepareStatement(stringBuilder.toString());
                preparedStatement.setString(1, userAccount0);
                preparedStatement.setString(2, userPassword0);
                preparedStatement.setString(3, userName0);
                preparedStatement.executeUpdate();
                return true;
            }
        } else {
            return false;
        }
    }

    //创建一个新表，根据用户的账号生成一个每位用户都不同的uid，uid与客户端的设备id一一对应，用来表示每一位用户
    //该表用来客户端登录互踢逻辑的实现用
    public boolean createUidByUserAccount(String userAccount,String deviceId) throws SQLException {
        Connection connection = DBConUnit.getInstance();
        UUID uuid=UUID.randomUUID();
        String s_uid = uuid.toString();

        if (isTableExist0(connection)) {
            StringBuilder stringBuilder0 = new StringBuilder();
            stringBuilder0.append("select UserAccount from userrelative where UserAccount=" + userAccount);
            PreparedStatement preparedStatement0 = connection.prepareStatement(stringBuilder0.toString());
            ResultSet resultSet = preparedStatement0.executeQuery();
            if (resultSet.next())//判断账号是否存在
            {
                System.out.println("用户" + userAccount + "已存在.");
                return false;
            } else {

                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("insert into userrelative(UserAccount,UserUid,DeviceId) value(?,?,?)");
                PreparedStatement preparedStatement = connection.prepareStatement(stringBuilder.toString());
                preparedStatement.setString(1, userAccount);
                preparedStatement.setString(2, s_uid);
                preparedStatement.setString(3, "qqqqqqqqqqq");
                preparedStatement.executeUpdate();
                return true;
            }
        } else {
            return false;
        }

    }

    //根据用户的账号来获取uid
    public String getUidByUserAccount(String userAccount) throws SQLException{
        Connection connection = DBConUnit.getInstance();
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append(
                "select UserUid from userrelative where UserAccount=" + userAccount);
        PreparedStatement preparedStatement1 = connection.prepareStatement(stringBuilder1.toString());
        ResultSet resultSet = preparedStatement1.executeQuery();
        if (resultSet.next()){
            String s = resultSet.getString(1);
            return s;
        }else
            return null;
    }

    //根据uid到数据库中查找，从而找出某个客户所关注的所有主播数据，返回的参数类型是list
    public List<AttentionAnchorModel> getAttentionAnchorListByUid(String uid) {
        return null;
    }

    public boolean loginCheck(String userAccount, String userPassword) throws SQLException {
        Connection connection = DBConUnit.getInstance();
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append(
                "select UserAccount,UserPassword from userdata where UserAccount=" + userAccount + " and UserPassword=" + userPassword);
        PreparedStatement preparedStatement1 = connection.prepareStatement(stringBuilder1.toString());
        ResultSet resultSet = preparedStatement1.executeQuery();
        if (resultSet.next())
            return true;
        else
            return false;
    }
    public String getUserName(String userAccount) throws SQLException{
        Connection connection = DBConUnit.getInstance();
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append(
                "select UserName from userdata where UserAccount=" + userAccount);
        PreparedStatement preparedStatement1 = connection.prepareStatement(stringBuilder1.toString());
        ResultSet resultSet = preparedStatement1.executeQuery();
        if (resultSet.next()){
            String s = resultSet.getString(1);
            return s;
        }else
            return null;
    }
}
