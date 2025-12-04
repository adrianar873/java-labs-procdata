package exOrm.dataWeather;

public class SkyCondition {
    private CeilingHeight ceilingHeight;
    private String cavok;

    public CeilingHeight getCeilingHeight() { return ceilingHeight; }
    public void setCeilingHeight(CeilingHeight value) { this.ceilingHeight = value; }

    public String getCavok() { return cavok; }
    public void setCavok(String value) { this.cavok = value; }
    @Override
    public String toString() {
        return "SkyCondition{" +
                "ceilingHeight=" + ceilingHeight +
                ", cavok='" + cavok + '\'' +
                '}';
    }
    }
