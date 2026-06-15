package Meko.MekoApp.services;

import Meko.MekoApp.entities.User;
import Meko.MekoApp.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findUserByUsername(String username){
        return userRepository.findByUsername(username).orElse(null);
    }
    public User save(User user){
        return userRepository.save(user);
    }


}
