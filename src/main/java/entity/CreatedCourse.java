package entity;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class CreatedCourse {
    int userId;
    int courseId;
}