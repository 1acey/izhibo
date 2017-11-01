package jwt;

/**
 * token的枚举，用来表示token的不同状态
 */

public enum  TokenState {
    //token过期
    EXPIRED("EXPIRED"),
    //token无效
    INVALID("INVALID"),
    //token有效
    VALID("VALID");

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    private String state;
    private TokenState(String state){
        this.state=state;
    }

    public String toString(){
        return this.state;
    }
   //从string字符串中得到tokenState
    public static TokenState getTokenStateFromStr(String tokenState){
        TokenState[] states=TokenState.values();
        TokenState tokenState1=null;
        for(TokenState tokenState2:states){
            if(states.toString().equals(tokenState)){
                tokenState1=tokenState2;
                break;
            }
        }
        return tokenState1;
    }
}
