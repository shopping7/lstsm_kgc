package cn.shopping.lstsm_kgc.service.impl;

import cn.shopping.lstsm_kgc.config.Crytpto;
import cn.shopping.lstsm_kgc.config.Serial;
import cn.shopping.lstsm_kgc.entity.*;
import cn.shopping.lstsm_kgc.mapper.UserKeyMapper;
import cn.shopping.lstsm_kgc.service.UserKeyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Field;
import org.apache.tomcat.jni.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Base64;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 公众号：java思维导图
 * @since 2021-01-27
 */
@Service
public class UserKeyServiceImpl extends ServiceImpl<UserKeyMapper, UserKey> implements UserKeyService {

    @Autowired
    UserKeyMapper mapper;

    @Override
    public void KeyGen(PP pp, MSK msk, String id, String[] attributes) {
        Field G1 = DoublePairing.G1;
        Field GT = DoublePairing.GT;
        Field K = DoublePairing.K;
        Field Zr = DoublePairing.Zr;

        Element g = G1.newElementFromBytes(pp.getG()).getImmutable();
        Element Y0 = GT.newElementFromBytes(pp.getY0()).getImmutable();
        Element f = G1.newElementFromBytes(pp.getF()).getImmutable();

        Element k1 = K.newElementFromBytes(msk.getK1()).getImmutable();;
        Element k2 = K.newElementFromBytes(msk.getK2()).getImmutable();;
        Element lambda = Zr.newElementFromBytes(msk.getLambda()).getImmutable();;
        Element alpha = Zr.newElementFromBytes(msk.getAlpha()).getImmutable();;
        Element tau = Zr.newElementFromBytes(msk.getTau()).getImmutable();;

        Element a = Zr.newRandomElement().getImmutable();
        Element r = Zr.newRandomElement().getImmutable();
        Element theta = Zr.newRandomElement().getImmutable();
        Element rho = Zr.newRandomElement().getImmutable();
        Element s_1 = Zr.newRandomElement().getImmutable();
        Element s_11 = Zr.newRandomElement().getImmutable();
        Element u_1 = Zr.newRandomElement().getImmutable();
        Element u_11 = Zr.newRandomElement().getImmutable();
        Element u_111 = Zr.newRandomElement().getImmutable();

        byte[] zeta_byte = Crytpto.SEnc(id.getBytes(), k1.toBytes());
        String zeta = Base64.getEncoder().encodeToString(zeta_byte);
        String delta_1 = zeta + theta.toString();
        byte[] delta_2 = Crytpto.SEnc(delta_1.getBytes(), k2.toBytes());
        String delta_s = Base64.getEncoder().encodeToString(delta_2);
        Element delta= Zr.newElementFromBytes(delta_2).getImmutable();
        Element D1 = g.powZn((alpha.sub(a.mul(r))).div((lambda.add(delta)))).getImmutable();
        Element D2 = delta.getImmutable();

        int k = attributes.length;
        D3_Map[] D3 = new D3_Map[k];
        Psi3_Map[] psi3 = new Psi3_Map[k];
        for (int i = 0; i < k; i++) {
            String attributes_md5 = DigestUtils.md5DigestAsHex(attributes[i].getBytes());
            Element xi = Zr.newElementFromHash(attributes_md5.getBytes(),0,attributes_md5.length()).getImmutable();
            Element D3_t = g.powZn(a.mul(r).mul(xi.add(tau).invert())).getImmutable();
            D3_Map d3_t = new D3_Map();
            d3_t.setAttr(attributes[i]);
            d3_t.setD3(D3_t.toBytes());
            D3[i] = d3_t;
            Element Psi3_t = (D3_t.powZn(rho)).powZn(u_11).getImmutable();
            Psi3_Map psi3_t = new Psi3_Map();
            psi3_t.setPsi3(Psi3_t.toBytes());
            psi3_t.setAttr(attributes[i]);
            psi3[i] = psi3_t;
        }

        Element D4 = rho.getImmutable();

        Element psi1 = D1.powZn(rho.mulZn(u_1)).getImmutable();
        Element psi2 = Y0.powZn(u_111).getImmutable();
        Element psi4 = g.powZn(s_1).getImmutable();
        Element psi5 = f.powZn(s_11).getImmutable();

        PK pk = new PK();
        pk.setPsi1(psi1.toBytes());
        pk.setPsi2(psi2.toBytes());
        pk.setPsi3(psi3);
        pk.setPsi4(psi4.toBytes());
        pk.setPsi5(psi5.toBytes());

        SK sk = new SK();
        sk.setD1(D1.toBytes());
        sk.setD2(D2.toBytes());
        sk.setD3(D3);
        sk.setD4(D4.toBytes());
        sk.setS_1(s_1.toBytes());
        sk.setS_11(s_11.toBytes());
        sk.setU_1(u_1.toBytes());
        sk.setU_11(u_11.toBytes());
        sk.setU_111(u_111.toBytes());
        sk.setDelta_s(delta_s);
        sk.setTheta(theta.toBytes());

        Serial serial = new Serial();
        byte[] pk_b = serial.serial(pk);
        byte[] sk_b = serial.serial(sk);

        UserKey userKey = new UserKey();
        userKey.setUserId(id);
        userKey.setPk(pk_b);
        userKey.setSk(sk_b);

        mapper.insert(userKey);
//        return userKey;
    }

    @Override
    public UserKey getUserKey(String id) {
        return mapper.selectById(id);
    }
}
