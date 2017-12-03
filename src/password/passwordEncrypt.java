package password;

import java.security.MessageDigest;
import java.util.Random;

/**
 * 实现对密码字符串password进行MD5加盐加密
 * 输入：密码字符串password
 * 输出：密码密文PS[0], 盐值PS[1]
 */


public class passwordEncrypt {
    /*产生8位随机数盐值salt并返回*/
    private String generateSalt() {
        Random rnd = new Random();
        StringBuilder salt = new StringBuilder(8);
        salt.append(rnd.nextInt(99999999));
        int len = salt.length();
        if(len < 8) { //若产生的随机数小于8位，则在末尾补"0"直到补满8位
            for(int i = 0; i < 8 - len; i ++) {
                salt.append("0");
            }
        }

        return salt.toString();
    }
    /*对输入的password进行加盐加密并返回加密后的结果*/
    public String[] encrypt(String password) {
        String[] PS = new String[2]; //PS[0]存密码密文, PS[1]存盐值

        PS[1] = generateSalt();

        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] passwordHash = md5.digest((PS[1]+password).getBytes()); //采用salt+password的形式
            PS[0] = util.byteArrayToHexString(passwordHash);
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }

        return PS;
    }
}

/*工具类*/
class util {
    /*将byte数组转换为16进制字符串*/
    public static String byteArrayToHexString(byte[] b){
        StringBuilder sb = new StringBuilder("");
        for(byte i: b) {
            int n = i & 0xFF;
            String hexn = Integer.toHexString(n);
            if(hexn.length() < 2) {
                sb.append(0);
            }
            sb.append(hexn);
        }

        return sb.toString();
    }
}
