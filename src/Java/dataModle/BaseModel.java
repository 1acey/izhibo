package dataModle;

import java.io.Serializable;

public class BaseModel implements Serializable {
    private static final long serialVersionUID = 35595L;
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private String code;
    private String msg;

}
