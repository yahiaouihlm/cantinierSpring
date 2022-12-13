package fr.sali.cantine.dto.in;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.sali.cantine.entity.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class MenuDto {

    private String description;

    private String jourassocier;


    private String label;

    private BigDecimal prixht;


    private Integer status;


    private List<OrderEntity> commandes;


    private ImageEntity image;


    // Pas  Besoin de recevoire toute un plat il  faut enregister  dans  la base tout d'abord
   // private List<MealEntity> plats;

    private List<QuantiteEntity> quantites;



    /**
     *  La list est utilisé  pour stocker les ids des plats qui  contient un Menu
     */
   private List <Integer> mealsIDS;
    /**
     * @doc La méthode vérifié la nullable des paramétreS,  construit Le Menu entité
     * @return  Le MenuEntity (Menu)
     * @throws IllegalArgumentException Si  l'un des arguments  ESt NULL
     * */


    @JsonIgnore
    public MenuEntity toMenuEntity () throws  IllegalArgumentException {
        if (this.description == null || this.label ==null || this.prixht == null || this.status ==null )
            throw new IllegalArgumentException("Invalid Argument  No  Null  Argument accepted ") ;
        MenuEntity   menu =  new MenuEntity() ;
        menu.setStatus(this.status);
        menu.setLabel(this.label);
        menu.setDescription(this.description);
        menu.setJourassocier(new Date().toString());
        menu.setPrixht(this.prixht);
      //  menu.setPlats(this.plats);
        // Les images

        return  menu;
    }











    /************************ Les getters  et  Les Setters  ******************************/

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getJourassocier() {
        return jourassocier;
    }

    public void setJourassocier(String jourassocier) {
        this.jourassocier = jourassocier;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public BigDecimal getPrixht() {
        return prixht;
    }

    public void setPrixht(BigDecimal prixht) {
        this.prixht = prixht;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<OrderEntity> getCommandes() {
        return commandes;
    }

    public void setCommandes(List<OrderEntity> commandes) {
        this.commandes = commandes;
    }

    public ImageEntity getImage() {
        return image;
    }

    public void setImage(ImageEntity image) {
        this.image = image;
    }

  /*  public List<MealEntity> getPlats() {
        return plats;
    }

    public void setPlats(List<MealEntity> plats) {
        this.plats = plats;
    }*/

    public List<QuantiteEntity> getQuantites() {
        return quantites;
    }

    public void setQuantites(List<QuantiteEntity> quantites) {
        this.quantites = quantites;
    }

    public List <Integer> getMealsIS() {
        return mealsIDS;
    }

    public void setMealsIS (List <Integer> mealsIS) {
        this.mealsIDS = mealsIS;
    }

}
