package entry;

import java.util.ArrayList;


/**
 * Created by Administrator on 2016/10/24.
 */

public class Data {
    String message;
    int status;
    ArrayList<Source> data;

    @Override
    public String toString() {
        return "Data{" +
                "message='" + message + '\'' +
                ", status=" + status +
                ", data=" + data +
                '}';
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setData(ArrayList<Source> data) {
        this.data = data;
    }

    public ArrayList<Source> getData() {

        return data;
    }

    public String getMessage() {

        return message;
    }

    public int getStatus() {
        return status;
    }


    public Data(String message, int status, ArrayList<Source> data) {

        this.message = message;
        this.status = status;
        this.data = data;
    }
}
