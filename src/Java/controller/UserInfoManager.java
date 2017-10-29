package controller;

import dataModle.UserInfoModel;
import databaseUnit.DBConUnit;
import databaseUnit.DBOpUnit;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserInfoManager {
    public static boolean checkUserLogin(UserInfoModel model) {
        try {
            if(DBOpUnit.getInstance().queryAllUser().contains(model))
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }
    public static Boolean checkUserValid(UserInfoModel model){
        return true;
    }
    public static Boolean checkUserAccountValid(String userAccount){
        Pattern p = Pattern.compile("^(1[358])\\d{9}$");//用户名是否为手机号
        Matcher m = p.matcher(userAccount);
        if(m.matches())
            return true;
        else
            return false;
    }
    public static Boolean isInsertDataSuccess(String userAccount,String userPassword,String userName) throws SQLException {
        return   DBOpUnit.getInstance().insertData(userAccount,userPassword,userName);
    }

}
