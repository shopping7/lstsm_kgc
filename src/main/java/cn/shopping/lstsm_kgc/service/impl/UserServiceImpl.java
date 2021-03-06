package cn.shopping.lstsm_kgc.service.impl;

import cn.shopping.lstsm_kgc.domain.UserVO;
import cn.shopping.lstsm_kgc.entity.User;
import cn.shopping.lstsm_kgc.mapper.UserMapper;
import cn.shopping.lstsm_kgc.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    UserMapper mapper;


    @Override
    public List<UserVO> getAllUsers() {
        return mapper.getAllUsers();
    }

    @Override
    public void addUser(User user) {
        mapper.insert(user);
    }

    @Override
    public void deleteUser(String username) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("username",username);
        mapper.delete(queryWrapper);
    }

    @Override
    public UserVO loginUser(String username, String password) {
        return mapper.loginUser(username,password);
    }

    @Override
    public UserVO getOneUser(String username) {
        return mapper.getOneUser(username);
    }

    @Override
    public boolean correctPwd(String username, String password) {
        return mapper.correctPwd(username,password);
    }

    @Override
    public void editPwd(String username, String password) {
        mapper.editPwd(username,password);
    }

    @Override
    public void editProfile(String username, boolean sex, String email, String phone) {
        mapper.editProfile(username, sex, email, phone);
    }

}
