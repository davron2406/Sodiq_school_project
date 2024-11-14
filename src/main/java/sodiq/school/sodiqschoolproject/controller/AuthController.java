package sodiq.school.sodiqschoolproject.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sodiq.school.sodiqschoolproject.entity.User;
import sodiq.school.sodiqschoolproject.payload.ApiResponse;
import sodiq.school.sodiqschoolproject.payload.LoginDto;
import sodiq.school.sodiqschoolproject.payload.RegisterDto;
import sodiq.school.sodiqschoolproject.security.JwtProvider;
import sodiq.school.sodiqschoolproject.service.AuthService;


@Controller
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/login")
    public HttpEntity<?> login(@RequestBody LoginDto loginDto){
        try{
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getPhoneNumber(), loginDto.getPassword()));
             User user = (User) authenticate.getPrincipal();
            String token = jwtProvider.generateToken(user.getPhoneNumber());
            System.out.println(token);
            return ResponseEntity.ok(new ApiResponse("Successfully logged in", true, token));
        }catch (Exception e){
            return ResponseEntity.ok(new ApiResponse("Password or email is incorrect",false,null));
        }
    }

    @PostMapping("/register")
    public HttpEntity<?> registerUser(@RequestBody RegisterDto registerDto){
        ApiResponse apiResponse = authService.registerUser(registerDto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

//    @PostMapping("/verifyEmail")
//    public  HttpEntity<?> verifyEmail(@RequestParam String emailCode, @RequestParam String email){
//        ApiResponse apiResponse = authService.verifyEmail(email, emailCode);
//        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
//    }
}
