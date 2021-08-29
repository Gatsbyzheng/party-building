package cn.cdcyy.partybuilding.management;

import cn.cdcyy.partybuilding.dao.entity.UserEntity;
import cn.cdcyy.partybuilding.dao.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class ManagementApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    void contextLoads() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setUserName("admin");
        userEntity.setPassword(new BCryptPasswordEncoder().encode("7133410q"));
        userRepository.save(userEntity);
    }

}
