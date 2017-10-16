package controller;

import dataModle.UserInfoModel;
import databaseUnit.DBConUnit;
import databaseUnit.DBOpUnit;

import java.sql.SQLException;

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
        return true;
    }
}
