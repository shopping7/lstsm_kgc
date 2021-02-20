package cn.shopping.lstsm_kgc.service.impl;

import cn.shopping.lstsm_kgc.config.Crytpto;
import cn.shopping.lstsm_kgc.config.CrytptpFile;
import cn.shopping.lstsm_kgc.config.Serial;
import cn.shopping.lstsm_kgc.config.lsss.LSSSMatrix;
import cn.shopping.lstsm_kgc.config.lsss.LSSSShares;
import cn.shopping.lstsm_kgc.config.lsss.Vector;
import cn.shopping.lstsm_kgc.entity.*;
import cn.shopping.lstsm_kgc.mapper.UploadFileMapper;
import cn.shopping.lstsm_kgc.service.UploadFileService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.io.File;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 公众号：java思维导图
 * @since 2021-01-28
 */
@Service
public class UploadFileServiceImpl extends ServiceImpl<UploadFileMapper, UploadFile> implements UploadFileService {



    @Autowired
    UploadFileMapper mapper;

    @Override
    public void Enc(PP pp, SK sk, File file, LSSSMatrix lsss, String KW) {
        Field Zr = DoublePairing.Zr;
        Field G1 = DoublePairing.G1;
        Field GT = DoublePairing.GT;
        Field K = DoublePairing.K;
        Element s_1 =  Zr.newElementFromBytes(sk.getS_1()).getImmutable();
        Element s_11 = Zr.newElementFromBytes(sk.getS_11()).getImmutable();
        Element gamma = GT.newRandomElement().getImmutable();
        String kse_t = DigestUtils.md5DigestAsHex(gamma.toBytes());
        Element kse = K.newElementFromBytes(kse_t.getBytes()).getImmutable();

        byte[] CM = new byte[0];
        try {
            CM = CrytptpFile.encrypt(file, kse.toBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Element s = Zr.newRandomElement().getImmutable();

        String attributes[] = lsss.getMap();
        int n = lsss.getMartix().getCols();
        int l = lsss.getMartix().getRows();
        Element[] Yn2 = new Element[n];//  get zr secret share matrix
        Yn2[0] = s.getImmutable();
        for(int i = 1 ; i < n; i++)
        {
            Yn2[i] = Zr.newRandomElement().getImmutable();
        }

        C3_Map[] C3 = new C3_Map[l];
        byte[][] C3_1 = new byte[l][];
        Vector secret2 = new Vector(false, Yn2);
        LSSSShares shares2 = lsss.genShareVector2(secret2);
        Element[] s_i = new Element[l];
        String kw_md5 = DigestUtils.md5DigestAsHex(KW.getBytes());
        Element HKW = Zr.newElementFromHash(kw_md5.getBytes(), 0, kw_md5.length()).getImmutable();
        for (int i = 0; i < l; i++) {
            s_i[i] = shares2.getVector().getValue2(i);
            String attributes_md5 = DigestUtils.md5DigestAsHex(attributes[i].getBytes());
            Element rou = Zr.newElementFromHash(attributes_md5.getBytes(),0,attributes_md5.length()).getImmutable();
            Element C3_t = rou.mul(s_i[i]).div(s_1.mul(HKW)).getImmutable();
            C3_1[i] = s_i[i].div(s_11.mul(HKW)).toBytes();
            C3_Map C3_T = new C3_Map();
            C3_T.setC3(C3_t.toBytes());
            C3_T.setAttr(attributes[i]);
            C3[i] = C3_T;
        }

        Element Y = GT.newElementFromBytes(pp.getY()).getImmutable();
        Element Y0 = GT.newElementFromBytes(pp.getY0()).getImmutable();
        Element g = G1.newElementFromBytes(pp.getG()).getImmutable();
        Element h = G1.newElementFromBytes(pp.getH()).getImmutable();

        Element C0 = gamma.mul(Y.powZn(s)).getImmutable();
        Element C1 = g.powZn(s).getImmutable();
        Element C2 = h.powZn(s).getImmutable();
        Element C4 = Y0.powZn(HKW).mul(Y.powZn(s.div(HKW))).getImmutable();

        CT ct = new CT();
        ct.setC0(C0.toBytes());
        ct.setC1(C1.toBytes());
        ct.setC2(C2.toBytes());
        ct.setC3(C3);
        ct.setC3_1(C3_1);
        ct.setC4(C4.toBytes());
        ct.setCm(CM);

        Serial serial = new Serial();
        byte[] ct_b = serial.serial(ct);
        byte[] lsss_b = serial.serial(lsss);

        UploadFile uploadFile = new UploadFile();
        uploadFile.setCt(ct_b);
        uploadFile.setLsss(lsss_b);
        uploadFile.setKw(KW);

        mapper.insert(uploadFile);
    }

    @Override
    public List getFile(String KW) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("kw", KW);
        List<UploadFile> list = mapper.selectList(wrapper);
        return list;
    }

}
