package pt.iade.AquaPoint.models;

public class AddUserInteraction {
    private int userId;
    private int pointId;
    private String comment;
    private int rating;

    public AddUserInteraction() {
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
