package widget;


public class Constants {
    public static final String CODE="code";
    public static final String DATA="data";
    public static final String MSG="msg";
    //账号或密码为空
    public static final String CODE_TWO="2";
    //账号不存在
    public static final String CODE_THREE="3";
    //密码错误
    public static final String CODE_FOUR="4";
    //账号不合法
    public static final String CODE_FIVE="5";
    public static final String CODE_SUCCESS="200";
    public static final String LOGIN_USERDATA_ACCOUNTNOTCONTAINS="账号不存在";
    public static final String LOGIN_ERROR="账号或密码错误";
    public static final String LOGIN_SUCCESS="登录成功";
    public static final String LOGIN_USERDATA_SOMETHINGNULL="账号或密码不能为空";
    public static final String LOGIN_USERDATA_PASSWORDERROR="密码错误";
    public static final String REGISTER_SUCCESS="注册成功";
    public static final String REGISTER_USERDATA_ACCOUNTVALID="账号不合法";
    public static final String REGISTER_USERDATA_ACCOUNTNOTCONTAINS="账号不存在";
    public static final String REGISTER_USERDATA_ACCOUNTCONTAINS="账号已存在";
    public static final String JWT_PAYLOAD_ISS="UESTC";
    public static final String JWT_HEADER_TYPE="JWT";
    public static final String JWT_HEADER_ALG="HS256";
    public static final String JWT_SECERT="132SDASD45DSD";//jwt所用到的密钥
    public static final String CODE_THOUSAND="1000";//该code表示token过期
    public static final String CODE_SIX="6";//该code表示token无效
    public static final String JWT_TOKEN_VALID="验证token合法";
    public static final String JWT_TOKEN_EXPIRED="token失效，请重新登录";
    public static final String JWT_TOKEN_INVALID="token错误，请重试";

}
