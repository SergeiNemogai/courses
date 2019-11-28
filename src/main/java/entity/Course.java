package entity;

import java.sql.Date;

public class Course {
    private int ID;
    private String name;
    private Date createdAt;
    private Date startDateTime; // time doesn't exist!
    private Date endDateTime; // time doesn't exist!
    private String status;

    public Course(int ID, String name, Date createdAt, Date startDateTime, Date endDateTime, String status) {
        this.ID = ID;
        this.name = name;
        this.createdAt = createdAt;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.status = status;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Date getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(Date endDateTime) {
        this.endDateTime = endDateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Course{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", createdAt=" + createdAt +
                ", startDateTime=" + startDateTime +
                ", endDateTime=" + endDateTime +
                ", status='" + status + '\'' +
                '}';
    }
}