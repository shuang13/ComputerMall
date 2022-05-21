package com.cy.store.controller;

import com.cy.store.controller.ex.*;
import com.cy.store.entity.Product;
import com.cy.store.service.ex.*;
import com.cy.store.utils.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

/**
 * 控制层类的基类
 */
public class BaseController {
    /**
     * 操作成功的状态码
     */
    public static final int OK = 200;
    @ExceptionHandler({ServiceException.class, FileUploadException.class}) // 用于统一处理抛出的异常
    public JsonResult<Void> handleException(Throwable e) {
        JsonResult<Void> res = new JsonResult<>(e);
        if(e instanceof UsernameDuplicatedException) {
            res.setState(4000);
            res.setMessage("用户已被占用");
        } else if(e instanceof InsertException) {
            res.setState(5000);
            res.setMessage("用户注册发生异常");
        } else if(e instanceof UserNotFoundException) {
            res.setState(6000);
            res.setMessage("用户不存在");
        } else if(e instanceof ProductNotFoundException) {
            res.setState(6001);
            res.setMessage("商品不存在");
        } else if(e instanceof PasswordNotMatchException) {
            res.setState(7000);
            res.setMessage("密码错误");
        } else if(e instanceof UpdateException) {
            res.setState(8000);
            res.setMessage("更新数据发生异常");
        } else if(e instanceof FileEmptyException) {
            res.setState(9000);
            res.setMessage("文件为空");
        } else if(e instanceof FileSizeException) {
            res.setState(9001);
            res.setMessage("文件大小超出");
        } else if(e instanceof FileStateException) {
            res.setState(9002);
            res.setMessage("文件状态异常");
        } else if(e instanceof FileTypeException) {
            res.setState(9003);
            res.setMessage("文件类型异常");
        } else if(e instanceof FileUploadIOException) {
            res.setState(9004);
            res.setMessage("文件IO异常");
        }
        return res;
    }

    /**
     * 获取session对象中的uid
     * @param session
     * @return 当前session中的uid
     */
    protected final Integer getUidFromSession(HttpSession session) {
        return Integer.valueOf(session.getAttribute("uid").toString());
    }

    /**
     * 获取session对象中的username
     * @param session
     * @return 当前session中的username
     */
    protected final String getUsernameFromSession(HttpSession session) {
        return session.getAttribute("username").toString();

    }
}
