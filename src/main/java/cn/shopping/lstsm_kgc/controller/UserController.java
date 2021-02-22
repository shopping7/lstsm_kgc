package cn.shopping.lstsm_kgc.controller;


import cn.shopping.lstsm_kgc.config.Serial;
import cn.shopping.lstsm_kgc.domain.ApiResult;
import cn.shopping.lstsm_kgc.domain.ApiResultUtil;
import cn.shopping.lstsm_kgc.domain.LoginVO;
import cn.shopping.lstsm_kgc.domain.UserVO;
import cn.shopping.lstsm_kgc.entity.*;
import cn.shopping.lstsm_kgc.service.SysParaService;
import cn.shopping.lstsm_kgc.service.UserAttrService;
import cn.shopping.lstsm_kgc.service.UserKeyService;
import cn.shopping.lstsm_kgc.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 */
@RestController
public class UserController {

    @Autowired
    UserAttrService userAttrService;

    @Autowired
    UserService userService;

    @Autowired
    UserKeyService userKeyService;

    @RequestMapping("/users")
    public ApiResult getAllUsers(HttpServletRequest request, HttpServletResponse response){
        List<User> allUser = userService.getAllUser();
        Map<User,List> map = new HashMap<>();
        for (User user: allUser) {
            List<String> userAttr = userAttrService.getUserAttr(user.getUsername());
            map.put(user,userAttr);
        }
        return ApiResultUtil.successReturn(map);
    }

    @RequestMapping("/addUser")
    public ApiResult addUser(@RequestBody UserVO user, HttpServletRequest request){
        String username = user.getUser().getUsername();
        HttpSession session = request.getSession();
        PP pp = (PP)session.getAttribute("pp");
        MSK msk = (MSK)session.getAttribute("msk");
        List<String> userAttrList = user.getAttr();
        String[] userAttr = userAttrList.toArray(new String[userAttrList.size()]);
        userService.addUser(user.getUser());
        for(String attr : user.getAttr()){
            userAttrService.addUserAttr(username,attr);
        }
        userKeyService.KeyGen(pp, msk, username, userAttr,true);
        return ApiResultUtil.success();
    }

}
