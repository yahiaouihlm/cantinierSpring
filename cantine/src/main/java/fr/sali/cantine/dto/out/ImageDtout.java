package fr.sali.cantine.dto.out;

import fr.sali.cantine.entity.ImageEntity;

public class ImageDtout {

      private   Integer idimage ;
      private String image;

      public ImageDtout (ImageEntity imageEntity){
          this.idimage  = imageEntity.getIdimage() ;

      }
    public Integer getIdimage() {
        return idimage;
    }

    public void setIdimage(Integer idimage) {
        this.idimage = idimage;
    }

    public String getImage() {
        return image;
    }

    public void setImage( String image) {
        this.image = image;
    }
}
