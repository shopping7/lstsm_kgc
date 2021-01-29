package cn.shopping.lstsm_kgc.service;

import cn.shopping.lstsm_kgc.config.lsss.LSSSMatrix;
import cn.shopping.lstsm_kgc.entity.PP;
import cn.shopping.lstsm_kgc.entity.SK;
import cn.shopping.lstsm_kgc.entity.Tkw;
import cn.shopping.lstsm_kgc.entity.UploadFile;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 公众号：java思维导图
 * @since 2021-01-28
 */
public interface UploadFileService extends IService<UploadFile> {

    public void Enc(PP pp, SK sk, String msg, LSSSMatrix lsss, String KW);

    public List getFile(String KW);
}
