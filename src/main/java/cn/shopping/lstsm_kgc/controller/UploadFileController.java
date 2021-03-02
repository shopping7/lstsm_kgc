package cn.shopping.lstsm_kgc.controller;


import cn.shopping.lstsm_kgc.config.Serial;
import cn.shopping.lstsm_kgc.config.lsss.LSSSEngine;
import cn.shopping.lstsm_kgc.config.lsss.LSSSMatrix;
import cn.shopping.lstsm_kgc.domain.ApiResult;
import cn.shopping.lstsm_kgc.domain.ApiResultUtil;
import cn.shopping.lstsm_kgc.domain.UserVO;
import cn.shopping.lstsm_kgc.entity.*;
import cn.shopping.lstsm_kgc.service.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 */
@RestController
public class UploadFileController {

    @Autowired
    UserRevoService userRevoService;

    @Autowired
    TrapdoorService trapdoorService;

    @Autowired
    TransformService transformService;

    @Autowired
    UserAttrService userAttrService;

    @Autowired
    UploadFileService uploadFileService;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @PostMapping("/user/uploadFile")
    @ResponseBody
    public ApiResult upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        String policy = request.getParameter("policy");
        String keyword = request.getParameter("keyword");
        System.out.println(policy);
        System.out.println(keyword);
        if (file.isEmpty()) {
            ApiResultUtil.errorAuthorized("无文件");
        }

        String format = sdf.format(new Date());
        String realPath = request.getServletContext().getRealPath("/") + format;
        System.out.println(realPath);
        File folder = new File(realPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        String newName = UUID.randomUUID().toString() + ".txt";
        System.out.println(newName);
        File upload_file = new File(folder, newName);
        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/" + format + "/" + newName;
        System.out.println(url);
        try {
            file.transferTo(upload_file);
            HttpSession session = request.getSession();
            PP pp = (PP) session.getAttribute("pp");
            SK sk = (SK) session.getAttribute("sk");
            LSSSEngine engine = new LSSSEngine();
            LSSSMatrix lsss = engine.genMatrix(policy);
            System.out.println(pp);
            System.out.println(sk);
            System.out.println(upload_file);
            if ((pp != null) && (sk != null) && (upload_file != null)) {
                uploadFileService.Enc(pp, sk, upload_file, lsss, keyword);
            }
            return ApiResultUtil.success();
        } catch (IOException e) {
            e.printStackTrace();
            return ApiResultUtil.errorParam("上传失败");
        }
    }


    @RequestMapping("/user/getFile")
    public ApiResult getFile(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        String format = sdf.format(new Date());
        String realPath = request.getServletContext().getRealPath("/") + format;
        System.out.println(realPath);
        File folder = new File(realPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        String keyword = request.getParameter("keyword");
        System.out.println(keyword);
        if (file.isEmpty()) {
            ApiResultUtil.errorAuthorized("无文件");
        }
        InputStream in = file.getInputStream();
        ObjectInputStream is = new ObjectInputStream(in);

        HttpSession session = request.getSession();
        Serial serial = new Serial();
        List<String> fileUrl = new ArrayList<>();
        try {
            PK pk = (PK) session.getAttribute("pk");
            System.out.println(pk);
            SK sk = (SK) is.readObject();
            System.out.println(sk);
//            SK sk = (SK) session.getAttribute("sk");
//            System.out.println(sk);
            UserVO loginUser = (UserVO) session.getAttribute("loginUser");
            List userAttr = userAttrService.getUserAttr(loginUser.getUsername());
            String[] attrs = (String[]) userAttr.toArray(new String[userAttr.size()]);
            for (int i = 0; i < attrs.length; i++) {
                System.out.println(attrs[i]);
            }
            List<UploadFile> fileList = uploadFileService.getFile(keyword);
            if (fileList.size() > 0) {
                for (UploadFile key_file : fileList) {
                    CT ct = (CT) serial.deserial(key_file.getCt());
                    LSSSMatrix lsss = (LSSSMatrix) serial.deserial(key_file.getLsss());
                    LSSSMatrix lsssD1 = lsss.extract(attrs);
                    int lsssIndex[] = lsssD1.getIndex();
                    Tkw tkw = trapdoorService.Trapdoor(sk, keyword);
                    CTout ctout = transformService.Transform(ct, tkw, pk, lsssD1, lsssIndex);

                    if (ctout != null) {
                        System.out.println(123);
                        String newName = UUID.randomUUID().toString() + ".txt";
                        System.out.println(newName);
                        File sk_file = new File(folder, newName);
                        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/" + format + "/" + newName;
                        byte[] dec = trapdoorService.Dec(ctout, sk);
                        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(sk_file));
                        os.writeObject(dec);
                        os.close();
                        fileUrl.add(url);
                    }
                }
            } else {
                return ApiResultUtil.errorParam("无匹配文件");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if(fileUrl.size() > 0){
            return ApiResultUtil.successReturn(fileUrl);
        }else{
            return ApiResultUtil.errorParam("无权限解密");
        }

    }

    @RequestMapping("/user/getFile1")
    public ApiResult getFile1(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        HttpSession session = request.getSession();

        Serial serial = new Serial();
        PK pk = (PK) session.getAttribute("pk");
        SK sk = (SK) session.getAttribute("sk");

        List userAttr = userAttrService.getUserAttr("zhangsan");
        String[] attrs = (String[]) userAttr.toArray(new String[userAttr.size()]);

        List<UploadFile> fileList = uploadFileService.getFile("test1234");
        System.out.println(fileList.size());
        for(UploadFile key_file : fileList){
            CT ct = (CT)serial.deserial(key_file.getCt());
            LSSSMatrix lsss = (LSSSMatrix) serial.deserial(key_file.getLsss());
            LSSSMatrix lsssD1 = lsss.extract(attrs);
            int lsssIndex[] = lsssD1.getIndex();
            Tkw tkw = trapdoorService.Trapdoor(sk, "test1234");
            CTout ctout = transformService.Transform(ct, tkw, pk,lsssD1,lsssIndex);
            if(ctout != null) {
                byte[] dec = trapdoorService.Dec(ctout, sk);
                System.out.println(dec.length);
                String cm = new String(dec);
                System.out.println(cm);
            }
        }

        return null;
    }
}