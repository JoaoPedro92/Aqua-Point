package pt.iade.AquaPoint.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "points_state")
public class PointState {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    private int state_id;

    @OneToOne
    @JoinColumn(name = "point_id")
    private AquaPoint aquaPoint; 

    // construtor para o jpa
    public PointState() {}
    
    // construtor
    public PointState
    (
        int id, 
        int stateId 
    ) 
    {
        this.id = id;
        this.state_id = stateId;
    }

    public int getId() {
        return id;
    }

    public int getStateId() {
        return state_id;
    }
}
