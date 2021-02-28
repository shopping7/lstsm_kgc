package cn.shopping.lstsm_kgc.service.impl;

import cn.shopping.lstsm_kgc.config.CrytptpFile;
import cn.shopping.lstsm_kgc.entity.CTout;
import cn.shopping.lstsm_kgc.config.DoublePairing;
import cn.shopping.lstsm_kgc.entity.SK;
import cn.shopping.lstsm_kgc.entity.Tkw;
import cn.shopping.lstsm_kgc.service.TrapdoorService;
import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Field;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class TrapdoorServiceImpl implements TrapdoorService {


    private Element u;

    public Tkw Trapdoor(SK sk, String KW){
        Field Zr = DoublePairing.Zr;
        Element D2 = Zr.newElementFromBytes(sk.getD2()).getImmutable();
        Element D4 = Zr.newElementFromBytes(sk.getD4()).getImmutable();
        Element u_1 = Zr.newElementFromBytes(sk.getU_1()).getImmutable();
        Element u_11 = Zr.newElementFromBytes(sk.getU_11()).getImmutable();
        Element u_111 = Zr.newElementFromBytes(sk.getU_111()).getImmutable();

        String kw_t_md5 = DigestUtils.md5DigestAsHex(KW.getBytes());
        Element HKW_t = Zr.newElementFromHash(kw_t_md5.getBytes(), 0, kw_t_md5.length()).getImmutable();

        u = Zr.newRandomElement().getImmutable();
        Element u0 = Zr.newRandomElement().getImmutable();

        Element T0 = u.mul(u_1.invert()).getImmutable();
        Element T1 = u0.div(u_1.mul(HKW_t)).getImmutable();
        Element T2 = D2.getImmutable();
        Element T3 = u0.mul(u_11.invert()).getImmutable();
        Element T3_1 = u.mul(HKW_t).mul(u_11.invert()).getImmutable();
        Element T4 = u0.mul(D4).getImmutable();
        Element T5 = u0.mul(D4).mul(HKW_t).mul(u_111.invert()).getImmutable();

        Tkw Tkw = new Tkw();
        Tkw.setT0(T0);
        Tkw.setT1(T1);
        Tkw.setT2(T2);
        Tkw.setT3(T3);
        Tkw.setT3_1(T3_1);
        Tkw.setT4(T4);
        Tkw.setT5(T5);
        return Tkw;
    }

    public byte[] Dec(CTout CTout, SK sk){
        Field Zr = DoublePairing.Zr;
        Field GT = DoublePairing.GT;
        Field K = DoublePairing.K;
        Element C0 = CTout.getC0().getImmutable();
        Element L1 = CTout.getL1().getImmutable();
        Element V1 = CTout.getV1().getImmutable();
        byte[] CM = CTout.getCm();
        Element D4 = Zr.newElementFromBytes(sk.getD4()).getImmutable();
        Element gamma_verify = C0.div((L1.mul(V1)).powZn(u.mul(D4).invert())).getImmutable();
        String kse_v = DigestUtils.md5DigestAsHex(gamma_verify.toBytes());
        Element kse_verify = K.newElementFromBytes(kse_v.getBytes()).getImmutable();
        try {
            byte[] bytes = CrytptpFile.SDec(CM, kse_verify.toBytes());
            return bytes;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
