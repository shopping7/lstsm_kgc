package cn.shopping.lstsm_kgc.service.impl;

import cn.shopping.lstsm_kgc.entity.User;
import cn.shopping.lstsm_kgc.mapper.UserMapper;
import cn.shopping.lstsm_kgc.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 公众号：java思维导图
 * @since 2021-01-30
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    UserMapper mapper;



}
