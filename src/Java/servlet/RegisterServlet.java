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
import java.io.PrintWriter;
import java.io.Writer;
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
        //System.out.println(userAccount);
        //System.out.println(userPassword);
        //System.out.println(userName);
        //如果接收到的账号和密码都不为空
        if (CommonUnits.stringDataIsValid(userAccount, userPassword)) {
            //判断账号是否合法
            if (CommonUnits.isUserAccountIsValied(userAccount)) {

                //账号合法写入数据库
                try{
                    if (UserInfoManager.isInsertDataSuccess(userAccount,userPassword,userName)){
                        jsonResponse.put(Constants.CODE, Constants.CODE_SUCCESS);
                        jsonResponse.put(Constants.MSG, Constants.REGISTER_SUCCESS);
                    }
                    else {
                        jsonResponse.put(Constants.CODE, Constants.CODE_THREE);
                        jsonResponse.put(Constants.MSG, Constants.REGISTER_USERDATA_ACCOUNTNOTCONTAINS);

                    }
                }catch (SQLException e)
                {
                    e.printStackTrace();
                }
                jsonResponse.put(Constants.CODE, Constants.CODE_SUCCESS);
                jsonResponse.put(Constants.MSG, Constants.REGISTER_SUCCESS);
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

        //response.setHeader("Content-type","register/josn;charset=UTF-8");
        //response.setContentType("register/json");
        //response.setCharacterEncoding("UTF-8");
        //实现跨域访问
        //response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        //PrintWriter writer=response.getWriter();
        //writer.write(jsonResponse.toString());
        //消息发出
        //writer.flush();
    }
}
