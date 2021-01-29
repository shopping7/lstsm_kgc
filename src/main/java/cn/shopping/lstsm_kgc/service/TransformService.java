package cn.shopping.lstsm_kgc.service;

import cn.shopping.lstsm_kgc.config.lsss.LSSSMatrix;
import cn.shopping.lstsm_kgc.entity.CT;
import cn.shopping.lstsm_kgc.entity.CTout;
import cn.shopping.lstsm_kgc.entity.PK;
import cn.shopping.lstsm_kgc.entity.Tkw;
import it.unisa.dia.gas.jpbc.Element;

public interface TransformService {

    public CTout Transform(CT ct, Tkw Tkw, PK pk, LSSSMatrix lsssD1,  int lsssIndex[]);

}
