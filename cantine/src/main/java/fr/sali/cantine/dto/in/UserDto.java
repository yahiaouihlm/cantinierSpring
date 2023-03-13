package fr.sali.cantine.dto.in;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import fr.sali.cantine.entity.UserEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

import java.time.LocalDate;
import java.util.List;
import java.util.regex.Pattern;

public class UserDto {
    private static final Logger LOG = LogManager.getLogger();
    private Integer id;


    private  String birthdayAsString;
    private LocalDate birthday ;
    private LocalDate creationDate;
    private BigDecimal credit;
    private String email;
    private String password;
    private String phone;
    private Integer status;
    private String fullname;
    private String username;


    private MultipartFile image ;
    @JsonIgnore
    private List<OrderDto> commandes;

    /**
     * @doc  la méthode permet de vérifié si la "syntaxe" du  numéro téléphone française  est  valide en  la matchant  avec une regex
     * @throws IllegalArgumentException  si  le numéro  ne matche pas avec la regex du  numéro de téléphone
     */


    @JsonIgnore
    public void phoneValidator ()throws  IllegalArgumentException{
           if (this.phone == null)
                   throw  new IllegalArgumentException("Invalid Password ") ;

            String regex = "^(?:(?:\\+|00)33|0)\\s*[1-9](?:[\\s.-]*\\d{2}){4}$";
            Pattern pattern = Pattern.compile(regex);

          if( !(pattern.matcher(this.phone).matches()) ) {
              throw new IllegalArgumentException("Invalide Phone Number");
          }

    }
    /**
     * @doc  La méthode check si  les argument de login (email ,  password ) ,  que email termine avec @social.aston-ecole.com
     * @throws IllegalArgumentException si  email ou  password  sont null  ou  leur taille  est inférieur < 4  ou email  termine pas avec @social.aston-ecole.cpm
     */

    @JsonIgnore
    public void  validateSignInInformation () throws IllegalArgumentException{
        if (this.password == null  || this.email == null)
             throw  new IllegalArgumentException("Invalide Connexion Argument ");
        if (this.password.length() < 4 || this.email.length() < 4 )
            throw  new IllegalArgumentException("Invalide lenght Argument ");

         validationUserEmail ();
     }

    /**
     * @doc  construire un userEntity avec la  validations des Arguments
     * @return userEntity
     * @throws  IllegalArgumentException si  un  des arguments  passsé en  paramétre est vide ou  null
     */
    @JsonIgnore
    public UserEntity toEntity() throws  IllegalArgumentException {
         validateUserNullableOrEmptyInformation ();
         validateLenghtUserInformation();
         validationUserEmail();
        try {

            this.birthday =  LocalDate.parse(this.birthdayAsString);

        }catch ( Exception  e){

            throw  new IllegalArgumentException("Invalid   user  Birthday  :: String  can  not be parsed  ");
        }

        var  user = new UserEntity( this.username ,  this.fullname , this.email ,  this.birthday,  this.password);
        if (this.phone != null )
            phoneValidator();
        user.setPhone(this.phone);
         return   user  ;
     }


    /**
     * @doc  vérifier la valider des information  de utilisateur saisi pour faire un update de profile
     * @throws IllegalArgumentException :  si  l'un des arguments (name, familyname ,  phone )  est invalid
     */
    @JsonIgnore
     public   void checkNewUserInformation () throws  IllegalArgumentException{
        if (this.username == null  || this.fullname == null  ||  this.birthdayAsString == null ){
            LOG.debug("username = { }   userfnmae =  { } or  birthday = { }  is  null  ",  this.username ,  this.fullname ,  this.birthday);
               throw  new IllegalArgumentException("Invalid user Informations NO null Infomation accepted  ");

        }

        if (this.fullname.trim().length() < 4 || this.username.trim().length() < 4  ) {
                 throw  new IllegalArgumentException("Invalid user Inofrmation  Lenght ");
         }

        try {
            this.birthday =  LocalDate.parse(this.birthdayAsString);

        }catch ( Exception  e){
            throw  new IllegalArgumentException("Invalid   user  Birthday  :: String  can  not be parsed  ");
        }



        if (this.phone != null )
            phoneValidator();

     }


    /**
     * @doc    La fonction  teste si  l'email  d'enregistrement n'est pas de aston il termine pas avec "@social.aston-ecole.com"
     * @throws IllegalArgumentException
     */
    @JsonIgnore
    public void validationUserEmail ()throws  IllegalArgumentException{
        if (this.email.endsWith("@social.aston-ecole.com") == false )
            throw  new  IllegalArgumentException("Invalid Email: you have to  be Aston Student ");
        this.email =  this.email.toLowerCase();
    }


    /**
     * @doc  vérifier la taille du  mot de passe et  nom  et prenom
     * @throws IllegalArgumentException si  la taille du  mot de passe ou  email   inférieure à 4
     */
    @JsonIgnore
    public  void validateLenghtUserInformation()throws  IllegalArgumentException {
        if (this.username.length() < 4 || this.fullname.length() < 4 ||  this.password.length() < 4 )
            throw new IllegalArgumentException("Invalid Lenght Arguments");


    }



    /**
     * @doc   Vérifier que  username ,  userfname ,  user,email ,  mot de passe birthday sont pas null  ou  bide
     * @throws IllegalArgumentException si  les arguments  sont null  ou   vide
     */
    @JsonIgnore
    public void  validateUserNullableOrEmptyInformation  () throws  IllegalArgumentException{
        if (this.username == null  || this.fullname == null || this.email == null || this.password == null || this.birthdayAsString == null)
            throw  new  IllegalArgumentException("Ivalid Argument") ;

        if (this.username.trim().isEmpty() ||  this.fullname .trim().isEmpty() || this.email .trim().isEmpty() || this.password.trim().isEmpty())
            throw  new IllegalArgumentException("Ivalid Argument");
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
        return fullname;
    }

    public void setUserfname(String userfname) {
        this.fullname = userfname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<OrderDto> getCommandes() {
        return commandes;
    }

    public void setCommandes(List<OrderDto> commandes) {
        this.commandes = commandes;
    }


    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile  image) {
        this.image = image;
    }
    public String getDateofbirth() {
        return birthdayAsString;
    }

    public void setDateofbirth(String dateofbirth) {
        this.birthdayAsString = dateofbirth;
    }

}
