package entry;

/**
 * Created by Administrator on 2016/10/31.
 */

public class LeftFragmentInfo {
    int Icon;
    String Name;
    String Eng;

    public LeftFragmentInfo(int icon, String name, String eng) {
        Icon = icon;
        Name = name;
        Eng = eng;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setIcon(int icon) {
        Icon = icon;
    }

    public void setEng(String eng) {
        Eng = eng;
    }

    public int getIcon() {

        return Icon;
    }

    public String getName() {
        return Name;
    }

    @Override
    public String toString() {
        return "LeftFragmentInfo{" +
                "Icon=" + Icon +
                ", Name='" + Name + '\'' +
                ", Eng='" + Eng + '\'' +
                '}';
    }

    public String getEng() {
        return Eng;
    }
}
