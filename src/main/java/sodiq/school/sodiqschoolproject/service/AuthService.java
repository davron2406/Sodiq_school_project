package sodiq.school.sodiqschoolproject.service;


import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sodiq.school.sodiqschoolproject.entity.User;
import sodiq.school.sodiqschoolproject.payload.ApiResponse;
import sodiq.school.sodiqschoolproject.payload.RegisterDto;
import sodiq.school.sodiqschoolproject.repository.RoleRepository;
import sodiq.school.sodiqschoolproject.repository.UserRepository;

import java.util.UUID;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RoleRepository roleRepository;



    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
            return userRepository.findByPhoneNumber(phoneNumber).orElseThrow();
    }

    public ApiResponse registerUser(RegisterDto registerDto) {
        if(userRepository.existsByPhoneNumber(registerDto.getPhoneNumber()))
            return new ApiResponse("Email already exist",false,null );
        User user = new User(
                registerDto.getFirstName(),
                registerDto.getLastName(),
                registerDto.getPhoneNumber(),
                passwordEncoder.encode(registerDto.getPassword()),
                roleRepository.findByName("user").get(),
                1);

        userRepository.save(user);
        return new ApiResponse("User successfully registered", true,null);
    }

}
