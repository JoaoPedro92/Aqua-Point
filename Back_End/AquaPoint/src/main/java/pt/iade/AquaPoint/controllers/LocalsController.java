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

import pt.iade.AquaPoint.models.Local;
import pt.iade.AquaPoint.repository.LocalsRepository;

import org.springframework.web.bind.annotation.PathVariable; 

@RestController 
@RequestMapping(path="/api/java/locals/") 
public class LocalsController {   
    private Logger logger = LoggerFactory.getLogger(LocalsController.class); 
    private final LocalsRepository localsRepository;

    public LocalsController(LocalsRepository localsRepository) {
        this.localsRepository = localsRepository;
    }

    @RequestMapping(path="/getAllLocals/", produces = MediaType.APPLICATION_JSON_VALUE) 
    public List<Local> getAllLocals() { 
        return localsRepository.findAll();
    }
} 