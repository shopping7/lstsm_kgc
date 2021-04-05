package cn.shopping.lstsm_kgc.controller;

import cn.shopping.lstsm_kgc.config.Serial;
import cn.shopping.lstsm_kgc.domain.ApiResult;
import cn.shopping.lstsm_kgc.domain.ApiResultUtil;
import cn.shopping.lstsm_kgc.domain.LoginVO;
import cn.shopping.lstsm_kgc.domain.UserVO;
import cn.shopping.lstsm_kgc.config.DoublePairing;
import cn.shopping.lstsm_kgc.entity.SysPara;
import cn.shopping.lstsm_kgc.entity.User;
import cn.shopping.lstsm_kgc.entity.UserKey;
import cn.shopping.lstsm_kgc.service.SysParaService;
import cn.shopping.lstsm_kgc.service.UserAttrService;
import cn.shopping.lstsm_kgc.service.UserKeyService;
import cn.shopping.lstsm_kgc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;

@RestController
public class LoginController {
    @Autowired
    UserService userService;

    @Autowired
    UserAttrService userAttrService;

    @Autowired
    SysParaService sysParaService;

    @Autowired
    UserKeyService userKeyService;

    @RequestMapping("/user/login")
    public ApiResult login(@RequestBody LoginVO login, HttpServletRequest request) {
        System.out.println(login.getUsername());
        UserVO loginUser = userService.loginUser(login.getUsername(),login.getPassword());
        HttpSession session = request.getSession();
        session.setAttribute("loginUser",loginUser);
        if (loginUser != null) {
            //获得系统参数
            DoublePairing doublePairing = new DoublePairing();
            doublePairing.getStart();
            SysPara sysPara = sysParaService.getSysPara();
            Serial serial = new Serial();
            session.setAttribute("pp",serial.deserial(sysPara.getPp()));
            session.setAttribute("msk",serial.deserial(sysPara.getMsk()));
            System.out.println(sysPara);
            //获得用户密钥
            System.out.println(loginUser.getUsername());
            UserKey userKey = userKeyService.getUserKey(loginUser.getUsername());
            System.out.println(userKey);
            session.setAttribute("pk",serial.deserial(userKey.getPk()));
            session.setAttribute("sk",serial.deserial(userKey.getSk()));

            String token = UUID.randomUUID().toString();
            session.setAttribute("token", token);

            return ApiResultUtil.successReturn(loginUser);
        } else {
            return ApiResultUtil.errorAuthorized("用户名或密码错误");
        }
    }




    @RequestMapping("/user/editPwd")
    public ApiResult editPwd(HttpServletRequest request, HttpServletResponse response) {
        String oldPwd = request.getParameter("oldPwd");
        String newPwd = request.getParameter("newPwd");
        HttpSession session = request.getSession();
        UserVO user = (UserVO)session.getAttribute("loginUser");
        String username = user.getUsername();
        if (user != null) {
            if(userService.correctPwd(username, oldPwd)){
                userService.editPwd(username, newPwd);
                return ApiResultUtil.success();
            }
        }
        return ApiResultUtil.errorAuthorized("修改用户信息错误");
    }
}
