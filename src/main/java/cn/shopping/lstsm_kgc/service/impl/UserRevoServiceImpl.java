package cn.shopping.lstsm_kgc.service.impl;

import cn.shopping.lstsm_kgc.config.Crytpto;
import cn.shopping.lstsm_kgc.entity.*;
import cn.shopping.lstsm_kgc.mapper.UserRevoMapper;
import cn.shopping.lstsm_kgc.service.UserRevoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Field;
import it.unisa.dia.gas.jpbc.Pairing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Base64;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 公众号：java思维导图
 * @since 2021-01-30
 */
@Service
public class UserRevoServiceImpl extends ServiceImpl<UserRevoMapper, UserRevo> implements UserRevoService {

    @Autowired
    UserRevoMapper mapper;

    @Override
    public String Trace(PP pp, MSK msk, SK sk) {
        Pairing pairing = DoublePairing.pairing;
        Field G1 = DoublePairing.G1;
        Field GT = DoublePairing.GT;
        Field K = DoublePairing.K;
        Field Zr = DoublePairing.Zr;

        Element D1 = G1.newElementFromBytes(sk.getD1()).getImmutable();
        Element D2 = Zr.newElementFromBytes(sk.getD2()).getImmutable();
        D3_Map[] D3 = sk.getD3();
        Element D4 = Zr.newElementFromBytes(sk.getD4()).getImmutable();
        Element s_1 = Zr.newElementFromBytes(sk.getS_1()).getImmutable();
        Element s_11 = Zr.newElementFromBytes(sk.getS_11()).getImmutable();
        Element u_1 = Zr.newElementFromBytes(sk.getU_1()).getImmutable();
        Element u_11 = Zr.newElementFromBytes(sk.getU_11()).getImmutable();
        Element u_111 = Zr.newElementFromBytes(sk.getU_111()).getImmutable();
        int k_t = D3.length;
        Element sum = G1.newElement().setToOne().getImmutable();
        Element sum1 = G1.newElement().setToOne().getImmutable();
        Element k = Zr.newElement(k_t).getImmutable();
        for (int i = 0; i < k_t; i++) {
            String attributes_md5 = DigestUtils.md5DigestAsHex(D3[i].getAttr().getBytes());
            Element xi = Zr.newElementFromHash(attributes_md5.getBytes(),0,attributes_md5.length()).getImmutable();
            Element D3_t = G1.newElementFromBytes(D3[i].getD3()).getImmutable();
            sum = sum.mul(D3_t.powZn(xi)).getImmutable();
            sum1 = sum1.mul(D3_t).getImmutable();
        }

        Element h = G1.newElementFromBytes(pp.getH()).getImmutable();
        Element g = G1.newElementFromBytes(pp.getG()).getImmutable();
        Element f = G1.newElementFromBytes(pp.getF()).getImmutable();
        Element Y = GT.newElementFromBytes(pp.getY()).getImmutable();

        Element e1 = pairing.pairing(D1,(h.mul(g.powZn(D2)))).powZn(k).getImmutable();
        Element e2 = pairing.pairing(g,sum).getImmutable();
        Element e3 = pairing.pairing(f,sum1).getImmutable();
        Element left = e1.mul(e2).mul(e3).getImmutable();
        Element right = Y.powZn(k).getImmutable();
        if(left.equals(right)){
            Element k1 = K.newElementFromBytes(msk.getK1()).getImmutable();
            Element k2 = K.newElementFromBytes(msk.getK2()).getImmutable();
            String delta = sk.getDelta_s();
            Element theta = Zr.newElementFromBytes(sk.getTheta()).getImmutable();
            byte[] delta_2 = Base64.getDecoder().decode(delta);
            byte[] delta_m = Crytpto.SDec(delta_2, k2.toBytes());
            String theta_s = new String(delta_m);
            String zeta = theta_s.replace(theta.toString(),"");
            byte[] decoded = Base64.getDecoder().decode(zeta);
            byte[] id_t = Crytpto.SDec(decoded,k1.toBytes());
            String id_revo = new String(id_t);
            if(id_revo != null){
                return id_revo;
            }else{
                return null;
            }
        }else{
            return null;
        }
    }

    @Override
    public void User_revo(String username, SK sk) {
        UserRevo userRevo = new UserRevo();
        userRevo.setUsername(username);
        userRevo.setDelta(sk.getD2());
        mapper.insert(userRevo);
    }

    @Override
    public List getAllBlacks() {
        return mapper.selectList(null);
    }

}
