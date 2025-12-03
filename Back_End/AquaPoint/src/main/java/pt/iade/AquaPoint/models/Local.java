package pt.iade.AquaPoint.models;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "local")
public class Local {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    private String local_name;
    private int zone_id;

    // construtor para o jpa
    public Local() {}

    public Local(int id, String local_name, int zone_id) {
        this.id = id;
        this.local_name = local_name;
        this.zone_id = zone_id;
    }

    public int getId() {
        return id;
    }

    public String getLocal_name() {
        return local_name;
    }

     public int getZone_id() {
        return zone_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLocal_name(String local_name) {
        this.local_name = local_name;
    }

    public void setZone_id(int zone_id) {
        this.zone_id = zone_id;
    }
}
