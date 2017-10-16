package widget;

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

}
