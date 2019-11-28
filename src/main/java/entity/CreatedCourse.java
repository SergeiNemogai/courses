package entity;

public class CreatedCourse {
    private int userID;
    private int courseID;

    public CreatedCourse(int userID, int courseID) {
        this.userID = userID;
        this.courseID = courseID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    @Override
    public String toString() {
        return "CreatedCourse{" +
                "userID=" + userID +
                ", courseID=" + courseID +
                '}';
    }
}