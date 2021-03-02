package cn.shopping.lstsm_kgc.controller;


import cn.shopping.lstsm_kgc.config.Serial;
import cn.shopping.lstsm_kgc.domain.ApiResult;
import cn.shopping.lstsm_kgc.domain.ApiResultUtil;
import cn.shopping.lstsm_kgc.domain.UserVO;
import cn.shopping.lstsm_kgc.entity.*;
import cn.shopping.lstsm_kgc.service.UserKeyService;
import cn.shopping.lstsm_kgc.service.UserRevoService;
import cn.shopping.lstsm_kgc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.text.SimpleDateFormat;
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
public class UserRevoController {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    UserService userService;

    @Autowired
    UserKeyService userKeyService;

    @Autowired
    UserRevoService userRevoService;

    @RequestMapping("/trance")
    public ApiResult Trance(MultipartFile file, HttpServletRequest request) throws ServletException, IOException {
        if (file.isEmpty()) {
            ApiResultUtil.errorAuthorized("无文件");
        }
        InputStream in = file.getInputStream();
        ObjectInputStream is = new ObjectInputStream(in);
        HttpSession session = request.getSession();
        PP pp = (PP)session.getAttribute("pp");
        MSK msk = (MSK)session.getAttribute("msk");
        try {
            SK sk = (SK) is.readObject();
            String trance = userRevoService.Trace(pp, msk, sk);
            UserVO user = userService.getOneUser(trance);

            return ApiResultUtil.successReturn(user);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/addBlack")
    public ApiResult addBlack(@RequestBody User user, HttpServletRequest request) {
        String username = user.getUsername();
        System.out.println(username);
        UserKey userKey = userKeyService.getUserKey(username);
        System.out.println(userKey);
        Serial serial = new Serial();
        SK sk = (SK)serial.deserial(userKey.getSk());
        userRevoService.User_revo(username,sk);
        return ApiResultUtil.success();
    }

    @RequestMapping("/deleteBlack")
    public ApiResult deleteBlack(@RequestBody User user, HttpServletRequest request) {
        String username = user.getUsername();
        userRevoService.black_delete(username);
        return ApiResultUtil.success();
    }

    @RequestMapping("/getAllBlacks")
    public ApiResult getAllBlacks(HttpServletRequest request) {
        List allBlacks = userRevoService.getAllBlacks();
        return ApiResultUtil.successReturn(allBlacks);
    }
}
