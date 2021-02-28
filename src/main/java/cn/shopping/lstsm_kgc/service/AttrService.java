package cn.shopping.lstsm_kgc.service;

import cn.shopping.lstsm_kgc.entity.Attr;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 公众号：java思维导图
 * @since 2021-02-08
 */
public interface AttrService extends IService<Attr> {
    public List<Attr> getAllAttr();

    public void deleteAttr(String attr);

    public void addAttr(Attr attr);

    public void editAttr(Attr attr);
}
