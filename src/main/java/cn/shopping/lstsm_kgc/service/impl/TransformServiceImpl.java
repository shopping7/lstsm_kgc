package cn.shopping.lstsm_kgc.service.impl;

import cn.shopping.lstsm_kgc.config.DoublePairing;
import cn.shopping.lstsm_kgc.config.lsss.LSSSMatrix;
import cn.shopping.lstsm_kgc.config.lsss.Vector;
import cn.shopping.lstsm_kgc.entity.*;
import cn.shopping.lstsm_kgc.service.TransformService;
import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Field;
import it.unisa.dia.gas.jpbc.Pairing;
import org.springframework.stereotype.Service;

@Service
public class TransformServiceImpl implements TransformService {

    public CTout Transform(CT ct, Tkw Tkw, PK pk, LSSSMatrix lsssD1, int lsssIndex[]) {
        Pairing pairing = DoublePairing.pairing;
        Field G1 = DoublePairing.G1;
        Field GT = DoublePairing.GT;
        Field Zr = DoublePairing.Zr;
        Element psi1 = G1.newElementFromBytes(pk.getPsi1()).getImmutable();
        Element psi2 = GT.newElementFromBytes(pk.getPsi2()).getImmutable();
        Psi3_Map[] psi3 = pk.getPsi3();
//        Psi3_Map[] psi3 = G1.newElementFromBytes(pk.getPsi3());
        Element psi4 = G1.newElementFromBytes(pk.getPsi4()).getImmutable();
        Element psi5 = G1.newElementFromBytes(pk.getPsi5()).getImmutable();
        Element C1 = G1.newElementFromBytes(ct.getC1()).getImmutable();
        Element C2 = G1.newElementFromBytes(ct.getC2()).getImmutable();
        C3_Map[] C3 = ct.getC3();
        byte[][] C3_1_b = ct.getC3_1();
        Element C3_1[] = new Element[C3_1_b.length];
        for (int i = 0; i < C3_1.length; i++) {
            C3_1[i] = Zr.newElementFromBytes(C3_1_b[i]).getImmutable();
        }
        Element C4 = GT.newElementFromBytes(ct.getC4()).getImmutable();
        Element T1 = Tkw.getT1().getImmutable();
        Element T2 = Tkw.getT2().getImmutable();
        Element T3 = Tkw.getT3().getImmutable();
        Element T4 = Tkw.getT4().getImmutable();
        Element T5 = Tkw.getT5().getImmutable();
        Vector w_v = lsssD1.getRv();
        if (w_v == null) {
            System.out.println("不符合要求");
            System.exit(0);
        }
        String[] user_attr = lsssD1.getMap();
        Element sum = G1.newElement().setToOne().getImmutable();
        Element sum2 = G1.newElement().setToOne().getImmutable();

        for (int i = 0; i < psi3.length; i++) {
            Element w = Zr.newElement(w_v.getValue(i)).getImmutable();
            for (int j = 0; j < C3.length; j++) {
                if((psi3[i].getAttr()).equals(C3[j].getAttr())){
                    Element psi3_t = G1.newElementFromBytes(psi3[i].getPsi3()).getImmutable();
                    Element C3_t = Zr.newElementFromBytes(C3[j].getC3()).getImmutable();
                    sum = sum.mul(psi3_t.powZn(C3_t.mul(w))).getImmutable();
                    sum2 = sum2.mul(psi3_t.powZn(C3_1[j].mul(w))).getImmutable();
                }
            }
        }

        Element V = pairing.pairing(psi4, sum).mul(pairing.pairing(psi5, sum2)).getImmutable();
        //Element V_verify = pairing.pairing(g,g).powZn((a.mul(r).mul(rho).mul(u_11).mul(s)).div(HKW_t));


        Element L = pairing.pairing(psi1, C1.powZn(T2).mul(C2)).getImmutable();
        // L_verify = pairing.pairing(g,g).powZn((alpha.sub(a.mul(r))).mul(rho).mul(u_1).mul(s)).getImmutable();

        Element L2 = L.powZn(T1).getImmutable();
        // L2_verify = pairing.pairing(g,g).powZn((alpha.sub(a.mul(r))).mul(rho).mul(u0).mul(s).div(HKW_t)).getImmutable();


        Element V2 = V.powZn(T3).getImmutable();
        //V2_verify = pairing.pairing(g,g).powZn((a.mul(r).mul(rho).mul(u0).mul(s).div(HKW_t)));


        Element LeftEq = (psi2.powZn(T5)).mul(L2).mul(V2).getImmutable();
        Element RightEq = C4.powZn(T4).getImmutable();
        //RightEq_verify = (pairing.pairing(g,f).powZn(HKW_t.mul(u0).mul(rho))).mul(pairing.pairing(g,g).powZn(alpha.mul(s).mul(u0).mul(rho).div(HKW_t))).getImmutable();


        if (LeftEq.isEqual(RightEq)) {
            System.out.println("KW matched successfully,run next method");
            Element T0 = Tkw.getT0().getImmutable();
            Element T3_1 = Tkw.getT3_1().getImmutable();

            Element L1 = L.powZn(T0).getImmutable();
            // L1_verify = pairing.pairing(g,g).powZn((alpha.sub(a.mul(r))).mul(rho).mul(u).mul(s)).getImmutable();

            Element V1 = V.powZn(T3_1).getImmutable();
            //  V1_verify = pairing.pairing(g,g).powZn((a.mul(r).mul(rho).mul(u).mul(s)));
            Element C0 = GT.newElementFromBytes(ct.getC0()).getImmutable();
            CTout CTout = new CTout();
            CTout.setC0(C0);
            CTout.setL1(L1);
            CTout.setV1(V1);
            CTout.setCm(ct.getCm());
            return CTout;
        } else {
            System.out.println("KW matched losely,please search another file");
            return null;
        }
    }
}
