package pt.iade.AquaPoint.models;

public class AquaPointModel {
    private int id;
    private String name;
    private int type;
    private int local_id;
    private int latitude;
    private int longitude;

    // construtor
    public AquaPointModel
    (
        int id, 
        String name, 
        int type, 
        int local_id, 
        int latitude, 
        int longitude
    ) 
    {
        this.name = name;
        this.type = type;
        this.local_id = local_id;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int GetID() {
        return id;
    }

    public String GetName() {
        return name;
    }

    public int GetType() {
        return type;
    }

    public int GetLocalID() {
        return local_id;
    }

    public int GetLatitude() {
        return latitude;
    }

    public int GetLongitude() {
        return longitude;
    }
}
