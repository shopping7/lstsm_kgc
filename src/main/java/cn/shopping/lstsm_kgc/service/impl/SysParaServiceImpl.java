package cn.shopping.lstsm_kgc.service.impl;

import cn.shopping.lstsm_kgc.config.Serial;
import cn.shopping.lstsm_kgc.entity.*;
import cn.shopping.lstsm_kgc.mapper.SysParaMapper;
import cn.shopping.lstsm_kgc.service.SysParaService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Field;
import it.unisa.dia.gas.jpbc.Pairing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 公众号：java思维导图
 * @since 2021-01-27
 */
@Service
public class SysParaServiceImpl extends ServiceImpl<SysParaMapper, SysPara> implements SysParaService {
    @Autowired
    SysParaMapper mapper;

    @Override
    public void Setup() {
        Element g, f, Y, Y0, h;
        Pairing pairing = DoublePairing.pairing;
        Field G1 = DoublePairing.G1;
        Field Zr = DoublePairing.Zr;
        Field K = DoublePairing.K;

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

        SysPara sysPara = new SysPara();
        sysPara.setPp(pp_b);
        sysPara.setMsk(msk_b);

        mapper.insert(sysPara);
    }

    @Override
    public SysPara getSysPara() {
        SysPara sysPara = mapper.selectOne(null);
        return sysPara;
    }
}
