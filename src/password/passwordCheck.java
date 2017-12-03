package password;

import java.security.MessageDigest;

/**
 * 实现判断当前输入密码是否和数据库中密码匹配
 * 输入：输入的密码passwordCur, 当前用户的密码加密时使用的盐值salt, 当前用户加盐加密后的密文passwordHash
 * 输出：若匹配返回true, 不匹配返回false
 */
public class passwordCheck {
    /*判断密码是否正确*/
    boolean passwordIsEqual(String passwordCur, String salt, String passwordHash) { //当前用户密码加密时使用的盐值salt
        String PS = "";
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            PS = util.byteArrayToHexString(md5.digest((salt+passwordCur).getBytes())); //采用salt+password的形式
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }

        return PS.equals(passwordHash);
    }
}
