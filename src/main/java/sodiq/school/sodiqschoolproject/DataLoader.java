package sodiq.school.sodiqschoolproject;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import sodiq.school.sodiqschoolproject.entity.Role;
import sodiq.school.sodiqschoolproject.entity.User;
import sodiq.school.sodiqschoolproject.entity.enums.Permission;
import sodiq.school.sodiqschoolproject.repository.RoleRepository;
import sodiq.school.sodiqschoolproject.repository.UserRepository;

import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;



    @Override
    public void run(String... args) throws Exception {
        Permission[] permissions = Permission.values();
        Role admin = roleRepository.save(new Role("admin", Arrays.asList(permissions)));
        Role user = roleRepository.save(new Role("user", Arrays.asList(Permission.GET_MY_MEETINGS,Permission.BOOK_MEETING)));
        Role teacher = roleRepository.save(new Role("teacher", Arrays.asList(Permission.GET_MY_MEETINGS)));
        userRepository.save(new User("Admin", "admin","944288899", passwordEncoder.encode( "admin123"),admin,1));
        userRepository.save(new User("Davron", "Saydullayev","1111", passwordEncoder.encode( "1111"),roleRepository.findByName("teacher").get(),1));
        userRepository.save(new User("Rustam", "Saliboyev","2222", passwordEncoder.encode( "2222"),roleRepository.findByName("teacher").get(),1));
        userRepository.save(new User("Javohir", "O'rolov","3333", passwordEncoder.encode( "3333"),roleRepository.findByName("teacher").get(),1));
        userRepository.save(new User("DavronM", "Mavlonov","4444", passwordEncoder.encode( "4444"),roleRepository.findByName("teacher").get(),1));
        userRepository.save(new User("Asilbek", "Yallaboyev","5555", passwordEncoder.encode( "5555"),roleRepository.findByName("teacher").get(),1));
        userRepository.save(new User("user2", "user2","957301425", passwordEncoder.encode( "user2"),roleRepository.findByName("user").get(),1));

        for (int i = 6; i < 100; i++) {
            userRepository.save(new User("user" + i, "user" + i, "" + i + i + i + i,  passwordEncoder.encode( "user" + i),roleRepository.findByName("user").get(),1));
        }

//
//        Sidebar sideBarMenu = new Sidebar("Questions","questions","quiz");
//        Sidebar sideBarMenu1 = new Sidebar("Practice Tests","practiceTests","assignment");
//        sidebarRepository.save(sideBarMenu);
//        sidebarRepository.save(sideBarMenu1);
    }
}
