package fr.sali.cantine.dto.out;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.sali.cantine.entity.OrderEntity;
import fr.sali.cantine.entity.UserEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class UserDtout {


    private Integer id;
    private LocalDate birthday;
    private BigDecimal credit;
    private String email;
    private String phone;
    private String userfname;
    private String username;
    private List<OrderDtout> commandes;

    public UserDtout (){}

    public  UserDtout (UserEntity user){
   //    this.commandes =  user.getCommandes();
       this.email  =  user.getEmail();
       this.phone  =  user.getPhone() ;
       this.userfname =  user.getUserfname() ;
       this.username =  user.getUsername() ;
    }
    /**
     * Constructor of the object.
     *
     * @param pMap where to take information
     */
    @JsonIgnore
    public UserDtout(Map<String, ?> pMap) {
        super();
        if (pMap.get("id") != null) {
            this.setId((Integer) pMap.get("id"));
        }

        this.setEmail((String) pMap.get("email"));
        //this.setImageId((Integer) pMap.get("imageId"));
        this.setUsername((String) pMap.get("name"));
        this.setUserfname((String) pMap.get("firstname"));
        this.setPhone((String) pMap.get("phone"));

        if (pMap.get("credit") != null) {
            this.setCredit(BigDecimal.valueOf(((Number) pMap.get("credit")).doubleValue()));
        } else {
            this.setCredit(BigDecimal.valueOf(0D));
        }
    }




    public Integer getId() {return id;}

    public void setId(Integer id) {this.id = id;}

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public List<OrderDtout> getCommandes() {
        return commandes;
    }

    public void setCommandes(List<OrderDtout> commandes) {
        this.commandes = commandes;
    }
}
