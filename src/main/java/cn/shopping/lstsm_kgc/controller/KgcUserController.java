package cn.shopping.lstsm_kgc.controller;


import cn.shopping.lstsm_kgc.domain.ApiResult;
import cn.shopping.lstsm_kgc.domain.ApiResultUtil;
import cn.shopping.lstsm_kgc.entity.User;
import cn.shopping.lstsm_kgc.service.KgcUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 公众号：java思维导图
 * @since 2021-01-30
 */
@RestController
public class KgcUserController {
    @Autowired
    KgcUserService service;


}
