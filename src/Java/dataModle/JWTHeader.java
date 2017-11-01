package dataModle;

import net.sf.json.JSONArray;

import widget.Constants;

public class JWTHeader {
    private String type;//默认都是JWT
    private String alg;//加密算法暂定为HS256

    public JWTHeader() {
        this.type = Constants.JWT_HEADER_TYPE;
        this.alg = Constants.JWT_HEADER_ALG;
    }


}
