package servlet;

import controller.UserInfoManager;
import net.sf.json.JSONObject;
import widget.CommonUnits;
import widget.Constants;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;

public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JSONObject jsonResponse = new JSONObject();
        //UserInfoModel userInfoModel = new UserInfoModel();
        String userAccount = request.getParameter("userAccount");
        String userPassword = request.getParameter("userPassword");
        String userName = request.getParameter("userName");
        String deviceId = request.getParameter("deviceId");
        System.out.println(userAccount);
        System.out.println(userPassword);
        System.out.println(deviceId);
        //如果接收到的账号和密码都不为空
        if (CommonUnits.stringDataIsValid(userAccount, userPassword)) {
            //判断账号是否合法
            if (CommonUnits.isUserAccountIsValied(userAccount)) {

                //账号合法写入数据库
                try{
                    if (UserInfoManager.isInsertDataSuccess(userAccount,userPassword,userName)){
                        UserInfoManager.createUidByUserAccount(userAccount,deviceId);//为用户创建uid,为客户端登录互踢的实现作准备
                        jsonResponse.put(Constants.CODE, Constants.CODE_SUCCESS);
                        jsonResponse.put(Constants.MSG, Constants.REGISTER_SUCCESS);
                    }
                    else {
                        jsonResponse.put(Constants.CODE, Constants.CODE_THREE);
                        jsonResponse.put(Constants.MSG, Constants.REGISTER_USERDATA_ACCOUNTCONTAINS);

                    }
                }catch (SQLException e)
                {
                    e.printStackTrace();
                }
//                jsonResponse.put(Constants.CODE, Constants.CODE_SUCCESS);
//                jsonResponse.put(Constants.MSG, Constants.REGISTER_SUCCESS);
            } else {
                jsonResponse.put(Constants.CODE, Constants.CODE_FIVE);
                jsonResponse.put(Constants.MSG, Constants.REGISTER_USERDATA_ACCOUNTVALID);
            }

        } else {
            jsonResponse.put(Constants.CODE, Constants.CODE_TWO);
            jsonResponse.put(Constants.MSG, Constants.LOGIN_USERDATA_SOMETHINGNULL);
        }
        response.setHeader("content-type", "text/html;charset=UTF-8");
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        OutputStream outputStream = response.getOutputStream();
        outputStream.write(jsonResponse.toString().getBytes("UTF-8"));
    }
}

