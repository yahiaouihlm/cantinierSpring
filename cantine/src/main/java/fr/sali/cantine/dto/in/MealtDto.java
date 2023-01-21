package fr.sali.cantine.dto.in;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.sali.cantine.entity.*;
import java.math.BigDecimal;
import java.util.List;

public class MealtDto {
    private String categorie;
    private String description;
    private String label;
    private BigDecimal prixht;
    private Integer quantite;
    private Integer status;
    private  String  image;



    /**
     * @doc  :  Vérifier La nullable des attribues categorie , label , description , status, et le prix
     * @return  Le PlatEntity
     * @throws IllegalArgumentException si  l'un des arguments est null
     */

    @JsonIgnore
    public MealEntity toMeal () throws  IllegalArgumentException {
        if (this.categorie == null || this.label == null || this.description == null || this.prixht == null
                || this.quantite == null  || this.image == null  )
             throw new IllegalArgumentException("Invalide Argument No Null  Argument Accepted ") ;
        if (this.quantite < 0  ||  ! (this.quantite instanceof  Integer)  || this.prixht.compareTo(BigDecimal.ZERO) < 0 )
            throw new IllegalArgumentException("Invalide Quantity Argument (it must  be an  Intger bigger that  0 )") ;

        MealEntity plat =  new MealEntity();
        plat.setLabel(this.label);
        plat.setCategorie(this.categorie);
        plat.setPrixht(this.prixht);
        plat.setDescription(this.description);
        plat.setQuantite(this.quantite);
        ImageEntity mealImgae =  new ImageEntity();
        mealImgae.setImage(this.image);
        plat.setImage(mealImgae);

        return  plat;
    }










    /********************************** Les Getters  Et Setters ******************************************************/

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

   /* public List<MenuEntity> getMenus() {
        return menus;
    }*/

    /*public void setMenus(List<MenuEntity> menus) {
        this.menus = menus;
    }*/

    public String  getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image  = image;
    }


}
