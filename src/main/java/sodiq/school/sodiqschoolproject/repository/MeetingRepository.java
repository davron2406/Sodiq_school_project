package sodiq.school.sodiqschoolproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sodiq.school.sodiqschoolproject.entity.Meeting;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface MeetingRepository extends JpaRepository<Meeting, UUID> {


    @Query(nativeQuery = true, value = "select * from meetings where teacher_id = :teacherId and date > :date order by date")
    List<Meeting> getMeetingsByTeacherIdAndDate(@Param("teacherId") UUID teacherId, @Param("date") Date date);

    @Query(nativeQuery = true, value = "select * from meetings where student_id = :studentId and date > :date order by date")
    List<Meeting> getMeetingsByStudentIdAndDate(@Param("studentId") UUID studentId, @Param("date") Date date);

    List<Meeting> findByTeacherIdAndDate(UUID teacherId, LocalDate date);

}
