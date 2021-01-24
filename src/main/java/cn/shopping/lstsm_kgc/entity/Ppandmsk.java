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
 * @since 2021-01-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Ppandmsk implements Serializable {

    private static final long serialVersionUID = 1L;

    private byte[] pp;

    private byte[] msk;


}
