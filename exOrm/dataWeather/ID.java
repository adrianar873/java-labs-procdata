package exOrm.dataWeather;

import org.bson.types.ObjectId;

public class ID {
    private ObjectId oid;

    public ObjectId getOID() { return oid; }
    public void setOID(ObjectId value) { this.oid = value; }

    @Override
    public String toString() {
        return "ID{oid=" + (oid != null ? oid.toHexString() : null) + "}";
    }
}
