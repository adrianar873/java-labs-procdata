package exOrm.dataWeather;

public class Speed {
    private double rate;
    private String quality;

    public double getRate() { return rate; }
    public void setRate(double value) { this.rate = value; }

    public String getQuality() { return quality; }
    public void setQuality(String value) { this.quality = value; }
    @Override
    public String toString() {
        return "Speed{" +
                "rate=" + rate +
                ", quality='" + quality + '\'' +
                '}';
    }
}
