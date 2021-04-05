package cn.shopping.lstsm_kgc.service;

import cn.shopping.lstsm_kgc.domain.UserVO;
import cn.shopping.lstsm_kgc.entity.User;
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
public interface UserService extends IService<User> {
    public List<UserVO> getAllUsers();

    public void addUser(User user);

    public void deleteUser(String username);

    public UserVO loginUser(String username, String password);

    public UserVO getOneUser(String username);

    public boolean correctPwd(String username, String password);

    public void editPwd(String username, String password);

    public void editProfile(String username, boolean sex, String email, String phone);
}
