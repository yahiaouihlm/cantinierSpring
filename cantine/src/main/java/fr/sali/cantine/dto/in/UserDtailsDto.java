package fr.sali.cantine.dto.in;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.sali.cantine.entity.RoleEntity;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class UserDtailsDto {
    private Integer id;
    private LocalDate birthday;
    private String email;
    private String userfname;
    private String username;

    private List<RolesDto> roles ;


    public UserDtailsDto(Integer id, LocalDate birthday, String email, String userfname, List<RoleEntity> roles) {
        this.id = id;
        this.birthday = birthday;
        this.email = email;
        this.userfname = userfname;
        fromRolesEntityToRolesDto (roles) ;
    }

    @JsonIgnore
    public void   fromRolesEntityToRolesDto (List<RoleEntity> rolesEntity){
         this.roles =  new LinkedList<>() ;
        for (RoleEntity roleEntity : rolesEntity ) {
             this.roles.add( new RolesDto( roleEntity.getIdrole() , roleEntity.getDescription() ,  roleEntity.getLibelle()) );
        }
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
}
