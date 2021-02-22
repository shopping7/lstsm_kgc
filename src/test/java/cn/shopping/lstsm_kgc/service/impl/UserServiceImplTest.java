package cn.shopping.lstsm_kgc.service.impl;

import cn.shopping.lstsm_kgc.entity.User;
import cn.shopping.lstsm_kgc.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    UserService userService;

    @Test
    public void getUser(){
        User zhangsan = userService.getUser("zhangsan");
        System.out.println(zhangsan);
    }
}