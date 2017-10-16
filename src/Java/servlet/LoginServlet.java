package servlet;

import controller.UserInfoManager;
import dataModle.UserInfoModel;
import net.sf.json.JSONObject;
import widget.CommonUnits;
import widget.Constants;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws IOException{
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JSONObject jsonResponse = new JSONObject();
        UserInfoModel userInfoModel = new UserInfoModel();
        String userAccount = request.getParameter("userAccount");
        String userPassword = request.getParameter("userPassword");
        //如果接收到的账号和密码都不为空
        if (CommonUnits.stringDataIsValid(userAccount, userPassword)) {
            //如果用户的账号存在
            if (UserInfoManager.checkUserAccountValid(userAccount)) {
                userInfoModel.setUserAccount(userAccount);
                userInfoModel.setUserPassword(userPassword);
                //如果用户的账号密码正确,登录成功
                if (UserInfoManager.checkUserValid(userInfoModel)) {
                    jsonResponse.put(Constants.CODE, Constants.CODE_SUCCESS);
                    jsonResponse.put(Constants.DATA, Constants.LOGIN_SUCCESS);
                } else {
                    jsonResponse.put(Constants.CODE, Constants.CODE_FOUR);
                    jsonResponse.put(Constants.DATA, Constants.LOGIN_USERDATA_PASSWORDERROR);
                }
            } else {
                jsonResponse.put(Constants.CODE, Constants.CODE_THREE);
                jsonResponse.put(Constants.DATA, Constants.LOGIN_USERDATA_ACCOUNTNOTCONTAINS);
            }


        } else {
            jsonResponse.put(Constants.CODE, Constants.CODE_TWO);
            jsonResponse.put(Constants.DATA, Constants.LOGIN_USERDATA_SOMETHINGNULL);
        }
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        //实现跨域访问
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        Writer writer=response.getWriter();
        writer.write(jsonResponse.toString());
        //消息发出
        writer.flush();
    }
}
