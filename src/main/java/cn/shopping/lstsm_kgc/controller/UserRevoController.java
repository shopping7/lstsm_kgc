package cn.shopping.lstsm_kgc.controller;


import cn.shopping.lstsm_kgc.config.lsss.LSSSEngine;
import cn.shopping.lstsm_kgc.config.lsss.LSSSMatrix;
import cn.shopping.lstsm_kgc.domain.ApiResult;
import cn.shopping.lstsm_kgc.domain.ApiResultUtil;
import cn.shopping.lstsm_kgc.entity.PP;
import cn.shopping.lstsm_kgc.entity.SK;
import cn.shopping.lstsm_kgc.service.UserRevoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

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
    UserRevoService userRevoService;

    @RequestMapping("/trance")
    public ApiResult Trance(MultipartFile file, HttpServletRequest request) throws ServletException, IOException {
        if (file.isEmpty()) {
            ApiResultUtil.errorAuthorized("无文件");
        }
        InputStream in = file.getInputStream();
        ObjectInputStream is = new ObjectInputStream(in);
        try {
            SK sk = (SK) is.readObject();
            userRevoService.User_revo(sk);
            return ApiResultUtil.success();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
