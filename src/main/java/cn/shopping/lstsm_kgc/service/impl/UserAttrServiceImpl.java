package cn.shopping.lstsm_kgc.service.impl;

import cn.shopping.lstsm_kgc.entity.User;
import cn.shopping.lstsm_kgc.mapper.UserAttrMapper;
import cn.shopping.lstsm_kgc.service.UserAttrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAttrServiceImpl implements UserAttrService {
    @Autowired
    UserAttrMapper mapper;

    @Override
    public List<User> getAllUser() {
        List<User> allUser = mapper.getAllUser();
        return allUser;
    }

    @Override
    public void addUser(String username, boolean sex, String email, String phone) {
        mapper.addUser(username, sex, email, phone);
    }

    @Override
    public List<String> getUserAttr(String username) {
        return mapper.getUserAttr(username);
    }

    @Override
    public void DeleteUserAttr(String username) {

        mapper.deleteUserAttr(username);
    }

    @Override
    public void addUserAttr(String username, String attr) {
        mapper.addUserAttr(username,attr);
    }
}
