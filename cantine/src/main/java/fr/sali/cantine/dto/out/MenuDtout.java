package fr.sali.cantine.dto.out;

import fr.sali.cantine.entity.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

public class MenuDtout {


    private Integer idmenu;
    private String description;
    private String jourassocier;

    private String label;

    private BigDecimal prixht;


    private Integer status;


    //bi-directional many-to-one association to ImageEntity

    private String image;

    //bi-directional many-to-many association to PlatEntity

    private List<MealDtout> plats;
    //bi-directional many-to-one association to QuantiteEntity

    private  Integer quantite ;


    public  MenuDtout (MenuEntity menu ){
        this.idmenu =  menu.getIdmenu();
        this.description =  menu.getDescription();
        this.label =  menu.getLabel();
        this.prixht = menu.getPrixht();
        this.quantite =  menu.getQuantite();
        this.jourassocier =  menu.getJourassocier();
        this.status=  menu.getStatus();
        this.plats =  menu.getPlats().stream().map(MealDtout :: new).toList();
        var path= menu.getImage().getNameimage();
        if (path== null) {
               path = "3904de73-8edc-4e73-87ed-dd9e45a0346dmainimage3.jpg";
        }
        this.image = "http://localhost:8080/cantine/download/images/menus/" + path ;
    }


    public Integer getIdmenu() {
        return idmenu;
    }

    public void setIdmenu(Integer idmenu) {
        this.idmenu = idmenu;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<MealDtout> getPlats() {
        return plats;
    }

    public void setPlats(List<MealDtout> plats) {
        this.plats = plats;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }
}
