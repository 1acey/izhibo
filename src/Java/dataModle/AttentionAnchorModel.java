package dataModle;

import java.io.Serializable;
import java.util.List;

//关注的主播
public class AttentionAnchorModel implements Serializable {
    private String anthorRoomNummber;//主播的房间号
    private String anthorName;//主播的名字
    private String anthorPic;//主播的头像的url

    public String getAnthorRoomNummber() {
        return anthorRoomNummber;
    }

    public void setAnthorRoomNummber(String anthorRoomNummber) {
        this.anthorRoomNummber = anthorRoomNummber;
    }

    public String getAnthorName() {
        return anthorName;
    }

    public void setAnthorName(String anthorName) {
        this.anthorName = anthorName;
    }

    public String getAnthorPic() {
        return anthorPic;
    }

    public void setAnthorPic(String anthorPic) {
        this.anthorPic = anthorPic;
    }


}
