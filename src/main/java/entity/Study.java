package entity;

public class Study {
    private int ID;
    private int courseID;
    private int userID;

    public Study(int ID, int courseID, int userID) {
        this.ID = ID;
        this.courseID = courseID;
        this.userID = userID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    @Override
    public String toString() {
        return "Study{" +
                "ID=" + ID +
                ", courseID=" + courseID +
                ", userID=" + userID +
                '}';
    }
}