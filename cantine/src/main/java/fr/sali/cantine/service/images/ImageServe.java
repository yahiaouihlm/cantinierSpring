package fr.sali.cantine.service.images;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Optional;
import java.util.OptionalLong;

@Service
public class ImageServe {


      public  Boolean uploadImage (MultipartFile image ,  String  path ) {
            var name =  image.getOriginalFilename();
            name  =
      }



      public  String removeExtensoin (String  filename   ){
            String fileNameWithoutExtension = filename.substring(0, filename.lastIndexOf('.'));
            return fileNameWithoutExtension ;
      }
}
