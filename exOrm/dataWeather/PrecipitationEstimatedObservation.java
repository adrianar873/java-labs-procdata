package exOrm.dataWeather;

public class PrecipitationEstimatedObservation {
    private String discrepancy;
    private long estimatedWaterDepth;

    public String getDiscrepancy() { return discrepancy; }
    public void setDiscrepancy(String value) { this.discrepancy = value; }

    public long getEstimatedWaterDepth() { return estimatedWaterDepth; }
    public void setEstimatedWaterDepth(long value) { this.estimatedWaterDepth = value; }
    @Override
    public String toString() {
        return "PrecipitationEstimatedObservation{" +
                "discrepancy='" + discrepancy + '\'' +
                ", estimatedWaterDepth=" + estimatedWaterDepth +
                '}';
    }
    }
