import java.text.DateFormat;
import java.util.Date;

public class Event {

    public Event(Date date, DateFormat dateFormat) {
        this.date = date;
        this.dateFormat = dateFormat;
//        this.id
    }

    private int id = (int) (Math.random() * 100);

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private String msg;
    private Date date;
    private DateFormat dateFormat;

    public String toString() {
        return "id: " + this.id + ", Date: " + this.dateFormat.format(this.date) + ", Messege: " + this.msg;
    }
}
