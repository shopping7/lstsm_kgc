package cn.shopping.lstsm_kgc.service.impl;

import cn.shopping.lstsm_kgc.service.UserAttrService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserAttrServiceImplTest {

    @Autowired
    UserAttrService service;

    @Test
    public void getUserAttr(){
        List<String> list = service.getUserAttr("zhangsan");
        for (String attr : list) {
            System.out.println(attr);
        }
    }

    @Test
    public void addUser(){
        service.addUser("赵六1",true,"11773","234");
    }

    @Test
    public void addUserAttr(){
        service.addUserAttr("zhangsan","医生");
    }

//    @Test
//    public void deleteUserAttr(){
//        service.DeleteUserAttr("zhangsan","医生");
//    }

}