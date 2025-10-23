package pt.iade.AquaPoint.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "aqua_points")
public class AquaPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    private String point_name;
    private int point_type;
    private int local_id;
    private int latitude;
    private int longitude;

    // construtor para o jpa
    public AquaPoint() {}
    
    // construtor
    public AquaPoint
    (
        int id, 
        String name, 
        int type, 
        int local_id, 
        int latitude, 
        int longitude
    ) 
    {
        point_name = name;
        point_type = type;
        this.local_id = local_id;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return point_name;
    }

    public int getType() {
        return point_type;
    }

    public int getLocalId() {
        return local_id;
    }

    public int getLatitude() {
        return latitude;
    }

    public int getLongitude() {
        return longitude;
    }
}
