package cn.shopping.lstsm_kgc.controller;


import cn.shopping.lstsm_kgc.service.SetupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 公众号：java思维导图
 * @since 2021-01-24
 */
@RestController
@RequestMapping("/ppandmsk")
public class SetupController {

    @Autowired
    SetupService service;

    @GetMapping("/setup")
    public void index() {
        service.Setup();
    }
}
