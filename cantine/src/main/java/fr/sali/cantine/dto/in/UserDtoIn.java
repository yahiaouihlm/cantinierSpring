package fr.sali.cantine.dto.in;

import fr.sali.cantine.entity.CommandeEntity;
import fr.sali.cantine.entity.UserEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class UserDtoIn {


    private Integer id;

    private LocalDate birthday;

    private LocalDate creationDate;
    private BigDecimal credit;

    private String email;


    private String password;


    private String phone;

    private Integer status;


    private String userfname;


    private String username;


    private List<CommandeEntity> commandes;






     public UserEntity toEntity() {
         var  UserEntity = new UserEntity( this.username ,  this.userfname , this.email ,  );
     }






    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public BigDecimal getCredit() {
        return credit;
    }

    public void setCredit(BigDecimal credit) {
        this.credit = credit;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUserfname() {
        return userfname;
    }

    public void setUserfname(String userfname) {
        this.userfname = userfname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<CommandeEntity> getCommandes() {
        return commandes;
    }

    public void setCommandes(List<CommandeEntity> commandes) {
        this.commandes = commandes;
    }
}
