package fr.sali.cantine.dto.in;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class LoginDto {

private  String email ;
private  String  password ;



   public  LoginDto (){
   }


    public LoginDto(String email, String password) {
        this.email = email;
        this.password = password;
    }



    @JsonIgnore
    public void   validate () throws IllegalArgumentException  {
           if (this.email == null || this.email.trim().length() < 4 )
                  throw new IllegalArgumentException(" Invalid Arguement ") ;
           if (this.password == null || this.password.trim().length() < 1)
               throw new IllegalArgumentException(" Invalid Arguement ") ;

        this.validationUserEmail ();
    }

    @JsonIgnore
    public void validationUserEmail () throws  IllegalArgumentException{
        if (this.email.endsWith("@social.aston-ecole.com") == false )
            throw  new  IllegalArgumentException("Invalid Email: you have to  be Aston Student ");
        this.email =  this.email.toLowerCase();
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
}
