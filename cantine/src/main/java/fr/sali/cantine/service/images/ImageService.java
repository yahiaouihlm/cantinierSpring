package fr.sali.cantine.service.images;


import fr.sali.cantine.service.exception.ImagePathException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Path;
import java.util.UUID;

@Service
public class ImageService {

    /**
     * @doc  :  the function save image in path with a unique name
     * @param image :  the Image  As  MulipartFile Type
     * @param path : the directory where this Image will  be  saved
     * @return : A new Unique name of image
     * @throws ImagePathException :  if  arguments  Are Not Valide
     * @throws IOException :  An image transformation or saving  error
     */
    public  String   uploadImage (MultipartFile image ,  String  path ) throws ImagePathException, IOException {
        if  (image == null || path == null || path.isEmpty())
            throw   new ImagePathException("Invalid Argument(s) In uploadImage,{ imgae= "+ image+ " " + " path : " + path +" }");
        var name =  image.getOriginalFilename();
        name  = UUID.randomUUID().toString()+name ;
        var spot = path+"/"+name;
        File file =  new File(spot);
        image.transferTo(Path.of(file.getPath()));
        return name ;
    }

    public InputStream downloadImage (String imageName,  String path) throws ImagePathException, FileNotFoundException {
        if  (imageName == null || path == null || imageName.isEmpty() || path.isEmpty() )
            throw   new ImagePathException("Invalid Argument(s) In download,{ imageName= "+ imageName+ " " + " path : " + path +" }");

        var spot =  path + "/" + imageName;
        return  new FileInputStream(spot);
    }


    public  Boolean  removeImga (String imageName ,   String  path ) {
        if  (imageName == null || path == null || imageName.isEmpty() || path.isEmpty() )
            return  false ;
        var  spot  = path+ "/" + imageName;
        File file  =   new File(spot);
        return file.delete() ;
    }

    public  String  updateImage ( String  oldImageName ,  MultipartFile image,  String  path ) throws ImagePathException, IOException {
        if (!removeImga (oldImageName ,    path )) {
            throw  new ImagePathException("Image Not Found  In UpdateImage ") ;
        }
        return   uploadImage ( image ,   path ) ;
    }
}
