package entity;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class Study {
    int id;
    int courseId;
    int userId;
}