package exOrm.dataWeather;

public class Visibility {
    private AirTemperature distance;
    private Variability variability;

    public AirTemperature getDistance() { return distance; }
    public void setDistance(AirTemperature value) { this.distance = value; }

    public Variability getVariability() { return variability; }
    public void setVariability(Variability value) { this.variability = value; }
    @Override
    public String toString() {
        return "Visibility{" +
                "distance=" + distance +
                ", variability=" + variability +
                '}';
    }
    }
