package entity;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class CreatedCourse {
    @NonNull
    private int userId;
    @NonNull
    private int courseId;
}