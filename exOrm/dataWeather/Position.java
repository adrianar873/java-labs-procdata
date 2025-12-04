package exOrm.dataWeather;

import java.util.List;

public class Position {
    private String type;
    private List<Double> coordinates;

    public String getType() { return type; }
    public void setType(String value) { this.type = value; }

    public List<Double> getCoordinates() { return coordinates; }
    public void setCoordinates(List<Double> value) { this.coordinates = value; }
    public String toString() {
        return "Position{" +
                "type='" + type + '\'' +
                ", coordinates=" + coordinates +
                '}';
    }
    }
