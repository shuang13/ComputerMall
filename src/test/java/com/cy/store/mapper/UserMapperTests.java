package com.cy.store.mapper;

import com.cy.store.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.runner.RunWith;

import java.util.Date;

//@SpringBootTest: 表示当前类为测试类，不会随项目打包
@SpringBootTest
//@RunWith: 表示启动这个单元测试类
@RunWith(SpringRunner.class)
public class UserMapperTests {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void insert() {
        User user = new User();
        user.setUsername("zbb3");
        user.setPassword("123");
        Integer rows = userMapper.insert(user);
        System.out.println(rows);
    }
    @Test
    public void findByUsername() {
        String username = "zbb";
        User res = userMapper.findByUsername(username);
        System.out.println(res);
    }
    @Test
    public void findByUid() {
        Integer uid = 22;
        User res = userMapper.findByUid(uid);
        System.out.println(res);
    }
    @Test
    public void updatePasswordByUid() {
        Integer uid = 22;
        String password = "111";
        Date modifiedTime = new Date();
        String modifiedUser = "admin";
        Integer res = userMapper.updatePasswordByUid(uid, password, modifiedUser, modifiedTime);
        System.out.println(res);
    }
    @Test
    public void updateInfoByUid() {
        Integer row = userMapper.updateInfoByUid(24, "111111", "111@qq.com", 0, "z", new Date());
        System.out.println(row);
    }
    @Test
    public void updateAvatarByUid() {
        Integer row = userMapper.updateAvatarByUid(24, "/upload/1.png", "z", new Date());
        System.out.println(row);
    }

}
