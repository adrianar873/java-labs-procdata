package exOrm.dataWeather;

public class AirTemperature {
    private double value;
    private String quality;

    public double getValue() { return value; }
    public void setValue(double value) { this.value = value; }

    public String getQuality() { return quality; }
    public void setQuality(String value) { this.quality = value; }

    @Override
    public String toString() {
        return "AirTemperature{" +
                "value=" + value +
                ", quality='" + quality + '\'' +
                '}';
    }
    }
