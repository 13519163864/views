package entry;

/**
 * Created by Administrator on 2016/11/4.
 */

public class LoginLog {
    String time;
    String address;
    int device;

    @Override
    public String toString() {
        return "LoginLog{" +
                "time='" + time + '\'' +
                ", address='" + address + '\'' +
                ", device='" + device + '\'' +
                '}';
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getDevice() {
        return device;
    }

    public void setDevice(int device) {
        this.device = device;
    }

    public LoginLog(String time, String address, int device) {

        this.time = time;
        this.address = address;
        this.device = device;
    }
}
