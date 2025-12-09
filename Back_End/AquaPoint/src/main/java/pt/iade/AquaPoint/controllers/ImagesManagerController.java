package pt.iade.AquaPoint.controllers; 

import org.slf4j.Logger; 
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType; 
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestBody;
import java.io.File;

@RestController 
@RequestMapping(path="/api/java/imagesManager/") 

public class ImagesManagerController {   
    private Logger logger = LoggerFactory.getLogger(UsersController.class); 
    
    @PostMapping(path = "/uploadAquaPointImage/",consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean uploadAquaPointImage(@RequestPart("file") MultipartFile file) {
        try {
            String fileName = file.getOriginalFilename(); 
            String projectDir = System.getProperty("user.dir");

            String folderPath = projectDir + "/src/main/resources/static/images/aquaPoints/";

            File directory = new File(folderPath);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            File dest = new File(directory, fileName);
            file.transferTo(dest);

            return true;
        } catch (Exception e) {
            //e.printStackTrace();

            return false;
        }
    }

    @PostMapping(path = "/uploadUserProfileImage/",consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean uploadUserProfileImage(@RequestPart("file") MultipartFile file) {
        try {
            String fileName = file.getOriginalFilename(); 
            String projectDir = System.getProperty("user.dir");

            String folderPath = projectDir + "/src/main/resources/static/images/userProfiles/";

            File directory = new File(folderPath);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            File dest = new File(directory, fileName);
            file.transferTo(dest);

            return true;
        } catch (Exception e) {
            //e.printStackTrace();

            return false;
        }
    }
} 