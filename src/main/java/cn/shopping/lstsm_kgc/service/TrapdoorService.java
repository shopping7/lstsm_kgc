package cn.shopping.lstsm_kgc.service;

import cn.shopping.lstsm_kgc.entity.CTout;
import cn.shopping.lstsm_kgc.entity.SK;
import cn.shopping.lstsm_kgc.entity.Tkw;

public interface TrapdoorService {

    public Tkw Trapdoor(SK sk, String KW);

    public byte[] Dec(CTout CTout, SK sk);
}
