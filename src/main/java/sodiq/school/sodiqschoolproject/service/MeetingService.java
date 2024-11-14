package sodiq.school.sodiqschoolproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import sodiq.school.sodiqschoolproject.entity.Meeting;
import sodiq.school.sodiqschoolproject.entity.User;
import sodiq.school.sodiqschoolproject.payload.ApiResponse;
import sodiq.school.sodiqschoolproject.payload.MeetingDto;
import sodiq.school.sodiqschoolproject.repository.MeetingRepository;
import sodiq.school.sodiqschoolproject.repository.UserRepository;
import sodiq.school.sodiqschoolproject.utils.AppConstants;

import java.security.Security;
import java.time.LocalDate;
import java.util.*;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class MeetingService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    MeetingRepository meetingRepository;

    public ApiResponse createMeeting(MeetingDto meetingDto) {
        Meeting meeting = new Meeting();
        meeting.setDate(meetingDto.getDate());
        meeting.setTime(meetingDto.getTime());
        meeting.setTeacher(userRepository.findById(meetingDto.getTeacherId()).get());
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        meeting.setStudent((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        try {
            meetingRepository.save(meeting);
        } catch (Exception e){
            return new ApiResponse("You have already booked meeting for this day", false, null);
        }

        return new ApiResponse("Meeting Successfully booked", true, null);
    }

    public ApiResponse getMeetings() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(user == null) {
            return new ApiResponse("Please Log in ", false, null);
        }
        if(user.getRole().getName().equals("teacher")){
            return new ApiResponse("Success", true, meetingRepository.getMeetingsByTeacherIdAndDate(user.getId(), new Date()));
        }
        if(user.getRole().getName().equals("user")){
            return new ApiResponse("Success", true, meetingRepository.getMeetingsByStudentIdAndDate(user.getId(), new Date()));
        }
        return new ApiResponse("Something went wrong", false, null);
    }

    public ApiResponse getTeacherFreeTime(UUID teacherId, LocalDate date){
        List<Meeting> byTeacherIdAndDate = meetingRepository.findByTeacherIdAndDate(teacherId, date);
        List<String> usersFreeTimes = new ArrayList<>(getUsersFreeTimes(teacherId));
        if(byTeacherIdAndDate.isEmpty()){
            return new ApiResponse("Success", true, usersFreeTimes);
        }

        for(Meeting meeting : byTeacherIdAndDate){
            assert usersFreeTimes != null;
            usersFreeTimes.remove(meeting.getTime());
        }

        return new ApiResponse("Success", true, usersFreeTimes);
    }

    private List<String> getUsersFreeTimes(UUID teacherId){
        String firstName = userRepository.findById(teacherId).get().getFirstName();
        if(firstName.equals("Davron")){
            return Arrays.asList(AppConstants.DavronFreeTimes);
        }
        else if(firstName.equals("Rustam")){
            return Arrays.asList(AppConstants.RustamFreeTimes);
        }
        else if(firstName.equals("Javohir")){
            return Arrays.asList(AppConstants.JavohirFreeTimes);
        }
        else if(firstName.equals("DavronM")){
            return Arrays.asList(AppConstants.DavronMFreeTimes);
        }

        return null;
    }
}
