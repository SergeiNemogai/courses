package entity;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class Study {
    @NonNull
    int id;
    @NonNull
    int courseId;
    @NonNull
    int userId;
}