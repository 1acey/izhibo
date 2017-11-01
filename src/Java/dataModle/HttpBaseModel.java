package dataModle;

import java.io.Serializable;

public class HttpBaseModel extends BaseModel {

    public UserInfoModel getData() {
        return data;
    }

    public void setData(UserInfoModel data) {
        this.data = data;
    }


    private UserInfoModel data;
}
