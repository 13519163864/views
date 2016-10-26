package entry;

/**
 * Created by Administrator on 2016/10/19.
 */

public class ViewInfo {
    String summary;
    String icon;
    String stamp;
    String title;
    String nid;
    String link;
    int type;


    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSummary() {
        return summary;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIcon() {
        return icon;
    }

    public void setStamp(String stamp) {
        this.stamp = stamp;
    }

    public String getStamp() {
        return stamp;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getNid() {
        return nid;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public ViewInfo(String summary,
                    String icon,
                    String stamp,
                    String title,
                    String nid,
                    String link,
                    int type) {
        this.summary = summary;
        this.stamp = stamp;
        this.icon = icon;
        this.title = title;
        this.nid = nid;
        this.link = link;
        this.type = type;


    }
}
