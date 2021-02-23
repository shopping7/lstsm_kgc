package cn.shopping.lstsm_kgc.mapper;

import cn.shopping.lstsm_kgc.domain.UserVO;
import cn.shopping.lstsm_kgc.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 公众号：java思维导图
 * @since 2021-01-30
 */
public interface UserMapper extends BaseMapper<User> {

    public List<UserVO> getAllUsers();

    public UserVO loginUser(String username, String password);

    public UserVO getOneUser(String username);
}
