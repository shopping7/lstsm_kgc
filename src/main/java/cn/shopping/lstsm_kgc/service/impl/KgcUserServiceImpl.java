package cn.shopping.lstsm_kgc.service.impl;

import cn.shopping.lstsm_kgc.entity.KgcUser;
import cn.shopping.lstsm_kgc.entity.User;
import cn.shopping.lstsm_kgc.mapper.KgcUserMapper;
import cn.shopping.lstsm_kgc.service.KgcUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class KgcUserServiceImpl extends ServiceImpl<KgcUserMapper, KgcUser> implements KgcUserService {

    @Autowired
    KgcUserMapper mapper;


}
