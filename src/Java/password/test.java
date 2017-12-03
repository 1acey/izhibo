package password;

public class test {
    /*测试*/
    public static void main(String[] args) {
        String passwd = "s$g14_4s351r", passwdCur = "sg14_43$51w"; //passwd:用户密码, passwdCur:当前输入密码
        passwordEncrypt passwordencrypt = new passwordEncrypt();
        String[] PS = passwordencrypt.encrypt(passwd); //PS:加密后密文+盐值

        System.out.println("用户密码:" + passwd + "  盐值:" + PS[1] + "  加密后密文:" + PS[0]);

        passwordCheck passwordcheck = new passwordCheck();
        if(passwordcheck.passwordIsEqual(passwdCur, PS[1], PS[0]))  {
            System.out.println("当前输入密码:" + passwdCur + "与用户密码:" + passwd + "匹配");
        }
        else {
            System.out.println("当前输入密码:" + passwdCur + "与用户密码:" + passwd + "不匹配");
        }

    }
}
