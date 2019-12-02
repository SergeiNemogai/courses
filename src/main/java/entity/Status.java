package entity;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class Status {
    private String status;

    @Override
    public String toString() {
        return status;
    }
}