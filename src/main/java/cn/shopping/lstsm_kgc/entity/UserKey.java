package cn.shopping.lstsm_kgc.entity;

import java.sql.Blob;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author 公众号：java思维导图
 * @since 2021-01-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserKey implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private String userId;

    private byte[] pk;

    private byte[] sk;

}
