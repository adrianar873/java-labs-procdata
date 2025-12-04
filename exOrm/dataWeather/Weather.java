package exOrm.dataWeather;

import java.util.Date;
import java.util.List;
import org.bson.types.ObjectId;

public class Weather {
    private ObjectId id;
    private String st;
    private Date ts;
    private Position position;
    private long elevation;
    private String callLetters;
    private String qualityControlProcess;
    private String dataSource;
    private String type;
    private AirTemperature airTemperature;
    private AirTemperature dewPoint;
    private AirTemperature pressure;
    private Wind wind;
    private Visibility visibility;
    private SkyCondition skyCondition;
    private List<String> sections;
    private PrecipitationEstimatedObservation precipitationEstimatedObservation;
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Weather {\n");
        sb.append("  id: ").append(id).append(",\n");
        sb.append("  st: ").append(st).append(",\n");
        sb.append("  ts: ").append(ts).append(",\n");
        sb.append("  position: ").append(position).append(",\n");
        sb.append("  elevation: ").append(elevation).append(",\n");
        sb.append("  callLetters: ").append(callLetters).append(",\n");
        sb.append("  qualityControlProcess: ").append(qualityControlProcess).append(",\n");
        sb.append("  dataSource: ").append(dataSource).append(",\n");
        sb.append("  type: ").append(type).append(",\n");
        sb.append("  airTemperature: ").append(airTemperature).append(",\n");
        sb.append("  dewPoint: ").append(dewPoint).append(",\n");
        sb.append("  pressure: ").append(pressure).append(",\n");
        sb.append("  wind: ").append(wind).append(",\n");
        sb.append("  visibility: ").append(visibility).append(",\n");
        sb.append("  skyCondition: ").append(skyCondition).append(",\n");
        sb.append("  sections: ").append(sections).append(",\n");
        sb.append("  precipitationEstimatedObservation: ").append(precipitationEstimatedObservation).append("\n");
        sb.append("}");
        return sb.toString();
    }

    public ObjectId getId() { return id; }
    public void setId(ObjectId value) { this.id = value; }

    public String getSt() { return st; }
    public void setSt(String value) { this.st = value; }

    public Date getTs() { return ts; }
    public void setTs(Date value) { this.ts = value; }

    public Position getPosition() { return position; }
    public void setPosition(Position value) { this.position = value; }

    public long getElevation() { return elevation; }
    public void setElevation(long value) { this.elevation = value; }

    public String getCallLetters() { return callLetters; }
    public void setCallLetters(String value) { this.callLetters = value; }

    public String getQualityControlProcess() { return qualityControlProcess; }
    public void setQualityControlProcess(String value) { this.qualityControlProcess = value; }

    public String getDataSource() { return dataSource; }
    public void setDataSource(String value) { this.dataSource = value; }

    public String getType() { return type; }
    public void setType(String value) { this.type = value; }

    public AirTemperature getAirTemperature() { return airTemperature; }
    public void setAirTemperature(AirTemperature value) { this.airTemperature = value; }

    public AirTemperature getDewPoint() { return dewPoint; }
    public void setDewPoint(AirTemperature value) { this.dewPoint = value; }

    public AirTemperature getPressure() { return pressure; }
    public void setPressure(AirTemperature value) { this.pressure = value; }

    public Wind getWind() { return wind; }
    public void setWind(Wind value) { this.wind = value; }

    public Visibility getVisibility() { return visibility; }
    public void setVisibility(Visibility value) { this.visibility = value; }

    public SkyCondition getSkyCondition() { return skyCondition; }
    public void setSkyCondition(SkyCondition value) { this.skyCondition = value; }

    public List<String> getSections() { return sections; }
    public void setSections(List<String> value) { this.sections = value; }

    public PrecipitationEstimatedObservation getPrecipitationEstimatedObservation() { return precipitationEstimatedObservation; }
    public void setPrecipitationEstimatedObservation(PrecipitationEstimatedObservation value) { this.precipitationEstimatedObservation = value; }
}
