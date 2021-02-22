package cn.shopping.lstsm_kgc.controller;

import cn.shopping.lstsm_kgc.config.Serial;
import cn.shopping.lstsm_kgc.domain.ApiResult;
import cn.shopping.lstsm_kgc.domain.ApiResultUtil;
import cn.shopping.lstsm_kgc.domain.LoginVO;
import cn.shopping.lstsm_kgc.entity.DoublePairing;
import cn.shopping.lstsm_kgc.entity.SysPara;
import cn.shopping.lstsm_kgc.entity.User;
import cn.shopping.lstsm_kgc.entity.UserKey;
import cn.shopping.lstsm_kgc.service.SysParaService;
import cn.shopping.lstsm_kgc.service.UserAttrService;
import cn.shopping.lstsm_kgc.service.UserKeyService;
import cn.shopping.lstsm_kgc.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("username", login.getUsername());
        queryWrapper.eq("password", login.getPassword());
        System.out.println(11);
        User user = userService.getOne(queryWrapper);
        HttpSession session = request.getSession();
        session.setAttribute("loginUser",user);
        List userAttr = userAttrService.getUserAttr(user.getUsername());
        session.setAttribute("userAttr",userAttr);
        Map<User,List> map = new HashMap<>();
        map.put(user,userAttr);
        if (user != null) {
            //获得系统参数
            DoublePairing doublePairing = new DoublePairing();
            doublePairing.getStart();
            SysPara sysPara = sysParaService.getSysPara();
            Serial serial = new Serial();
            session.setAttribute("pp",serial.deserial(sysPara.getPp()));
            session.setAttribute("msk",serial.deserial(sysPara.getMsk()));
            System.out.println(sysPara);
            //获得用户密钥
            System.out.println(user.getUsername());
            UserKey userKey = userKeyService.getUserKey(user.getUsername());
            System.out.println(userKey);
            session.setAttribute("pk",serial.deserial(userKey.getPk()));
            session.setAttribute("sk",serial.deserial(userKey.getSk()));

            String token = UUID.randomUUID().toString();
            session.setAttribute("token", token);

            return ApiResultUtil.successReturn(map);
        } else {
            return ApiResultUtil.errorAuthorized("用户名或密码错误");
        }
    }

    @RequestMapping("/user/info")
    public ApiResult getUserInfo(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("loginUser");
        List userAttr = (List)session.getAttribute("userAttr");
        Map<User,List> map = new HashMap<>();
        map.put(user,userAttr);
        if (user != null) {
            System.out.println("success");
            return ApiResultUtil.successReturn(map);
        } else {
            return ApiResultUtil.errorAuthorized("获取用户信息错误");
        }
    }
}
