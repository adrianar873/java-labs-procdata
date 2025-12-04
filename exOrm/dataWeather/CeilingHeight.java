package exOrm.dataWeather;

public class CeilingHeight {
    private long value;
    private String quality;
    private String determination;

    public long getValue() { return value; }
    public void setValue(long value) { this.value = value; }

    public String getQuality() { return quality; }
    public void setQuality(String value) { this.quality = value; }

    public String getDetermination() { return determination; }
    public void setDetermination(String value) { this.determination = value; }
    @Override
    public String toString() {
        return "CeilingHeight{" +
                "value=" + value +
                ", quality='" + quality + '\'' +
                ", determination='" + determination + '\'' +
                '}';
    }
    }
