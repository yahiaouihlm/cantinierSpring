package fr.sali.cantine.dto.in;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.sali.cantine.entity.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.sql.SQLOutput;
import java.util.Date;
import java.util.List;

public class MenuDto {

    private String description;

    private String jourassocier;


    private String label;

    private BigDecimal prixht;


    private Integer status;



    private  Integer quantite ;
    private List<OrderEntity> commandes;


    private MultipartFile image;


    // Pas  Besoin de recevoire toute un plat il  faut enregister  dans  la base tout d'abord
   // private List<MealEntity> plats;

    private List<QuantiteDto> quantites;




    /**
     *  La list est utilisé  pour stocker les ids des plats qui  contient un Menu
     */
   private List <String > mealsIDS;
    /**
     * @doc La méthode vérifié la nullable des paramétreS,  construit Le Menu entité
     * @return  Le MenuEntity (Menu)
     * @throws IllegalArgumentException Si  l'un des arguments  ESt NULL
     * */


    @JsonIgnore
    public MenuEntity toMenuEntity () throws  IllegalArgumentException {
        System.out.println("exceptio  lever  ");
        if (this.description == null || this.label ==null || this.prixht == null
               || this.image == null)
            throw new IllegalArgumentException("Invalid Argument  No  Null  Argument accepted ") ;

        MenuEntity   menu =  new MenuEntity() ;

       // menu.setStatus(this.status);
        menu.setLabel(this.label);
        menu.setDescription(this.description);
        menu.setJourassocier(new Date().toString());
        menu.setPrixht(this.prixht);
        menu.setStatus(1);
        menu.setQuantite(this.quantite);
      //  menu.setPlats(this.plats);
        // Les images

        return  menu;
    }











    /************************ Les getters  et  Les Setters  ******************************/
    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }
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

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }


    public List<String> getMealsIDS() {
        return mealsIDS;
    }

    public void setMealsIDS(List<String> mealsIDS) {
        this.mealsIDS = mealsIDS;
    }

  /*  public List<MealEntity> getPlats() {
        return plats;
    }

    public void setPlats(List<MealEntity> plats) {
        this.plats = plats;
    }*/







}
