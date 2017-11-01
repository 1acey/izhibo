package servlet;

import controller.UserInfoManager;
import dataModle.AttentionAnchorBaseModel;
import dataModle.AttentionAnchorModel;
import dataModle.HttpBaseModel;
import jwt.TokenState;
import net.sf.json.JSONObject;
import widget.CommonUnits;
import widget.Constants;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

//获取某个账号所关注的主播的相关数据
public class GetAttentionAnchorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        JSONObject jsonResponse = new JSONObject();
        AttentionAnchorBaseModel attentionAnchorBaseModel = new AttentionAnchorBaseModel();
        String token = request.getParameter("token");
        TokenState tokenState = UserInfoManager.checkToken(token);
        //接收到的token是有效的话
        if (tokenState.equals(TokenState.VALID)) {
            String userUid = UserInfoManager.getUidFromToken(token);//获得uid
            List<AttentionAnchorModel> list = UserInfoManager.getAttentionAnchorList(userUid);
            attentionAnchorBaseModel.setData(list);
            attentionAnchorBaseModel.setCode(Constants.CODE_SUCCESS);
            attentionAnchorBaseModel.setMsg(Constants.LOGIN_SUCCESS);
        }
        //接收到的token已经过期
        else if(tokenState.equals(TokenState.EXPIRED)){
            attentionAnchorBaseModel.setCode(Constants.CODE_THOUSAND);
            attentionAnchorBaseModel.setMsg(Constants.JWT_TOKEN_EXPIRED);
        }
        else {
            attentionAnchorBaseModel.setCode(Constants.CODE_SIX);
            attentionAnchorBaseModel.setMsg(Constants.JWT_TOKEN_INVALID);

        }
        response.setHeader("content-type", "text/html;charset=UTF-8");
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        OutputStream outputStream = response.getOutputStream();
        outputStream.write(CommonUnits.getJsonArrayFromObj(attentionAnchorBaseModel)
                .toString()
                .getBytes("UTF-8"));
    }
}

