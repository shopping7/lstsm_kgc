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
        List<UserVO> allUsers = userService.getAllUsers();
        return ApiResultUtil.successReturn(allUsers);
    }

    @RequestMapping("/addUser")
    public ApiResult addUser(@RequestBody UserVO user, HttpServletRequest request){
        String username = user.getUsername();
        List<String> user_attr = user.getAttr();
        HttpSession session = request.getSession();
        PP pp = (PP)session.getAttribute("pp");
        MSK msk = (MSK)session.getAttribute("msk");
        User newUser = new User();
        if(username !=null && user_attr != null){
            newUser.setUsername(username);
            userService.addUser(newUser);
            for(String attr : user_attr){
                userAttrService.addUserAttr(username,attr);
            }
        }
        String[] userAttr = user_attr.toArray(new String[user_attr.size()]);
        userKeyService.KeyGen(pp, msk, username, userAttr,true);
        return ApiResultUtil.success();
    }

    @RequestMapping("/deleteUser")
    public ApiResult deleteUser(@RequestBody UserVO user, HttpServletRequest request){
        String username = user.getUsername();
        HttpSession session = request.getSession();

        return ApiResultUtil.success();
    }
}
