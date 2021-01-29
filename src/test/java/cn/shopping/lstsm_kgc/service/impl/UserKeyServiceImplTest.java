package cn.shopping.lstsm_kgc.service.impl;

import cn.shopping.lstsm_kgc.entity.*;
import cn.shopping.lstsm_kgc.mapper.SysParaMapper;
import cn.shopping.lstsm_kgc.service.SysParaService;
import cn.shopping.lstsm_kgc.service.UserKeyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserKeyServiceImplTest {

    @Autowired
    UserKeyService userKeyService;

    @Autowired
    SysParaService sysParaService;

    @Test
    public void keygen(){
//        Ppandmsk ppandmsk = sysParaService.getSysPara();
//        PP pp = ppandmsk.getPp();
//        MSK msk = ppandmsk.getMsk();
//        DoublePairing db = new DoublePairing();
//        db.getStart();
//        String[] attrs = {"hospital","doctor","heart"};
//        userKeyService.KeyGen(pp, msk,"id",attrs);
    }

    @Test
    public void getUserKey(){
        UserKey userKey = userKeyService.getUserKey("id");
        System.out.println(userKey);
    }
}