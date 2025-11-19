package pt.iade.AquaPoint.models;

public class RemoveAquaPoint {
    private int userId;
    private int pointId;

    public RemoveAquaPoint() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) { 
        this.userId = userId;
    }

    public int getPointId() {
        return pointId;
    }

    public void setPointId(int pointId) {
        this.pointId = pointId;
    }
}
