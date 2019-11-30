package entity;

public class Study {
    private int id;
    private int courseId;
    private int userId;

    public Study(int id, int courseId, int userId) {
        this.id = id;
        this.courseId = courseId;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Study{" +
                "id=" + id +
                ", courseId=" + courseId +
                ", userId=" + userId +
                '}';
    }
}