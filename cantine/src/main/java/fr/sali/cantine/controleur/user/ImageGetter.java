package fr.sali.cantine.controleur.user;

import fr.sali.cantine.service.images.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;

@RestController
public class ImageGetter {

    @Autowired
    ImageService imageService ;
    @GetMapping(value = "/cantine/download/images/{spot}/{image}")
    public ResponseEntity<InputStreamResource> getImage (@PathVariable(value="image") String image ,   @PathVariable(value="spot") String  spot) {
        /*TODO  : mettre le path dans  le fichier de configuration */

        var path = "images/"+spot;
        try {
            MediaType contentType = image.substring(image.lastIndexOf('.')).equals(".png") ? MediaType.IMAGE_PNG : MediaType.IMAGE_JPEG;
            InputStream input = this.imageService.downloadImage(image, path);
            return ResponseEntity.ok()
                    .contentType(contentType)
                    .body(new InputStreamResource(input));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
    }




}
