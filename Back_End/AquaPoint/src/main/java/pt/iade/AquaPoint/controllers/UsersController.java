package pt.iade.AquaPoint.controllers; 

import java.util.List;

import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 
import org.springframework.http.MediaType; 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RestController;

import pt.iade.AquaPoint.repository.UserRepository;
import pt.iade.AquaPoint.models.User;

import org.springframework.web.bind.annotation.PathVariable; 

@RestController 
@RequestMapping(path="/api/java/users/") 

public class UsersController {   
    private Logger logger = LoggerFactory.getLogger(AquaPointsController.class); 
    private final UserRepository userRepository;

    public UsersController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(path="/getAllUsers/", produces = MediaType.APPLICATION_JSON_VALUE) 
    public List<User> getAllUsers() { 
        return userRepository.findAll();
    }
    
    @RequestMapping(path="/getUserDataByName/{name}", produces = MediaType.APPLICATION_JSON_VALUE) 
    public User getUserDataByName(String name) { 
        return userRepository.findByName(name);
    }
    
    // in development...
    @RequestMapping(path="/createNewUser/", produces = MediaType.APPLICATION_JSON_VALUE) 
    public Boolean getAquaPoints() { 
        return true;
    }
} 