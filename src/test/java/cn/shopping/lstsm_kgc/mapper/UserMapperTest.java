package cn.shopping.lstsm_kgc.mapper;

import cn.shopping.lstsm_kgc.domain.UserVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserMapperTest {

    @Autowired
    UserMapper userMapper;

    @Test
    public void getOne(){
        UserVO zhangsan = userMapper.loginUser("zhangsan", "123456");
        UserVO lisi = userMapper.getOneUser("lisi");
        System.out.println(lisi);
        System.out.println(zhangsan);
    }
}