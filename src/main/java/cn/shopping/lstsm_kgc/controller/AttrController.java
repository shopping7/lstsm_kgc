package cn.shopping.lstsm_kgc.controller;


import cn.shopping.lstsm_kgc.domain.ApiResult;
import cn.shopping.lstsm_kgc.domain.ApiResultUtil;
import cn.shopping.lstsm_kgc.entity.Attr;
import cn.shopping.lstsm_kgc.service.AttrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 公众号：java思维导图
 * @since 2021-02-08
 */
@RestController
public class AttrController {
    @Autowired
    AttrService service;

    @RequestMapping("/attrs")
    public ApiResult getAllAttr(HttpServletRequest request, HttpServletResponse response){
        List<Attr> allAttr = service.getAllAttr();
        return ApiResultUtil.successReturn(allAttr);
    }

    @RequestMapping("/addAttr")
    public ApiResult addAttr(@RequestBody Attr attr,HttpServletRequest request){
        System.out.println(attr);
        service.addAttr(attr);
        return ApiResultUtil.success();
    }

    @RequestMapping("/deleteAttr")
    public ApiResult deleteAttr(@RequestBody Attr attr,HttpServletRequest request){
        service.deleteAttr(attr.getAttr());
        System.out.println(attr);
        return ApiResultUtil.success();
    }

    @RequestMapping("/editAttr")
    public ApiResult editAttr(@RequestBody Attr attr,HttpServletRequest request){
        service.editAttr(attr);
        System.out.println(attr);
        return ApiResultUtil.success();
    }
}

