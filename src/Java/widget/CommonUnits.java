package widget;

import net.sf.json.JSONArray;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import sun.security.provider.SHA;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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

    //将object对象经base64处理之后转化成字符串
    public static String getStrFromObjByBase64(Object object) {
        JSONArray jsonArray = getJsonArrayFromObj(object);
        BASE64Encoder base64Encoder = new BASE64Encoder();
        String result = null;
        result = base64Encoder.encode(jsonArray.toString().getBytes());
        return result;
    }

    //将base64编码之后的字符串进行解密并返回一个对象
    public static Object getObjectFromStrByBased64(String strByBase64) {
        BASE64Decoder base64Decoder = new BASE64Decoder();
        byte[] result = null;
        Object resultObj=null;
        try {
            result = base64Decoder.decodeBuffer(strByBase64);
        } catch (IOException e) {
            System.out.println("base64解密错误");
            e.printStackTrace();
        }
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(result);
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            try {
                resultObj=objectInputStream.readObject();
                byteArrayInputStream.close();
                objectInputStream.close();
            } catch (ClassNotFoundException e) {
                System.out.println("将byte[]转成对象的时候出错");
                e.printStackTrace();
            }
        } catch (IOException e) {
            System.out.println("读byte[]时候出错");
            e.printStackTrace();
        }
        return resultObj;

    }

    /**
     *
     * @param signedTime  数据的签名时间
     * @param survivalTime  数据的有效存活期限
     * @param nowTime  当前时间
     * @return
     */
    public static Boolean isDataExpired(String signedTime,String survivalTime,String nowTime){
        return true;
    }

    //将需要加密的明文与密钥secert进行加密，返回字符串
    public static String getStrByAlg_HS256(String strBeforeAlg, String secert) {
        try{
            MessageDigest sha = MessageDigest.getInstance("SHA-256");
            sha.update(strBeforeAlg.getBytes());
            sha.digest();
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return null;
    }
}
