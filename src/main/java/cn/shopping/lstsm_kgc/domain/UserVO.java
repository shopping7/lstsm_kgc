package cn.shopping.lstsm_kgc.domain;

import cn.shopping.lstsm_kgc.entity.User;
import lombok.Data;

import java.util.List;

@Data
public class UserVO {
    private String username;

    private boolean sex;

    private String email;

    private String phone;

    private List<String> attr;
}
