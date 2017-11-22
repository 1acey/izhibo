package dataModle;

import widget.CommonUnits;
import widget.Constants;
import widget.TokenUnits;

import java.io.Serializable;

public class JWTBaseModel implements Serializable {
    private static final long serialVersionUID = 3559533002594201715L;
    private String headerAndPayloadStr;//从token中解析出头部base64+"."+负载base64的字符串
    private String algStr;//从token中解析加密部分的数据
    private String payLoadStr;//从token中解析出来的负载数据
    private String iatStr;//从token中解析出来的token的签发时间
    private String expStr;//从token中解析出来的token的有效时间
    private JWTPayLoad jwtPayLoad;
    private String headerAndPayLoadByAlg;

    public String getPayLoadStr() {
        return payLoadStr;
    }

    public void setPayLoadStr(String payLoadStr) {
        this.payLoadStr = payLoadStr;
    }

    public String getAlgStr() {
        return algStr;
    }

    public void setAlgStr(String algStr) {
        this.algStr = algStr;
    }


    public String getHeaderAndPayloadStr() {
        return headerAndPayloadStr;
    }

    public void setHeaderAndPayloadStr(String headerAndPayloadStr) {
        this.headerAndPayloadStr = headerAndPayloadStr;
    }

    public JWTBaseModel(String token) {
        parseToken(token);
    }

    private void parseToken(String token) {
        String[] array =token.split("[.]");
        setHeaderAndPayloadStr(array[0] + "." + array[1]);
        setAlgStr(array[2]);
        setPayLoadStr(array[1]);
    }

    //从负载中解析出token的签发时间和过期时间
    private void parseTimeDataFromPayLoad() {

    }

    //将headerAndPayloadStr进行一次重加密，存放在headerAndPayLoadByAlg中，并与algStr进行比较。判断token是否有效,优先返回true
    public Boolean checkTokenValid() {
        this.headerAndPayLoadByAlg = CommonUnits.getStrByAlg_HS256(this.headerAndPayloadStr, Constants.JWT_SECERT);
        if (this.headerAndPayLoadByAlg.equals(getAlgStr()))
            return true;
        else
            return false;
    }

    //判断token是否过期
    public Boolean checkTokenIsExpired() {
        this.jwtPayLoad = CommonUnits.getObjectFromStrByBased64(getPayLoadStr());
        return CommonUnits.isDataExpired(this.jwtPayLoad.getIat()
                ,this.jwtPayLoad.getExp()
        ,"100");
    }
}
