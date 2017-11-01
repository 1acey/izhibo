package controller;

import dataModle.AttentionAnchorModel;
import dataModle.JWTBaseModel;
import dataModle.JWTHeader;
import dataModle.UserInfoModel;
import databaseUnit.DBConUnit;
import databaseUnit.DBOpUnit;
import jwt.TokenState;

import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserInfoManager {
    public static boolean checkUserLogin(UserInfoModel model) {
        try {
            if (DBOpUnit.getInstance().queryAllUser().contains(model))
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }

    public static Boolean checkUserValid(UserInfoModel model) {
        return true;
    }

    public static Boolean checkUserAccountValid(String userAccount) {
        Pattern p = Pattern.compile("^(1[358])\\d{9}$");//用户名是否为手机号
        Matcher m = p.matcher(userAccount);
        if (m.matches())
            return true;
        else
            return false;
    }

    public static Boolean loginCheck(String userAccount, String userPassword) throws SQLException {
        return DBOpUnit.getInstance().loginCheck(userAccount, userPassword);

    }

    public static Boolean isInsertDataSuccess(String userAccount, String userPassword, String userName) throws SQLException {
        return DBOpUnit.getInstance().insertData(userAccount, userPassword, userName);
    }

    public static void createUidByUserAccount(String userAccount) {
        DBOpUnit.getInstance().createUidByUserAccount(userAccount);

    }

    public static String getUidByUserAccount(String userAccount) {
        return DBOpUnit.getInstance().getUidByUserAccount(userAccount);
    }
    //解析token。并判断token的有效性和是否已经过期，返回的结果是一个枚举类型
    public static TokenState checkToken(String token){
        JWTBaseModel jwtBaseModel=new JWTBaseModel(token);
        //如果token检查是有效的
        if(jwtBaseModel.checkTokenValid()){
            //如果token未过期
            if(jwtBaseModel.checkTokenIsExpired())
                return TokenState.VALID;//token是有效的
            else
                return TokenState.EXPIRED;//token已过期

        }
        else {
            return TokenState.INVALID;//token是无效的
        }
    }
    public static String getUidFromToken(String token){
        return null;
    }

    public static List<AttentionAnchorModel>getAttentionAnchorList(String uid){
        return DBOpUnit.getInstance().getAttentionAnchorListByUid(uid);
    }
}
