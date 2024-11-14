package sodiq.school.sodiqschoolproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sodiq.school.sodiqschoolproject.entity.Meeting;
import sodiq.school.sodiqschoolproject.payload.ApiResponse;
import sodiq.school.sodiqschoolproject.payload.MeetingDto;
import sodiq.school.sodiqschoolproject.service.MeetingService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;

@Controller
@RequestMapping("/api/meeting")
public class MeetingController {

    @Autowired
    MeetingService meetingService;

    @PostMapping
    @PreAuthorize(value = "hasAuthority('BOOK_MEETING')")
    public HttpEntity<?> createMeeting(@RequestBody MeetingDto meetingDto) {
        ApiResponse meeting = meetingService.createMeeting(meetingDto);

        return ResponseEntity.ok(meeting);
    }

    @GetMapping("/getMeetings")
    @PreAuthorize(value = "hasAuthority('GET_MY_MEETINGS')")
    public HttpEntity<?> getMeetings() {
        ApiResponse teacherMeetings = meetingService.getMeetings();
        return ResponseEntity.ok(teacherMeetings);
    }


    @GetMapping("/getTeacherFreeTime")
    @PreAuthorize(value = "hasAuthority('BOOK_MEETING')")
    public HttpEntity<?> getFreeTime(@RequestParam UUID teacherId, @RequestParam String date) {

        LocalDate date1 = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        ApiResponse teacherFreeTime = meetingService.getTeacherFreeTime(teacherId, date1);
        return ResponseEntity.ok(teacherFreeTime);
    }
}
