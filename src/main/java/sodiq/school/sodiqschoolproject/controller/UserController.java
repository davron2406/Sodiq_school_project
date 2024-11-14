package sodiq.school.sodiqschoolproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sodiq.school.sodiqschoolproject.service.UserService;

@Controller
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/getTeachers")
    @PreAuthorize(value = "hasAuthority('BOOK_MEETING')")
    public HttpEntity<?> getTeachers() {
        return ResponseEntity.ok(userService.getTeachers());
    }
}
