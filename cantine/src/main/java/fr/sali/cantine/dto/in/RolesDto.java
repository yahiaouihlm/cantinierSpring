package fr.sali.cantine.dto.in;

public class RolesDto {

    private Integer idrole;
    private String description;
    private String libelle;
    // J'ai pas ajouter les Users  par ce que c'est  pas utile dans  ce cas


    public RolesDto(Integer idrole  ,  String description ,  String libelle){
        this.idrole =  idrole ;
        this.libelle =  libelle;
        this.description =  description ;
    }


    public Integer getIdrole() {
        return idrole;
    }

    public void setIdrole(Integer idrole) {
        this.idrole = idrole;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
