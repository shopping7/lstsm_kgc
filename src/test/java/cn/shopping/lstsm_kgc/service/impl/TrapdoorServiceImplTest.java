package cn.shopping.lstsm_kgc.service.impl;

import cn.shopping.lstsm_kgc.config.Serial;
import cn.shopping.lstsm_kgc.config.lsss.LSSSEngine;
import cn.shopping.lstsm_kgc.config.lsss.LSSSMatrix;
import cn.shopping.lstsm_kgc.entity.*;
import cn.shopping.lstsm_kgc.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
class TrapdoorServiceImplTest {

    @Autowired
    TrapdoorService trapdoorService;

    @Autowired
    UserKeyService userKeyService;

    @Autowired
    TransformService transformService;

    @Autowired
    UploadFileService uploadFileService;

    @Autowired
    SysParaService sysParaService;

    @Test
    public void trapdoor(){
        DoublePairing doublePairing = new DoublePairing();
        doublePairing.getStart();
        UserKey userKey = userKeyService.getUserKey("id");
        byte[] sk_b = userKey.getSk();
        Serial serial = new Serial();
        SK sk = (SK)serial.deserial(sk_b);
        Tkw tkw = trapdoorService.Trapdoor(sk, "hospital");
        System.out.println(tkw);
    }

    @Test
    public void dec(){
        DoublePairing doublePairing = new DoublePairing();
        doublePairing.getStart();
        Serial serial = new Serial();
        sysParaService.Setup();
        SysPara sysPara = sysParaService.getSysPara();
        byte[] pp_b = sysPara.getPp();
        byte[] msk_b = sysPara.getMsk();
        PP pp = (PP)serial.deserial(pp_b);
        MSK msk = (MSK)serial.deserial(msk_b);

        String[] user_attr = {"hospital","doctor","flu","heart"};
        userKeyService.KeyGen(pp,msk,"id",user_attr);
        UserKey userKey = userKeyService.getUserKey("id");
        byte[] sk_b = userKey.getSk();
        byte[] pk_b = userKey.getPk();
        SK sk = (SK)serial.deserial(sk_b);
        PK pk = (PK)serial.deserial(pk_b);

        String policy = "hospital&doctor&(headache|(flu&heart))";
        LSSSEngine engine = new LSSSEngine();
        LSSSMatrix lsss = engine.genMatrix(policy);
        uploadFileService.Enc(pp, sk, "trytry", lsss, "hospital");
        List list = uploadFileService.getFile("hospital");
        for(Object object : list){
            UploadFile uploadFile = (UploadFile)object;
            byte[] ct_b = uploadFile.getCt();
            CT ct = (CT)serial.deserial(ct_b);

            LSSSMatrix lsssD1 = lsss.extract(user_attr);
            int lsssIndex[] = lsssD1.getIndex();
            Tkw tkw = trapdoorService.Trapdoor(sk, "hospital");
            CTout CTout = transformService.Transform(ct, tkw, pk, lsssD1, lsssIndex);
            if(CTout != null){
                byte[] dec = trapdoorService.Dec(CTout, sk);
                String str = new String(dec);
                System.out.println(str);
            }else{
                System.out.println("CTout is null");
            }
        }


        }


}