package cn.shopping.lstsm_kgc.service.impl;

import cn.shopping.lstsm_kgc.config.DoublePairing;
import cn.shopping.lstsm_kgc.config.Serial;
import cn.shopping.lstsm_kgc.config.lsss.LSSSEngine;
import cn.shopping.lstsm_kgc.config.lsss.LSSSMatrix;
import cn.shopping.lstsm_kgc.entity.*;
import cn.shopping.lstsm_kgc.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.List;

@SpringBootTest
class UploadFileServiceImplTest {

    @Autowired
    UploadFileService uploadFileService;

    @Autowired
    TransformService transformService;

    @Autowired
    UserKeyService userKeyService;

    @Autowired
    UserAttrService userAttrService;

    @Autowired
    TrapdoorService trapdoorService;

    @Autowired
    SysParaService sysParaService;

    @Test
    public void uploadFile(){
        SysPara sysPara = sysParaService.getSysPara();
        Serial serial = new Serial();
        PP pp = (PP)serial.deserial(sysPara.getPp());
        UserKey userKey = userKeyService.getUserKey("张三");
        byte[] sk_b = userKey.getSk();
        SK sk = (SK)serial.deserial(sk_b);
        String policy = "医院&医生&(头疼|(流感&心脏科))";
        LSSSEngine engine = new LSSSEngine();
        LSSSMatrix lsss = engine.genMatrix(policy);
        DoublePairing doublePairing = new DoublePairing();
        doublePairing.getStart();
        File file = new File("C:\\Users\\shopping3\\Downloads\\12.txt");
        uploadFileService.Enc(pp,sk,file,lsss,"test1234");
    }

    @Test
    public void getFile(){
        DoublePairing doublePairing = new DoublePairing();
        doublePairing.getStart();

        UserKey userKey = userKeyService.getUserKey("zhangsan");
        Serial serial = new Serial();
        SK sk = (SK)serial.deserial(userKey.getSk());
        PK pk = (PK)serial.deserial(userKey.getPk());

        List userAttr = userAttrService.getUserAttr("zhangsan");
        String[] attrs = (String[]) userAttr.toArray(new String[userAttr.size()]);

        List<UploadFile> fileList = uploadFileService.getFile("test1234");
        System.out.println(fileList.size());
        for(UploadFile key_file : fileList){
            CT ct = (CT)serial.deserial(key_file.getCt());
            LSSSMatrix lsss = (LSSSMatrix) serial.deserial(key_file.getLsss());
            LSSSMatrix lsssD1 = lsss.extract(attrs);
            int lsssIndex[] = lsssD1.getIndex();
            Tkw tkw = trapdoorService.Trapdoor(sk, "test1234");
            CTout ctout = transformService.Transform(ct, tkw, pk,lsssD1,lsssIndex);
            if(ctout != null) {
                    byte[] dec = trapdoorService.Dec(ctout, sk);
                    System.out.println(dec.length);
                    String cm = new String(dec);
                    System.out.println(cm);
                }
            }

        }

        @Test
        public void get(){
            List<UploadFile> fileList = uploadFileService.getFile("test1234");
            System.out.println(fileList.size());
        }


}