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
import pt.iade.AquaPoint.models.RemoveAquaPoint;
import pt.iade.AquaPoint.models.ModifyPointState;
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
    public List<AquaPoint> getAllAquaPoints() { 
        return aquaPointRepository.findAll();
    }
    
    @PostMapping(path="/getAquaPointById/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE) 
    public AquaPoint getAquaPointById(@RequestBody int id) { 
        return aquaPointRepository.findById(id).orElse(null); 
    }

    @PostMapping(path="/getFavoriteAquaPointsByUserId/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE) 
    public List<AquaPoint> getFavoriteAquaPointsByUserId(@RequestBody int id) { 
        return aquaPointRepository.getFavoriteAquaPointsByUserId(id);
    }

    @PostMapping(path="/removeAquaPointFromFavorites/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE) 
    public int removeAquaPointFromFavorites(@RequestBody RemoveAquaPoint data) { 
        int userId = data.getUserId();
        int pointId = data.getPointId();

        return aquaPointRepository.removeAquaPointFromFavorites(pointId, userId);
    }

    @PostMapping(path="/addAquaPointToFavorite/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE) 
    public int addAquaPointToFavorite(@RequestBody RemoveAquaPoint data) { 
        int userId = data.getUserId();
        int pointId = data.getPointId();

        return aquaPointRepository.addAquaPointToFavorite(pointId, userId);
    }

    @PostMapping(path="/createNewAquaPoint/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE) 
    public AquaPoint createNewAquaPoint(@RequestBody AquaPoint pointData) { 
        AquaPoint AddedAquaPoint = aquaPointRepository.save(pointData);
        aquaPointRepository.AddAquaPointToStateList(AddedAquaPoint.getId(), 1);

        return AddedAquaPoint; 
    }

    @PostMapping(path="/editAquaPointState/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE) 
    public int editAquaPointState(@RequestBody ModifyPointState data) { 
        return aquaPointRepository.EditPointState(data.getPointId(), data.getStateId()); 
    }
} 