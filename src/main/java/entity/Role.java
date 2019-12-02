package entity;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class Role {
    @NonNull
    private String role;

    @Override
    public String toString() {
        return role;
    }
}