package cn.shopping.lstsm_kgc.controller;


import cn.shopping.lstsm_kgc.domain.ApiResult;
import cn.shopping.lstsm_kgc.domain.ApiResultUtil;
import cn.shopping.lstsm_kgc.domain.LoginVO;
import cn.shopping.lstsm_kgc.domain.UserVO;
import cn.shopping.lstsm_kgc.entity.*;
import cn.shopping.lstsm_kgc.service.AttrService;
import cn.shopping.lstsm_kgc.service.UserAttrService;
import cn.shopping.lstsm_kgc.service.UserKeyService;
import org.apache.catalina.Session;
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

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 公众号：java思维导图
 * @since 2021-02-08
 */
@RestController
public class UserAttrController {
    @Autowired
    UserAttrService userAttrService;

    @Autowired
    UserKeyService userKeyService;

    @RequestMapping("/users")
    public ApiResult getAllUsers(HttpServletRequest request, HttpServletResponse response){
        List<User> allUser = userAttrService.getAllUser();
        Map<User,List> map = new HashMap<>();
        for (User user: allUser) {
            List<String> userAttr = userAttrService.getUserAttr(user.getUsername());
            map.put(user,userAttr);
        }
        return ApiResultUtil.successReturn(map);
    }

    @RequestMapping("/addUser")
    public ApiResult addUser(@RequestBody UserVO user, HttpServletRequest request){
        String username = user.getUsername();
        HttpSession session = request.getSession();
        PP pp = (PP)session.getAttribute("pp");
        MSK msk = (MSK)session.getAttribute("msk");
        List<String> userAttrList = user.getAttr();
        String[] userAttr = userAttrList.toArray(new String[userAttrList.size()]);
        userAttrService.addUser(username,user.getSex(),user.getEmail(),user.getPhone());
        for(String attr : user.getAttr()){
            userAttrService.addUserAttr(username,attr);
        }
        userKeyService.KeyGen(pp, msk, username, userAttr,true);
        return ApiResultUtil.success();
    }

    @RequestMapping("/deleteUser")
    public ApiResult deleteUser(@RequestBody UserVO user, HttpServletRequest request){
        String username = user.getUsername();
        HttpSession session = request.getSession();

        return ApiResultUtil.success();
    }

    @RequestMapping("/userAttrEdit")
    public ApiResult userAttrEdit(@RequestBody List<String> attrs,HttpServletRequest request){
        String username = request.getParameter("username");
        System.out.println(username);
        System.out.println(attrs);
        userAttrService.DeleteUserAttr(username);
        for(String attr : attrs){
            userAttrService.addUserAttr(username,attr);
        }

        List<String> userAttrList = userAttrService.getUserAttr(username);
        String[] userAttr = userAttrList.toArray(new String[userAttrList.size()]);
        HttpSession session = request.getSession();
        PP pp = (PP)session.getAttribute("pp");
        MSK msk = (MSK)session.getAttribute("msk");
        userKeyService.KeyGen(pp, msk, username,userAttr,false);
        return ApiResultUtil.success();
    }

}

