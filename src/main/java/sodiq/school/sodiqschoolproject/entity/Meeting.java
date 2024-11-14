package sodiq.school.sodiqschoolproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(
        name = "meetings",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"date", "student_id"})}
)
public class Meeting extends AbstractEntity {

    @ManyToOne
    private User teacher;

    @Column(name = "date")
    private LocalDate date;
    private String time;

    @JoinColumn(name = "student_id")
    @ManyToOne
    private User student;

}
