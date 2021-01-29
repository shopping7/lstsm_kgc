package cn.shopping.lstsm_kgc.service.impl;

import cn.shopping.lstsm_kgc.config.Serial;
import cn.shopping.lstsm_kgc.config.lsss.LSSSEngine;
import cn.shopping.lstsm_kgc.config.lsss.LSSSMatrix;
import cn.shopping.lstsm_kgc.entity.*;
import cn.shopping.lstsm_kgc.service.SysParaService;
import cn.shopping.lstsm_kgc.service.UploadFileService;
import cn.shopping.lstsm_kgc.service.UserKeyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UploadFileServiceImplTest {

    @Autowired
    UploadFileService uploadFileService;

    @Autowired
    SysParaService sysParaService;

    @Autowired
    UserKeyService userKeyService;

    @Test
    public void uploadFile(){
//        Ppandmsk ppandmsk = sysParaService.getSysPara();
//        PP pp = ppandmsk.getPp();
//        UserKey userKey = userKeyService.getUserKey("id");
//        byte[] sk_b = userKey.getSk();
//        Serial serial = new Serial();
//        SK sk = (SK)serial.deserial(sk_b);
//        String policy = "hospital&doctor&(headache|(flu&heart))";
//        LSSSEngine engine = new LSSSEngine();
//        LSSSMatrix lsss = engine.genMatrix(policy);
//        DoublePairing doublePairing = new DoublePairing();
//        doublePairing.getStart();
//        uploadFileService.Enc(pp,sk,"trytry",lsss,"hospital");
    }



}