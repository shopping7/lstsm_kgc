package cn.shopping.lstsm_kgc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface AttrMapper1 extends BaseMapper<String> {

    @Select("select attr from attr")
    List<String> getAllAttr();

    @Delete("delete attr from attr where attr = #{attr}")
    void deleteAttr(String attr);

    @Update("INSERT INTO attr (attr) VALUES (#{attr})")
    void addAttr(String attr);
}
