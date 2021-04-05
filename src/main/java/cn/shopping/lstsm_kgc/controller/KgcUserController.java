package cn.shopping.lstsm_kgc.controller;


import cn.shopping.lstsm_kgc.config.DoublePairing;
import cn.shopping.lstsm_kgc.config.Serial;
import cn.shopping.lstsm_kgc.domain.ApiResult;
import cn.shopping.lstsm_kgc.domain.ApiResultUtil;
import cn.shopping.lstsm_kgc.domain.LoginVO;
import cn.shopping.lstsm_kgc.entity.KgcUser;
import cn.shopping.lstsm_kgc.entity.SysPara;
import cn.shopping.lstsm_kgc.service.KgcUserService;
import cn.shopping.lstsm_kgc.service.SysParaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 *
 */
@RestController
public class KgcUserController {
    @Autowired
    KgcUserService kgcUserService;

    @Autowired
    SysParaService sysParaService;


    @RequestMapping("/kgc-user/login")
    public ApiResult login(@RequestBody LoginVO login, HttpServletRequest request) {
        KgcUser kgcUser = kgcUserService.loginUser(login.getUsername(), login.getPassword());
        if(kgcUser != null){
            HttpSession session = request.getSession();
            session.setAttribute("kgcUser",kgcUser);
            //获得系统参数
            DoublePairing doublePairing = new DoublePairing();
            doublePairing.getStart();
            SysPara sysPara = sysParaService.getSysPara();
            Serial serial = new Serial();
            session.setAttribute("pp",serial.deserial(sysPara.getPp()));
            session.setAttribute("msk",serial.deserial(sysPara.getMsk()));
            System.out.println(sysPara);
            return ApiResultUtil.successReturn(kgcUser);
        } else {
            return ApiResultUtil.errorAuthorized("用户名或密码错误");
        }
    }
}
