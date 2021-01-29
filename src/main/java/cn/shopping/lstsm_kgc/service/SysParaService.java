package cn.shopping.lstsm_kgc.service;


import cn.shopping.lstsm_kgc.entity.SysPara;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 公众号：java思维导图
 * @since 2021-01-27
 */
public interface SysParaService extends IService<SysPara> {
    public void Setup();

    public SysPara getSysPara();
}
