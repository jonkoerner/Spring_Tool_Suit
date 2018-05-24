package com.jonathan.auth.services;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.jonathan.auth.models.User;
import com.jonathan.auth.repositories.CourseRepository;
import com.jonathan.auth.repositories.UserRepository;
@Service
public class UserService {
    private UserRepository userRepository;
    private CourseRepository courseRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    public UserService(UserRepository userRepository, CourseRepository courseRepository, BCryptPasswordEncoder bCryptPasswordEncoder)     {
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    
    
    // 1
    public void saveWithUserCourse(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setCourses(courseRepository.findByName("Course_USER"));
        userRepository.save(user);
    }
     
     // 2 
    public void saveUserWithAdminCourse(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setCourses(courseRepository.findByName("Course_ADMIN"));
        userRepository.save(user);
    }    
    
    // 3
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
