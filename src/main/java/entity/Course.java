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
    int id;
    @NonNull
    String name;
    @NonNull
    Date createdAt;
    @NonNull
    Timestamp startDateTime;
    @NonNull
    Timestamp endDateTime;
    @NonNull
    String status;
}