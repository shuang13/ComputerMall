package com.cy.store.service.impl;

import com.cy.store.entity.User;
import com.cy.store.mapper.UserMapper;
import com.cy.store.service.IUserService;
import com.cy.store.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public void reg(User user) {
        String username = user.getUsername();
        User res = userMapper.findByUsername(username);
        if(res != null) {
            throw new UsernameDuplicatedException("用户名被占用");
        }
        // md5密码加密
        String oldPassword = user.getPassword();
        // 获取盐值
        String salt = UUID.randomUUID().toString().toUpperCase();
        // 3次加密
        String md5Password = getMD5Password(oldPassword, salt);
        user.setPassword(md5Password);
        user.setSalt(salt);

        // 日志
        user.setIsDelete(0);
        Date date = new Date();
        user.setCreatedTime(date);
        user.setCreatedUser(username);
        user.setModifiedTime(date);
        user.setModifiedUser(username);
        Integer rows = userMapper.insert(user);
        if(rows != 1) {
            throw new InsertException("插入数据失败");
        }

    }

    @Override
    public User login(User user) {
        String username = user.getUsername();
        String oldPassword = user.getPassword();
        User res = userMapper.findByUsername(username);
        String salt = res.getSalt();

        if(res == null || res.getIsDelete() == 1) {
            throw new UserNotFoundException("登录失败，用户不存在");
        }
        String resPassword = res.getPassword();
        String md5Password = getMD5Password(oldPassword, salt);
        if(!md5Password.equals(resPassword)) {
            throw new PasswordNotMatchException("登录失败，密码错误");
        }
        User responseUser = new User();
        responseUser.setUsername(res.getUsername());
        responseUser.setUid(res.getUid());
        return responseUser;
    }

    private String getMD5Password(String password, String salt) {
        for (int i = 0; i < 3; i ++) {
            password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toUpperCase();

        }
        return password;
    }

    @Override
    public void changePassword(Integer uid, String username, String oldPassword, String newPassword) {
        User res = userMapper.findByUid(uid);
        if(res == null || res.getIsDelete() == 1) {
            throw new UserNotFoundException("用户数据不存在");
        }
        String oldMd5Password = getMD5Password(oldPassword, res.getSalt());
        if(!res.getPassword().equals(oldMd5Password)) {
            throw new PasswordNotMatchException("密码错误");
        }
        String newMd5Password = getMD5Password(newPassword, res.getSalt());
        Integer row = userMapper.updatePasswordByUid(uid, newMd5Password, username, new Date());
        if(row != 1) {
            throw new UpdateException("更新数据产生未知异常");
        }
    }

    @Override
    public User getInfoByUid(Integer uid) {
        User res = userMapper.findByUid(uid);
        if(res == null || res.getIsDelete() == 1) {
            throw new UserNotFoundException("用户数据不存在");
        }
        User user = new User();
        user.setUid(uid);
        user.setUsername(res.getUsername());
        user.setPhone(res.getPhone());
        user.setEmail(res.getEmail());
        user.setGender(res.getGender());
        return user;
    }

    @Override
    public void changeInfo(Integer uid,
                           String username,
                           String phone,
                           String email,
                           Integer gender) {
        Integer row = userMapper.updateInfoByUid(uid, phone, email, gender, username, new Date());
        if(row != 1) {
            throw new UpdateException("更新发生异常");
        }
    }

    @Override
    public void changeAvatar(Integer uid, String username, String avatar) {
        User user = userMapper.findByUid(uid);
        if (user == null || user.getIsDelete().equals(1)) {
            throw new UserNotFoundException("用户不存在");
        }
        Integer row = userMapper.updateAvatarByUid(uid, avatar, username, new Date());
        if(row != 1) {
            throw new UpdateException("用户头像更新发生未知异常");
        }

    }
}
