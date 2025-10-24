package pt.iade.AquaPoint.controllers; 
import java.sql.Date;
import java.util.List;

import org.slf4j.Logger; 
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DurationFormat.Unit;
import org.springframework.http.MediaType; 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RestController;

import pt.iade.AquaPoint.models.AquaPoint;
import pt.iade.AquaPoint.models.User;
import pt.iade.AquaPoint.repository.AquaPointRepository;

import org.springframework.web.bind.annotation.PathVariable; 

@RestController 
@RequestMapping(path="/api/java/aquapoints/") 
public class AquaPointsController {   
    private Logger logger = LoggerFactory.getLogger(AquaPointsController.class); 
    private final AquaPointRepository aquaPointRepository;

    public AquaPointsController(AquaPointRepository aquaPointRepository) {
        this.aquaPointRepository = aquaPointRepository;
    }

    @RequestMapping(path="/getAllAquaPoints/", produces = MediaType.APPLICATION_JSON_VALUE) 
    public List<AquaPoint> getAllUsers() { 
        return aquaPointRepository.findAll();
    }
    
    @PostMapping(path="/getAquaPointById/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE) 
    public AquaPoint getUserDataByName(/*int id*/) { 
        return aquaPointRepository.findById(8).orElse(null); // hardcode 8 para testar, deve ir do android studio
    }

    // in development...
    @PostMapping(path="/createNewAquaPoint/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE) 
    public AquaPoint createNewUser(AquaPoint pointData) { 
        AquaPoint testVar = new AquaPoint();
        return aquaPointRepository.save(pointData); // dados hardcode para testar, devem vir do android studio
    }
} 