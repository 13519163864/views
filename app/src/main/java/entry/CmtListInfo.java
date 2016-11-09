package entry;

/**
 * Created by Administrator on 2016/11/9.
 * 评论内容的信息实体类
 */

public class CmtListInfo {
    //用户名
    String uid;
    //评论内容
    String content;
    //时间戳
    String stamp;
    //评论id
    String cid;
    //用户头像
    String portrait;

    @Override
    public String toString() {
        return "CmtListInfo{" +
                "uid='" + uid + '\'' +
                ", content='" + content + '\'' +
                ", stamp='" + stamp + '\'' +
                ", cid='" + cid + '\'' +
                ", portrait='" + portrait + '\'' +
                '}';
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getStamp() {
        return stamp;
    }

    public void setStamp(String stamp) {
        this.stamp = stamp;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public CmtListInfo(String uid, String content, String stamp, String cid, String portrait) {

        this.uid = uid;
        this.content = content;
        this.stamp = stamp;
        this.cid = cid;
        this.portrait = portrait;
    }
}
