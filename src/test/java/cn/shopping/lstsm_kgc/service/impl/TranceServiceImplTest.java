package cn.shopping.lstsm_kgc.service.impl;

import cn.shopping.lstsm_kgc.config.Serial;
import cn.shopping.lstsm_kgc.entity.*;
import cn.shopping.lstsm_kgc.service.SysParaService;
import cn.shopping.lstsm_kgc.service.TranceService;
import cn.shopping.lstsm_kgc.service.UserKeyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TranceServiceImplTest {
    @Autowired
    TranceService tranceService;

    @Autowired
    SysParaService sysParaService;

    @Autowired
    UserKeyService userKeyService;

    @Test
    public void trance(){
        DoublePairing doublePairing = new DoublePairing();
        doublePairing.getStart();
        Serial serial = new Serial();
        SysPara sysPara = sysParaService.getSysPara();
        byte[] pp_b = sysPara.getPp();
        byte[] msk_b = sysPara.getMsk();
        PP pp = (PP)serial.deserial(pp_b);
        MSK msk = (MSK)serial.deserial(msk_b);

        UserKey userKey = userKeyService.getUserKey("id");
        byte[] sk_b = userKey.getSk();
        SK sk = (SK)serial.deserial(sk_b);

        String id = tranceService.Trace(pp, msk, sk);
        System.out.println(id);
    }

}