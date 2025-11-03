package pt.iade.AquaPoint.controllers; 

import java.util.List;

import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 
import org.springframework.http.MediaType; 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import pt.iade.AquaPoint.repository.UserInteractionsRepository;
import pt.iade.AquaPoint.repository.UserRepository;
import pt.iade.AquaPoint.models.AquaPoint;
import pt.iade.AquaPoint.models.User;
import pt.iade.AquaPoint.models.UserInteraction;

import java.sql.Date;

import org.springframework.web.bind.annotation.PathVariable; 

@RestController 
@RequestMapping(path="/api/java/usersInteractions/") 

public class UsersInteractionsController {   
    private Logger logger = LoggerFactory.getLogger(UsersController.class); 
    private final UserInteractionsRepository userInteractionsRepository;

    public UsersInteractionsController(UserInteractionsRepository userInteractionsRepository) {
        this.userInteractionsRepository = userInteractionsRepository;
    }

    @PostMapping(path="/getUserReviewByPointId/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE) 
    public List<UserInteraction> getUserReviewByPointId(@RequestBody int id) { 
        return userInteractionsRepository.getUserReviewByPointId(id); 
    }
} 