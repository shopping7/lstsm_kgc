package cn.shopping.lstsm_kgc.service.impl;

import cn.shopping.lstsm_kgc.config.Serial;
import cn.shopping.lstsm_kgc.entity.*;
import cn.shopping.lstsm_kgc.mapper.SysParaMapper;
import cn.shopping.lstsm_kgc.service.SysParaService;
import cn.shopping.lstsm_kgc.service.UserKeyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserKeyServiceImplTest {

    @Autowired
    UserKeyService userKeyService;

    @Autowired
    SysParaService sysParaService;

    @Test
    public void keygen(){
        SysPara sysPara = sysParaService.getSysPara();
        Serial serial = new Serial();
        PP pp = (PP)serial.deserial(sysPara.getPp());
        MSK msk = (MSK)serial.deserial(sysPara.getMsk());
        DoublePairing db = new DoublePairing();
        db.getStart();
        String[] attrs = {"hospital","doctor","headache"};
        userKeyService.KeyGen(pp, msk,"zhangsan",attrs,true);
    }

    @Test
    public void getUserKey(){
        UserKey userKey = userKeyService.getUserKey("zhangsan");
        byte[] sk_b = userKey.getSk();
        Serial serial = new Serial();
        SK sk = (SK)serial.deserial(sk_b);
        System.out.println(sk);
        File sk_file = new File("./sk.txt");
        ObjectOutputStream os = null;
        try {
            os = new ObjectOutputStream(new FileOutputStream(sk_file));
            os.writeObject(sk);
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Test
    public void getSK(){
        ObjectInputStream is = null;
        try {
            is = new ObjectInputStream(new FileInputStream("./sk.txt"));
            SK sk = (SK)is.readObject();
            System.out.println(sk);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}