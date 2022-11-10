package fr.sali.cantine.dto.in;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.sali.cantine.entity.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

public class PlatDto {

    private String categorie;


    private String description;


    private String label;


    private BigDecimal prixht;


    private String quantite;

    private Integer status;


    private List<MenuEntity> menus;

    private ImageEntity image;


    private List<CommandeEntity> commandes;


    private List<QuantiteEntity> quantites;

    /**
     * @doc  :  Vérifier La nullable des attribues categorie , label , description , status, et le prix
     * @return  Le PlatEntity
     * @throws IllegalArgumentException si  l'un des arguments est null
     */

    @JsonIgnore
    public PlatEntity toPlat () throws  IllegalArgumentException {
        if (this.categorie == null || this.label == null || this.description == null || this.prixht == null || this.status ==null )
             throw new IllegalArgumentException("Invalide Argument No Null  Argument Accepted ") ;

        PlatEntity plat =  new PlatEntity();
        plat.setLabel(this.label);
        plat.setCategorie(this.categorie);
        plat.setPrixht(this.prixht);
        plat.setStatus(this.status);
        plat.setDescription(this.description);

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

    public String getQuantite() {
        return quantite;
    }

    public void setQuantite(String quantite) {
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

    public List<CommandeEntity> getCommandes() {
        return commandes;
    }

    public void setCommandes(List<CommandeEntity> commandes) {
        this.commandes = commandes;
    }

    public List<QuantiteEntity> getQuantites() {
        return quantites;
    }

    public void setQuantites(List<QuantiteEntity> quantites) {
        this.quantites = quantites;
    }
}
