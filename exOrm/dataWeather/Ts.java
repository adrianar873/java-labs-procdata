package exOrm.dataWeather;

import java.util.Date;

public class Ts {
    private Date date;

    public Date getDate() { return date; }
    public void setDate(Date value) { this.date = value; }
    @Override
    public String toString() {
        return "Ts{" +
                "date=" + date +
                '}';
    }
    }
