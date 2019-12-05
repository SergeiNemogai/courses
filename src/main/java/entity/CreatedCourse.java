package entity;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class CreatedCourse {
    @NonNull
    Long userId;
    @NonNull
    Long courseId;
}