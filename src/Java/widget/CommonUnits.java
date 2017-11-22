package widget;

import net.sf.json.*;

import java.util.Base64;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import dataModle.JWTPayLoad;
import widget.TokenUnits;

public class CommonUnits {
    public static Boolean stringDataIsValid(String data) {
        if (null == data || "".equals(data.trim())) {
            return false;
        } else {
            return true;
        }
    }

    public static Boolean stringDataIsValid(String data1, String data2) {
        if (null == data1 || "".equals(data1.trim()) || null == data2 || "".equals(data2.trim())) {
            return false;
        } else {
            return true;
        }
    }

    public static Boolean isUserAccountIsValied(String userAccount) {
        Pattern p = Pattern.compile("^(1[358])\\d{9}$");//用户名是否为手机号
        Matcher m = p.matcher(userAccount);
        if (m.matches())
            return true;
        else
            return false;
    }

    public static JSONArray getJsonArrayFromObj(Object object) {
        JSONArray jsonArray = JSONArray.fromObject(object);
        return jsonArray;
    }
    public static String getJsonValue(JSONObject jsonObject,String str){
        return jsonObject.getString(str);
    }

    //将object对象经base64处理之后转化成字符串
    public static String getStrFromObjByBase64(Object object) {
        JSONObject jsonObject=JSONObject.fromObject(object);
        String jsonarr=jsonObject.toString();
        String result = null;
        System.out.println(jsonarr);
        try {
            result = Base64.getEncoder().encodeToString(jsonarr.getBytes("utf-8"));
        }catch (IOException e){
            System.out.println("base64编码和序列化问题。");
        }
        return result;
    }

    //将base64编码之后的字符串进行解密并返回一个对象
    public static JWTPayLoad getObjectFromStrByBased64(String strByBase64) {
        byte[] result = null;
        String strresult ="";
        String iat ="";
        String exp ="";
        String uid ="";
        try {
            result = Base64.getDecoder().decode(strByBase64);
            strresult = new String(result,"utf-8");
            JSONObject jsonObject= JSONObject.fromObject(strresult);
            iat = getJsonValue(jsonObject,"iat");
            exp = getJsonValue(jsonObject,"exp");
            uid = getJsonValue(jsonObject,"uid");
        }catch (IOException e){
            System.out.println("base64解码和反序列化问题。");
        }
        JWTPayLoad resultObj = new JWTPayLoad(iat,exp,uid,"demo");
        return resultObj;
    }

    /**
     * @param signedTime   数据的签名时间
     * @param survivalTime 数据的有效存活期限
     * @param nowTime      当前时间
     * @return
     */
    public static Boolean isDataExpired(String signedTime, String survivalTime, String nowTime) {
        long expiredTime = Long.parseLong(survivalTime);
        if (expiredTime > System.currentTimeMillis()) {
            return true;
        } else
            return false;
    }

    //将需要加密的明文与密钥secert进行加密，返回字符串
    public static String getStrByAlg_HS256(String strBeforeAlg, String key) {
        try {
            SecretKeySpec singkey = new SecretKeySpec(key.getBytes(), "HmacSHA256");
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(singkey);
            return byte2hex(mac.doFinal(strBeforeAlg.getBytes()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String byte2hex(byte[] b) {
        StringBuilder hs = new StringBuilder();
        String stmp;
        for (int n = 0; b != null && n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 0XFF);
            if (stmp.length() == 1)
                hs.append('0');
            hs.append(stmp);
        }
        return hs.toString().toUpperCase();
    }
}
