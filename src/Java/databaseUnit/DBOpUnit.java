package databaseUnit;

import dataModle.UserInfoModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//数据库操作类
public class DBOpUnit {
    public List<UserInfoModel> queryAllUser() throws SQLException{
        List<UserInfoModel> userInfoModelList=new ArrayList<UserInfoModel>();
        Connection connection=DBConUnit.getConnection();
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("select id,name,mobie,email,address from goddess");
        PreparedStatement preparedStatement=connection.prepareStatement(stringBuilder.toString());
        ResultSet resultSet=preparedStatement.executeQuery();
        UserInfoModel model=null;
        while (resultSet.next()){
            model=new UserInfoModel();
            model.setUserAccount(resultSet.getString("account"));
            model.setUserName(resultSet.getString("account"));
            model.setUserPassword(resultSet.getString("account"));
            userInfoModelList.add(model);
        }
        return userInfoModelList;
     }
}
