package cn.shopping.lstsm_kgc.controller;

import cn.shopping.lstsm_kgc.config.Serial;
import cn.shopping.lstsm_kgc.entity.DoublePairing;
import cn.shopping.lstsm_kgc.entity.MSK;
import cn.shopping.lstsm_kgc.entity.PP;
import cn.shopping.lstsm_kgc.service.SysParaService;
import cn.shopping.lstsm_kgc.service.UserKeyService;
import cn.shopping.lstsm_kgc.service.impl.SysParaServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserKeyControllerTest {

    @Autowired
    UserKeyService userKeyService;
//    SysParaService sysParaService;

    @Test
    public void keygen(){
//        SysParaService sysParaService = new SysParaServiceImpl();
//        Ppandmsk ppandmsk = sysParaService.getSysPara();
//        MSK msk = ppandmsk.getMsk();
//        PP pp = ppandmsk.getPp();
//        String[] attrs = {"hospital","doctor","heart"};
//        DoublePairing doublePairing = new DoublePairing();
//        doublePairing.getStart();
//        userKeyService.KeyGen(pp,msk,"123",attrs);

    }
}