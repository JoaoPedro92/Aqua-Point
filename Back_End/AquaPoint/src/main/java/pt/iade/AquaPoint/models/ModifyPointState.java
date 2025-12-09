package pt.iade.AquaPoint.models;

public class ModifyPointState {
    private int point_id;
    private int state_id;

    public ModifyPointState() {
    }

    public int getPointId() {
        return point_id;
    }

    public void setPoint_id(int point_id) { 
        this.point_id = point_id;
    }

    public int getStateId() {
        return state_id;
    }

    public void setState_id(int state_id) {
        this.state_id = state_id;
    }
}
