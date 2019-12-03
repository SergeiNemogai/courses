package entity;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class User {
    int id;
    @NonNull
    String firstName;
    @NonNull
    String lastName;
    @NonNull
    String role;
}