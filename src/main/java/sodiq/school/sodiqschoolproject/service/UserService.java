package sodiq.school.sodiqschoolproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sodiq.school.sodiqschoolproject.entity.User;
import sodiq.school.sodiqschoolproject.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> getTeachers() {
        return userRepository.findByRoleName("teacher");

    }
}
