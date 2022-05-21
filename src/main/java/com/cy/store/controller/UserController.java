package com.cy.store.controller;

import com.cy.store.controller.ex.*;
import com.cy.store.entity.User;
import com.cy.store.mapper.UserMapper;
import com.cy.store.service.IUserService;
import com.cy.store.service.ex.InsertException;
import com.cy.store.service.ex.ServiceException;
import com.cy.store.service.ex.UsernameDuplicatedException;
import com.cy.store.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.*;

@RestController
public class UserController extends BaseController{
    @Autowired
    private IUserService userService;
    @RequestMapping("/users/reg")
    public JsonResult<Void> reg(User user){
        JsonResult<Void> res = new JsonResult<>();
        userService.reg(user);
        res.setState(OK);
        res.setMessage("用户注册成功");
        return res;
    }
    @RequestMapping("/users/login")
    public JsonResult<User> login(User user, HttpSession session){
        User responseUser = userService.login(user);
        session.setAttribute("uid", responseUser.getUid());
        session.setAttribute("username", responseUser.getUsername());
        System.out.println(getUidFromSession(session));
        System.out.println(getUsernameFromSession(session));
        return new JsonResult<User>(OK, responseUser);
    }
    @RequestMapping("/users/change_password")
    public JsonResult<Void> changePassword(String oldPassword, String newPassword, HttpSession session){
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        userService.changePassword(uid, username, oldPassword, newPassword);
        return new JsonResult<Void>(OK);
    }
    @RequestMapping("/users/get_by_uid")
    public JsonResult<User> getInfoByUid(HttpSession session){
        Integer uid = getUidFromSession(session);
        User data = userService.getInfoByUid(uid);
        return new JsonResult<User>(OK, data);
    }
    @RequestMapping("/users/change_info")
    public JsonResult<Void> changeInfo(User user, HttpSession session){
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        userService.changeInfo(uid, username, user.getPhone(), user.getEmail(), user.getGender());
        return new JsonResult<Void>(OK);
    }
    public static final Integer AVATAR_SIZE = 10 * 1024 * 1024;
    public static final List<String> AVATAR_TYPE = new ArrayList<>();
    static {
        AVATAR_TYPE.add("image/png");
        AVATAR_TYPE.add("image/jpeg");
        AVATAR_TYPE.add("image/bmp");
        AVATAR_TYPE.add("image/gif");
    }

    @RequestMapping("/users/change_avatar")
    public JsonResult<String> changeAvatar(MultipartFile file, HttpSession session){
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);

        if(file.isEmpty()) {
            throw new FileEmptyException("文件为空");
        }
        if(file.getSize() > AVATAR_SIZE) {
            throw new FileSizeException("文件大小超过10M");
        }
        String contentType = file.getContentType();
        if(!AVATAR_TYPE.contains(file.getContentType())) {
            throw new FileTypeException("文件类型异常");
        }
        String parentPath = session.getServletContext().getRealPath("upload");
        File dir = new File(parentPath);
        if(!dir.exists()) {
            dir.mkdirs();
        }
        String originalFilename = file.getOriginalFilename();
        Integer ind = originalFilename.lastIndexOf(".");
        String suffix = originalFilename.substring(ind);
        String filename = UUID.randomUUID().toString().toUpperCase() + suffix;
        System.out.println(filename);
        System.out.println(contentType);
        System.out.println(dir);
        File newFile = new File(dir, filename);
        try {
            file.transferTo(newFile);
        } catch (IOException e) {
            throw new FileUploadIOException("文件IO异常");
        } catch (FileStateException e) {
            throw new FileSizeException("文件状态异常");
        }
        String avatar = "/upload/" + filename;
        userService.changeAvatar(uid, username, avatar);
        return new JsonResult<String>(OK, avatar);
    }
}
