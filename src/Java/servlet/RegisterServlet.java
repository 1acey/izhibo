package servlet;

import controller.UserInfoManager;
import dataModle.BaseModel;
import dataModle.HttpBaseModel;
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
        HttpBaseModel baseModel = new HttpBaseModel();
        JSONObject jsonResponse = new JSONObject();
        String userAccount = request.getParameter("userAccount");
        String userPassword = request.getParameter("userPassword");
        String userName = request.getParameter("userName");
        String deviceId = request.getParameter("deviceId");
        //如果接收到的账号和密码都不为空
        if (CommonUnits.stringDataIsValid(userAccount, userPassword)) {
            //判断账号是否合法
            if (CommonUnits.isUserAccountIsValied(userAccount)) {

                //账号合法写入数据库
                try{
                    if (UserInfoManager.isInsertDataSuccess(userAccount,userPassword,userName)){
                        UserInfoManager.createUidByUserAccount(userAccount,deviceId);//为用户创建uid,为客户端登录互踢的实现作准备
                        baseModel.setCode(Constants.CODE_SUCCESS);
                        baseModel.setMsg(Constants.REGISTER_SUCCESS);
                    }
                    else {
                        baseModel.setCode(Constants.CODE_THREE);
                        baseModel.setMsg(Constants.REGISTER_USERDATA_ACCOUNTCONTAINS);
                    }
                }catch (SQLException e)
                {
                    e.printStackTrace();
                }
            } else {
                baseModel.setCode(Constants.CODE_FIVE);
                baseModel.setMsg(Constants.REGISTER_USERDATA_ACCOUNTVALID);
            }

        } else {
            baseModel.setCode(Constants.CODE_TWO);
            baseModel.setMsg(Constants.LOGIN_USERDATA_SOMETHINGNULL);
        }
        response.setHeader("content-type", "text/html;charset=UTF-8");
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        OutputStream outputStream = response.getOutputStream();
        outputStream.write(CommonUnits.getJsonArrayFromObj(baseModel).toString().getBytes("UTF-8"));
    }
}

