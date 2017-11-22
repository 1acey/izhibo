package dataModle;

import widget.Constants;

import java.io.Serializable;

public class JWTPayLoad implements Serializable {
    private static final long serialVersionUID = 33300242015L;
    private String iss;//jwt的签发者
    private String iat;//jwi的签发时间。这里采用时间戳的形式
    private String exp;//jwt的过期时间，规定jwt的有效期为一周
    private String uid;//jwt的接受者的用户id,用来方便数据库操作
    private String targetUser;//jwt的接受者，这里用该接受者的账号来代替。

    public String getIat() {
        return iat;
    }

    public void setIat(String iat) {
        this.iat = iat;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public JWTPayLoad(String iat, String exp, String uid, String targetUser) {
        this.iss = Constants.JWT_PAYLOAD_ISS;
        this.iat = iat;
        this.exp = exp;
        this.uid = uid;
        this.targetUser = targetUser;
    }

}
