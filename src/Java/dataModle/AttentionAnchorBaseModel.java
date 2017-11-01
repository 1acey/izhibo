package dataModle;

import java.util.ArrayList;
import java.util.List;

//所关注的主播的数据
public class AttentionAnchorBaseModel extends BaseModel{
    public List<AttentionAnchorModel> getData() {
        return data;
    }

    public void setData(List<AttentionAnchorModel> data) {
        this.data = data;
    }

    private List<AttentionAnchorModel> data =new ArrayList<AttentionAnchorModel>();
}
