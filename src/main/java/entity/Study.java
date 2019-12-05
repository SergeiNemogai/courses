package entity;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class Study {
    @NonNull
    Long id;
    @NonNull
    Long courseId;
    @NonNull
    Long userId;
}