package com.cy.store.service;

import com.cy.store.entity.User;
import com.cy.store.mapper.UserMapper;
import com.cy.store.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

//@SpringBootTest: 表示当前类为测试类，不会随项目打包
@SpringBootTest
//@RunWith: 表示启动这个单元测试类
@RunWith(SpringRunner.class)
public class UserServiceTests {
    @Autowired
    private IUserService userService;
    @Test
    public void reg() {
        try {
            User user = new User();
            user.setUsername("zbb9");
            user.setPassword("123");
            userService.reg(user);
            System.out.println("regist OK");
            System.out.println(user.toString());
        } catch (ServiceException e) {
            System.out.println(e.getClass());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void changePassword() {

        try {
            userService.changePassword(24, "z", "123", "111");
        } catch (ServiceException e) {
            System.out.println(e.getClass());
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void getInfoByUid() {
        try {
            User res = userService.getInfoByUid(24);
            System.out.println(res);
        } catch (ServiceException e) {
            System.out.println(e.getClass());
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void updateInfo() {
        try {
            userService.changeInfo(24, "z", "111111", "111@qq.com", 0);
        } catch (ServiceException e) {
            System.out.println(e.getClass());
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void changeAvatar() {
        try {
            userService.changeAvatar(24, "z", "/upload/2.png");
        } catch (ServiceException e) {
            System.out.println(e.getClass());
            System.out.println(e.getMessage());
        }
    }


}
