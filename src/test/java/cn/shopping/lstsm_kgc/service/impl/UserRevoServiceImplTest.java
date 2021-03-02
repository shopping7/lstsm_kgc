package cn.shopping.lstsm_kgc.service.impl;

import cn.shopping.lstsm_kgc.config.Serial;
import cn.shopping.lstsm_kgc.entity.*;
import cn.shopping.lstsm_kgc.service.SysParaService;
import cn.shopping.lstsm_kgc.service.UserKeyService;
import cn.shopping.lstsm_kgc.service.UserRevoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRevoServiceImplTest {

    @Autowired
    UserRevoService userRevoService;

    @Autowired
    UserKeyService userKeyService;

    @Autowired
    SysParaService sysParaService;

    @Test
    public void trance(){
//        DoublePairing doublePairing = new DoublePairing();
//        doublePairing.getStart();
//        Serial serial = new Serial();
//        SysPara sysPara = sysParaService.getSysPara();
//        byte[] pp_b = sysPara.getPp();
//        byte[] msk_b = sysPara.getMsk();
//        PP pp = (PP)serial.deserial(pp_b);
//        MSK msk = (MSK)serial.deserial(msk_b);
//
//        UserKey userKey = userKeyService.getUserKey("id");
//        byte[] sk_b = userKey.getSk();
//        SK sk = (SK)serial.deserial(sk_b);
//
//        String id = userRevoService.Trace(pp, msk, sk);
//        System.out.println(id);
    }

    @Test
    public void userRevo(){
        UserKey userKey = userKeyService.getUserKey("zhangsan");
        byte[] sk_b = userKey.getSk();
        Serial serial = new Serial();
        SK sk = (SK)serial.deserial(sk_b);
        String d2 = new String(sk.getD2());
        List<UserRevo> allBlacks = userRevoService.getAllBlacks();
        for(UserRevo ur : allBlacks){
            if (new String(ur.getDelta()).equals(d2)){
                System.out.println(1);
            }else{
                System.out.println(2);
            }
        }
    }

    @Test
    public void getALLBlacks(){
        List allBlacks = userRevoService.getAllBlacks();
        System.out.println(allBlacks);
    }
}