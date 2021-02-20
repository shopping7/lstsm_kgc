package cn.shopping.lstsm_kgc.service.impl;

import cn.shopping.lstsm_kgc.entity.Attr;
import cn.shopping.lstsm_kgc.mapper.AttrMapper;
import cn.shopping.lstsm_kgc.service.AttrService;
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
 * @since 2021-02-08
 */
@Service
public class AttrServiceImpl extends ServiceImpl<AttrMapper, Attr> implements AttrService {

    @Autowired
    AttrMapper mapper;

    @Override
    public List<Attr> getAllAttr() {
        return mapper.selectList(null);
    }

    @Override
    public void deleteAttr(String attr) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("attr", attr);
        mapper.delete(wrapper);
    }

    @Override
    public void addAttr(Attr attr) {
        mapper.insert(attr);
    }
}
