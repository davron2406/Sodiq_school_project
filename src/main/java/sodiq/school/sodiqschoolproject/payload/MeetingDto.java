package sodiq.school.sodiqschoolproject.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MeetingDto {
    private LocalDate date;
    private UUID teacherId;
    private String time;
}
