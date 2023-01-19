package fr.sali.cantine.service;

import fr.sali.cantine.dto.in.UserDto;
import fr.sali.cantine.service.user.UserInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
class UpdateUserInfoTest {
    @Autowired
    UserInfoService updateUserInfoService ;
    @Test
    void  updateUserInformationTest  () throws   Exception {
        UserDto userdto =  new UserDto();
        userdto.setUsername("halim");
        userdto.setUserfname("zemour");
        userdto.setPhone("0631990180");
        userdto.setBirthday(LocalDate.of( 2000,07 , 18));

        userdto.setId(11);
        updateUserInfoService.updateUserInformation(userdto);
    }

}