package cn.shopping.lstsm_kgc.mapper;

import cn.shopping.lstsm_kgc.domain.UserVO;
import cn.shopping.lstsm_kgc.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserAttrMapper extends BaseMapper {

    @Select("SELECT attr FROM user_attr u INNER JOIN attr a ON u.`attr_id` = a.`id` WHERE u.`username` = #{username}")
    List<String> getUserAttr(String username);

    @Update("INSERT INTO user_attr(username,attr_id) VALUES(#{username},(SELECT id FROM attr WHERE attr=#{attr}))")
    void addUserAttr(String username, String attr);

    @Update("DELETE FROM user_attr WHERE username=#{username}")
    void deleteUserAttr(String username);
}
