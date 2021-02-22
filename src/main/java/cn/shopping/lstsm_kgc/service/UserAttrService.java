package cn.shopping.lstsm_kgc.service;

import cn.shopping.lstsm_kgc.entity.User;

import java.util.List;

/**
 * 用于KGC管理用户属性
 */
public interface UserAttrService {

    public List<String> getUserAttr(String username);

    public void DeleteUserAttr(String username);

    public void addUserAttr(String username, String attr);
}
