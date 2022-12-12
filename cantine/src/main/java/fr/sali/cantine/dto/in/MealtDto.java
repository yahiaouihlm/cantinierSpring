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


    private List<MenuEntity> menus;

    private ImageEntity image;


    private List<OrderEntity> commandes;


    private List<QuantiteEntity> quantites;

    /**
     * @doc  :  Vérifier La nullable des attribues categorie , label , description , status, et le prix
     * @return  Le PlatEntity
     * @throws IllegalArgumentException si  l'un des arguments est null
     */

    @JsonIgnore
    public MealEntity toPlat () throws  IllegalArgumentException {
        if (this.categorie == null || this.label == null || this.description == null || this.prixht == null || this.status ==null  || this.quantite == null)
             throw new IllegalArgumentException("Invalide Argument No Null  Argument Accepted ") ;
        if (this.quantite < 0 )
            throw new IllegalArgumentException("Invalide Quantity Argument (it must  be an  Intger bigger that  0 )") ;

        MealEntity plat =  new MealEntity();
        plat.setLabel(this.label);
        plat.setCategorie(this.categorie);
        plat.setPrixht(this.prixht);
        plat.setStatus(this.status);
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

    public List<MenuEntity> getMenus() {
        return menus;
    }

    public void setMenus(List<MenuEntity> menus) {
        this.menus = menus;
    }

    public ImageEntity getImage() {
        return image;
    }

    public void setImage(ImageEntity image) {
        this.image = image;
    }

    public List<OrderEntity> getCommandes() {
        return commandes;
    }

    public void setCommandes(List<OrderEntity> commandes) {
        this.commandes = commandes;
    }

    public List<QuantiteEntity> getQuantites() {
        return quantites;
    }

    public void setQuantites(List<QuantiteEntity> quantites) {
        this.quantites = quantites;
    }
}
