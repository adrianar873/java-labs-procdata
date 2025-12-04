package exOrm.dataWeather;

public class Variability {
    private String value;
    private String quality;

    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }

    public String getQuality() { return quality; }
    public void setQuality(String value) { this.quality = value; }
    @Override
    public String toString() {
        return "Variability{" +
                "value='" + value + '\'' +
                ", quality='" + quality + '\'' +
                '}';
    }
    }
