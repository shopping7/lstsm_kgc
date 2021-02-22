package cn.shopping.lstsm_kgc.entity;

import java.sql.Blob;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author 公众号：java思维导图
 * @since 2021-02-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserRevo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;

    private byte[] delta;
}
