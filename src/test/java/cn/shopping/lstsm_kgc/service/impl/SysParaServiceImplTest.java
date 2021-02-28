package cn.shopping.lstsm_kgc.service.impl;

import cn.shopping.lstsm_kgc.config.DoublePairing;
import cn.shopping.lstsm_kgc.service.SysParaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class SysParaServiceImplTest {

    @Autowired
    SysParaService service;

    @Test
    public void setSysPara(){
        DoublePairing doublePairing = new DoublePairing();
        doublePairing.getStart();
        service.Setup();
    }

    @Test
    public void getSysPara(){
//        Ppandmsk ppandmsk = service.getSysPara();
//        PP pp = ppandmsk.getPp();
//        MSK msk = ppandmsk.getMsk();
    }

}