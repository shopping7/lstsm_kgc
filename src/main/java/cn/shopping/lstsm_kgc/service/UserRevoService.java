package cn.shopping.lstsm_kgc.service;

import cn.shopping.lstsm_kgc.entity.MSK;
import cn.shopping.lstsm_kgc.entity.PP;
import cn.shopping.lstsm_kgc.entity.SK;
import cn.shopping.lstsm_kgc.entity.UserRevo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 公众号：java思维导图
 * @since 2021-01-30
 */
public interface UserRevoService extends IService<UserRevo> {
    public String Trace(PP pp, MSK msk, SK sk);

    public void User_revo(String username, SK sk);

    public List getAllBlacks();
}
