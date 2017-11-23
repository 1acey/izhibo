package servlet;

import controller.UserInfoManager;
import dataModle.HttpBaseModel;
import dataModle.UserInfoModel;
import databaseUnit.DBOpUnit;
import widget.CommonUnits;
import widget.Constants;
import widget.TokenUnits;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws IOException {
        doPost(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{


        HttpBaseModel baseModel=new HttpBaseModel();
        UserInfoModel userInfoModel = new UserInfoModel();
        String userAccount = request.getParameter("userAccount");
        String userPassword = request.getParameter("userPassword");
        //如果接收到的账号和密码都不为空
        if (CommonUnits.stringDataIsValid(userAccount, userPassword)) {
            //如果用户的账号存在
            try {
                if (UserInfoManager.loginCheck(userAccount,userPassword)){
                    userInfoModel.setUserAccount(userAccount);
                    userInfoModel.setUserPassword(userPassword);
                    String userName = UserInfoManager.getUserName(userAccount);
                    userInfoModel.setUserName(userName);
                    //如果用户的账号密码正确,此时为用户生成一个token
                    String token= new TokenUnits.Builder()
                            .setExp(UserInfoManager.getExpiredTime())
                            .setIat(UserInfoManager.getCurrentTime())
                            .setTargetUser(userAccount)
                            .setUid(UserInfoManager.getUidByUserAccount(userAccount))
                            .createToken();
                    userInfoModel.setUserToken(token);
                    baseModel.setCode(Constants.CODE_SUCCESS);
                    baseModel.setMsg(Constants.LOGIN_SUCCESS);
                    baseModel.setData(userInfoModel);

                } else {
                    baseModel.setCode(Constants.CODE_THREE);
                    baseModel.setMsg(Constants.LOGIN_ERROR);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }


        } else {
            baseModel.setCode(Constants.CODE_TWO);
            baseModel.setMsg(Constants.LOGIN_USERDATA_SOMETHINGNULL);

        }
        response.setHeader("content-type", "text/html;charset=UTF-8");
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        OutputStream outputStream = response.getOutputStream();
        outputStream.write(CommonUnits.getJsonObjectFromObj(baseModel).toString().getBytes("UTF-8"));
        System.out.println("数据返回成功。");

    }
}

