package cn.shopping.lstsm_kgc.service;

import cn.shopping.lstsm_kgc.entity.MSK;
import cn.shopping.lstsm_kgc.entity.PKAndSK;
import cn.shopping.lstsm_kgc.entity.Ppandmsk;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 公众号：java思维导图
 * @since 2021-01-24
 */
public interface SetupService extends IService<Ppandmsk> {
    public void Setup();

    public Ppandmsk getPpMsk();

    public PKAndSK KenGen(MSK msk, String id, String attributes[]);
}
