package com.cy.store.service;

import com.cy.store.entity.User;

import java.util.Date;

public interface IUserService {
    /**
     * 用户注册
     * @param user 注册信息
     */
    public void reg(User user);

    /**
     * 用户登录
     * @param user 登录信息
     * @return 用户信息
     */
    public User login(User user);

    /**
     * 修改密码
     * @param uid 用户id
     * @param username 用户名
     * @param oldPassword 老密码
     * @param newPassword 新密码
     */
    public void changePassword(Integer uid,
                               String username,
                               String oldPassword,
                               String newPassword);

    /**
     * 通过用户id获取用户信息
     * @param uid 用户id
     * @return 用户信息
     */
    public User getInfoByUid(Integer uid);

    public void changeInfo(Integer uid,
                           String username,
                           String phone,
                           String email,
                           Integer gender);

    /**
     * 更改用户头像
     * @param uid 用户id
     * @param username 用户名
     * @param avatar 头像路径
     */
    public void changeAvatar(Integer uid,
                             String username,
                             String avatar);
}
