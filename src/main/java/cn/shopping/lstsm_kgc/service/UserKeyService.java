package cn.shopping.lstsm_kgc.service;

import cn.shopping.lstsm_kgc.entity.MSK;
import cn.shopping.lstsm_kgc.entity.PP;
import cn.shopping.lstsm_kgc.entity.UserKey;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 公众号：java思维导图
 * @since 2021-01-27
 */
public interface UserKeyService extends IService<UserKey> {
    public void KeyGen(PP pp, MSK msk, String id, String attributes[]);

    public UserKey getUserKey(String id);
}
