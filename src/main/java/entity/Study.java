package entity;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class Study {
    @NonNull
    private int id;
    @NonNull
    private int courseId;
    @NonNull
    private int userId;
}