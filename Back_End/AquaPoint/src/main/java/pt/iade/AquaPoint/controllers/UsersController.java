package pt.iade.AquaPoint.controllers; 

import java.util.List;

import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 
import org.springframework.http.MediaType; 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RestController;

import pt.iade.AquaPoint.repository.UserRepository;
import pt.iade.AquaPoint.models.User;

import java.sql.Date;

import org.springframework.web.bind.annotation.PathVariable; 

@RestController 
@RequestMapping(path="/api/java/users/") 

public class UsersController {   
    private Logger logger = LoggerFactory.getLogger(UsersController.class); 
    private final UserRepository userRepository;

    public UsersController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(path="/getAllUsers/", produces = MediaType.APPLICATION_JSON_VALUE) 
    public List<User> getAllUsers() { 
        return userRepository.findAll();
    }
    
    @PostMapping(path="/getUserDataByName/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE) 
    public User getUserDataByName(/*String name*/) { 
        return userRepository.findByName("João Silva"); // hardcode "João Silva" para testar, deve ir do android studio
    }
    
    // in development...
    @PostMapping(path="/createNewUser/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE) 
    public User createNewUser(User userData) { 
        userData.setJoined(new Date(System.currentTimeMillis()));

        return userRepository.save(userData); // dados hardcode para testar, devem vir do android studio
    }

    @PostMapping(path="/editUserData/", produces = MediaType.APPLICATION_JSON_VALUE) 
    public User editUserData(User userData) { 
        return userRepository.save(userData); // dados hardcode para testar, devem vir do android studio
    }
} 