package entity;

import java.sql.Date;
import java.sql.Timestamp;

public class Course {
    private int id;
    private String name;
    private Date createdAt;
    private Timestamp startDateTime; // time doesn't exist!
    private Timestamp endDateTime; // time doesn't exist!
    private String status;

    public Course(int id, String name, Date createdAt, Timestamp startDateTime, Timestamp endDateTime, String status) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Timestamp getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Timestamp startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Timestamp getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(Timestamp endDateTime) {
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
                "ID=" + id +
                ", name='" + name + '\'' +
                ", createdAt=" + createdAt +
                ", startDateTime=" + startDateTime +
                ", endDateTime=" + endDateTime +
                ", status='" + status + '\'' +
                '}';
    }
}