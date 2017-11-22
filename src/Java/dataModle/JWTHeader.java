package dataModle;

import widget.Constants;

import java.io.Serializable;

public class JWTHeader implements Serializable{
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAlg() {
        return alg;
    }

    public void setAlg(String alg) {
        this.alg = alg;
    }

    private String type;//默认都是JWT
    private String alg;//加密算法暂定为HS256

    public JWTHeader() {
        this.type = Constants.JWT_HEADER_TYPE;
        this.alg = Constants.JWT_HEADER_ALG;
    }


}
