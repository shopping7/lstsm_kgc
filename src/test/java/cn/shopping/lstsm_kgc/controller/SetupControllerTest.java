package cn.shopping.lstsm_kgc.controller;

import cn.shopping.lstsm_kgc.entity.Ppandmsk;
import cn.shopping.lstsm_kgc.service.SetupService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SetupControllerTest {

    @Autowired
    SetupService service;

    @Test
    public void test(){
        service.Setup();
    }

    @Test
    public void get(){
        Ppandmsk ppMsk = service.getPpMsk();
        System.out.println(ppMsk);
    }


}