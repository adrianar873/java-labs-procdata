package exOrm.dataWeather;

public class Wind {
    private Direction direction;
    private String type;
    private Speed speed;

    public Direction getDirection() { return direction; }
    public void setDirection(Direction value) { this.direction = value; }

    public String getType() { return type; }
    public void setType(String value) { this.type = value; }

    public Speed getSpeed() { return speed; }
    public void setSpeed(Speed value) { this.speed = value; }
    @Override
    public String toString() {
        return "Wind{" +
                "direction=" + direction +
                ", type='" + type + '\'' +
                ", speed=" + speed +
                '}';
    }
    }
