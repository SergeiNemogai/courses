package entity;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class Status {
    @NonNull
    String status;

    @Override
    public String toString() {
        return status;
    }
}