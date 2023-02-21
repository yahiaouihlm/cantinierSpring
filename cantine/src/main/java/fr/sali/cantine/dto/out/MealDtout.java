package fr.sali.cantine.dto.out;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.sali.cantine.entity.ImageEntity;
import fr.sali.cantine.entity.MealEntity;

import java.math.BigDecimal;

public class MealDtout {

    private  Integer id ;
    private String label ;
    private String description;
    private String categorie;
    private BigDecimal prixht;
    private Integer quantite;
    private Integer status;

    private String image ;
    /*TODO
    METTRE L4ADRESSE DU  SERVEUR DANS UN  FICHER DES PROPERTIES
     */
    public MealDtout(MealEntity meal ) {
        this.id = meal.getIdplat();
        this.description = meal.getDescription();
        this.categorie = meal.getCategorie();
        this.prixht = meal.getPrixht();
        this.quantite = meal.getQuantite();
        this.status = meal.getStatus();
        this.label = meal.getLabel();
        var  path  = meal.getImage().getNameimage() ;
         if  ( path == null )
              path =  "3904de73-8edc-4e73-87ed-dd9e45a0346dmainimage3.jpg" ;
        this.image = "http://localhost:8080/cantine/download/" + path ;

    }



    @JsonIgnore
   public  ImageDtout  fromImgaeEntityToDo(ImageEntity imageEntity)  {
        return  new ImageDtout(imageEntity);
   }

    public Integer getId() {
        return id;
    }


    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public BigDecimal getPrixht() {
        return prixht;
    }

    public void setPrixht(BigDecimal prixht) {
        this.prixht = prixht;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String  getImage() {
        return image;
    }

    public void setImage(String  image) {
        this.image = image;
    }
}
