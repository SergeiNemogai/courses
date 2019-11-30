package entity;

public class CreatedCourse {
    private int userId;
    private int courseId;

    public CreatedCourse(int userId, int courseId) {
        this.userId = userId;
        this.courseId = courseId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "CreatedCourse{" +
                "userId=" + userId +
                ", courseId=" + courseId +
                '}';
    }
}