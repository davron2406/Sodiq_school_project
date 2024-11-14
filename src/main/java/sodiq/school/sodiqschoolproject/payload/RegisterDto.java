package sodiq.school.sodiqschoolproject.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterDto {
    private String lastName;
    private String firstName;
    private String password;
    private String phoneNumber;
}
