package cn.shopping.lstsm_kgc.domain;

import cn.shopping.lstsm_kgc.entity.User;
import lombok.Data;

import java.util.List;

@Data
public class UserVO {
    private User user;

    private List<String> attr;
}
