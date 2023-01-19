package fr.sali.cantine.dto.out;

import fr.sali.cantine.entity.ImageEntity;

public class ImageDtout {

      private   Integer idimage ;
      private byte[] image;

      public ImageDtout (ImageEntity imageEntity){
          this.idimage  = imageEntity.getIdimage() ;
          this.image   =  imageEntity.getImage() ;
      }
    public Integer getIdimage() {
        return idimage;
    }

    public void setIdimage(Integer idimage) {
        this.idimage = idimage;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
