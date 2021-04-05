package cn.shopping.lstsm_kgc.service.impl;

import cn.shopping.lstsm_kgc.domain.UserVO;
import cn.shopping.lstsm_kgc.entity.User;
import cn.shopping.lstsm_kgc.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    UserService userService;

    @Test
    public void getUser(){
        UserVO zhangsan = userService.getOneUser("zhangsan");
        System.out.println(zhangsan);
    }

    @Test
    public void getAllUsers(){
        List<UserVO> allUser = userService.getAllUsers();
        System.out.println(allUser);
    }

    @Test
    public void correctPwd(){
//        if(userService.correctPwd("lisi", "123456")){
//            userService.editPwd("lisi","234567");
//
//        }else{
//            System.out.println(222);
//        }
        userService.editPwd("lisi","234567");
    }

}