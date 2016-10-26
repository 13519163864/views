package entry;

/**
 * Created by Administrator on 2016/10/24.
 */

public class Source {
    String summary;
    String icon;
    String stamp;
    String title;
    String nid;
    String link;
    int type;

    public Source(String summary, String stamp, String title, String icon, String nid, String link, int type) {
        this.summary = summary;
        this.stamp = stamp;
        this.title = title;
        this.icon = icon;
        this.nid = nid;
        this.link = link;
        this.type = type;
    }

    public String getSummary() {
        return summary;
    }

    public String getIcon() {
        return icon;
    }

    public String getStamp() {
        return stamp;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getNid() {
        return nid;
    }

    public int getType() {
        return type;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setStamp(String stamp) {
        this.stamp = stamp;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Source{" +
                "summary='" + summary + '\'' +
                ", icon='" + icon + '\'' +
                ", stamp='" + stamp + '\'' +
                ", title='" + title + '\'' +
                ", nid='" + nid + '\'' +
                ", link='" + link + '\'' +
                ", type=" + type +
                '}';
    }
}
