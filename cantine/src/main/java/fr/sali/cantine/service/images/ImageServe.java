package fr.sali.cantine.service.images;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Optional;
import java.util.OptionalLong;
import java.util.UUID;

@Service
public class ImageServe {


      public  String   uploadImage (MultipartFile image ,  String  path ) throws Exception {
            if  (image == null || path == null || path.isEmpty())
                   throw   new IllegalArgumentException("Invalid Argument ");
            var name =  image.getOriginalFilename();
            name  = UUID.randomUUID().toString()+name ;
            var spot = path+"/"+name;
            File file =  new File(spot);
            image.transferTo(Path.of(file.getPath()));
            return name ;
      }

      public InputStream downloadImage (String imageName,  String path) throws  Exception{
            if  (imageName == null || path == null || imageName.isEmpty() || path.isEmpty() )
                    throw  new IllegalArgumentException("Invald Arguments ");
            var spot =  path + "/" + imageName;
            return  new FileInputStream(spot);
      }


      public  Boolean  RemoveImga (String imageName ,   String  path ) {
            if  (imageName == null || path == null || imageName.isEmpty() || path.isEmpty() )
                   return  false ;
            var  spot  = path+ "/" + imageName;
            File file  =   new File(spot);
            return file.delete();
      }
}
