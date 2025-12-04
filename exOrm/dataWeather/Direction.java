package exOrm.dataWeather;

public class Direction {
    private long angle;
    private String quality;

    public long getAngle() { return angle; }
    public void setAngle(long value) { this.angle = value; }

    public String getQuality() { return quality; }
    public void setQuality(String value) { this.quality = value; }

    @Override
    public String toString() {
        return "Direction{" +
                "angle=" + angle +
                ", quality='" + quality + '\'' +
                '}';
    }
}
