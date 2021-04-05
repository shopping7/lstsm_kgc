package cn.shopping.lstsm_kgc.mapper;

import cn.shopping.lstsm_kgc.domain.UserVO;
import cn.shopping.lstsm_kgc.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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

    @Select("SELECT COUNT(*) FROM USER WHERE username = #{username} AND PASSWORD=#{password}")
    public boolean correctPwd(String username, String password);

    @Update("UPDATE USER SET PASSWORD=#{password} WHERE username=#{username}")
    public void editPwd(String username, String password);

    @Update("UPDATE USER SET sex = #{sex} , email = #{email} , phone = #{phone} WHERE username = #{username}")
    public void editProfile(String username, boolean sex, String email, String phone);
}
