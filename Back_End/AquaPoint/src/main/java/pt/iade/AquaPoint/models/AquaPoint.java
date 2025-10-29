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

    public void setPoint_name(String point_name) {
        this.point_name = point_name;
    }

    public void setPoint_type(int point_type) {
        this.point_type = point_type;
    }

    public void setLocal_id(int local_id) {
        this.local_id = local_id;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }
}
