package cn.shopping.lstsm_kgc.domain;

import lombok.Data;

import java.util.List;

@Data
public class UserVO {
    private String username;

    private Boolean sex;

    private String email;

    private String phone;

    private List<String> attr;
}
