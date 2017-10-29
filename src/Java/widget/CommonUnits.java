package widget;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUnits {
    public static Boolean stringDataIsValid(String data){
        if(null==data||"".equals(data.trim())){
            return false;
        }
        else{
            return true;
        }
    }
    public static Boolean stringDataIsValid(String data1,String data2){
        if(null==data1||"".equals(data1.trim())||null==data2||"".equals(data2.trim())){
            return false;
        }
        else{
            return true;
        }
    }

    public static Boolean isUserAccountIsValied(String userAccount){
        Pattern p = Pattern.compile("^(1[358])\\d{9}$");//用户名是否为手机号
            Matcher m = p.matcher(userAccount);
            if(m.matches())
                return true;
            else
                return false;
        }


}
