package pt.iade.AquaPoint.controllers; 
import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 
import org.springframework.http.MediaType; 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RestController; 
import org.springframework.web.bind.annotation.PathVariable; 

@RestController 
@RequestMapping(path="/api/java/users/") 
public class UsersController {   
    private Logger logger = LoggerFactory.getLogger(AquaPointsController.class); 

    @RequestMapping(path="/getUserDataByName/{name}", produces = MediaType.APPLICATION_JSON_VALUE) 
    public Boolean getUserDataByName(String name) { 
        return true;
    }
    
    @RequestMapping(path="/createNewUser/", produces = MediaType.APPLICATION_JSON_VALUE) 
    public Boolean getAquaPoints() { 
        return true;
    }
} 