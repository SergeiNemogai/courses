package entity;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.sql.Date;
import java.sql.Timestamp;

@Value
@Builder(toBuilder = true)
public class Course {
    @NonNull
    private int id;
    @NonNull
    private String name;
    @NonNull
    private Date createdAt;
    @NonNull
    private Timestamp startDateTime;
    @NonNull
    private Timestamp endDateTime;
    @NonNull
    private String status;
}