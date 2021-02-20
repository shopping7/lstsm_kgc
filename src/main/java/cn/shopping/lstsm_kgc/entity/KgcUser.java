package cn.shopping.lstsm_kgc.entity;

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
 * @since 2021-01-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class KgcUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;

    private String password;

    private Boolean sex;

    private String email;

    private String phone;


}
