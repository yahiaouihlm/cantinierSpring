package fr.sali.cantine.dto.in;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.sali.cantine.entity.*;
import jdk.jfr.ContentType;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class MealtDto implements Serializable {
    private String categorie;
    private String description;
    private String label;
    private BigDecimal prixht;
    private Integer quantite;
    private Integer status;

    private MultipartFile image;



    /**
     * @doc  :  Vérifier La nullable des attribues categorie , label , description , status, et le prix
     * @return  Le PlatEntity
     * @throws IllegalArgumentException si  l'un des arguments est null
     * @NOTE :  le traitement des image n'est pas fais ici
     */

    @JsonIgnore
    public MealEntity toMeal () throws  IllegalArgumentException {
        if (this.categorie == null || this.label == null || this.description == null || this.prixht == null
                || this.quantite == null  || this.image == null  )
             throw new IllegalArgumentException("Invalide Argument No Null  Argument Accepted  ok  ") ;
        if (this.quantite < 0    || this.prixht.compareTo(BigDecimal.ZERO) < 0 )
            throw new IllegalArgumentException("Invalide Quantity Argument (it must  be an  Intger bigger that  0 )") ;

        MealEntity plat =  new MealEntity();
        plat.setLabel(this.label);
        plat.setCategorie(this.categorie);
        plat.setPrixht(this.prixht);
        plat.setDescription(this.description);
        plat.setQuantite(this.quantite);

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

    public MultipartFile  getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image  = image;
    }


}
