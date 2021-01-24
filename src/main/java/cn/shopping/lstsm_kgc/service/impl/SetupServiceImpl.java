package cn.shopping.lstsm_kgc.service.impl;

import cn.shopping.lstsm_kgc.config.Serial;
import cn.shopping.lstsm_kgc.entity.MSK;
import cn.shopping.lstsm_kgc.entity.PKAndSK;
import cn.shopping.lstsm_kgc.entity.PP;
import cn.shopping.lstsm_kgc.entity.Ppandmsk;
import cn.shopping.lstsm_kgc.mapper.SetupMapper;
import cn.shopping.lstsm_kgc.service.SetupService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Field;
import it.unisa.dia.gas.jpbc.Pairing;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 公众号：java思维导图
 * @since 2021-01-24
 */
@Service
public class SetupServiceImpl extends ServiceImpl<SetupMapper, Ppandmsk> implements SetupService {
    private Pairing pairing;
    public static Field G1, GT, Zr, K;

    @Autowired
    SetupMapper mapper;

    @Override
    public void Setup() {
        Element g, f, Y, Y0, h;
        pairing = PairingFactory.getPairing("application.properties");
        PairingFactory.getInstance().setUsePBCWhenPossible(true);
        if(!pairing.isSymmetric()){
            throw new RuntimeException("密钥不对称");
        }

        Zr = pairing.getZr();
        G1 = pairing.getG1();
        K = pairing.getG2();
        GT = pairing.getGT();

        g = G1.newRandomElement().getImmutable();
        Element alpha = Zr.newRandomElement().getImmutable();
        Element lambda = Zr.newRandomElement().getImmutable();
        Element tau = Zr.newRandomElement().getImmutable();

        Element k1 = K.newRandomElement().getImmutable();
        Element k2 = K.newRandomElement().getImmutable();

        f = g.powZn(tau).getImmutable();
        Y = pairing.pairing(g, g).powZn(alpha).getImmutable();
        Y0 = pairing.pairing(g, f).getImmutable();
        h = g.powZn(lambda).getImmutable();

        MSK msk = new MSK();
        msk.setAlpha(alpha.toBytes());
        msk.setLambda(lambda.toBytes());
        msk.setTau(tau.toBytes());
        msk.setK1(k1.toBytes());
        msk.setK2(k2.toBytes());

        PP pp = new PP();
        pp.setG(g.toBytes());
        pp.setH(h.toBytes());
        pp.setF(f.toBytes());
        pp.setY(Y.toBytes());
        pp.setY0(Y0.toBytes());

        Serial serial = new Serial();
        byte[] msk_b = serial.serial(msk);
        byte[] pp_b = serial.serial(pp);

        Ppandmsk ppAndMsk = new Ppandmsk();
        ppAndMsk.setPp(pp_b);
        ppAndMsk.setMsk(msk_b);

        mapper.insert(ppAndMsk);
    }

    @Override
    public Ppandmsk getPpMsk() {
        return mapper.selectOne(null);
    }

    @Override
    public PKAndSK KenGen(MSK msk, String id, String[] attributes) {
        return null;
    }
}
