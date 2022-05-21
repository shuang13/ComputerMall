package com.cy.store.mapper;

import com.cy.store.entity.User;

import java.util.Date;

public interface UserMapper {
    /**
     * 插入用户数据
     * @param user
     * @return
     */
    Integer insert(User user);

    /**
     * 通过用户名查找数据
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * 根据用户的id修改密码
     * @param uid 用户的id
     * @param password 用户密码
     * @param modifiedUser 修改者
     * @param modifiedTime 修改时间
     * @return 相应的行数
     */
    Integer updatePasswordByUid(Integer uid,
                            String password,
                            String modifiedUser,
                            Date modifiedTime);

    /**
     * 根据用户的id查找
     * @param uid 用户的id
     * @return 查找的结果
     */
    User findByUid(Integer uid);

    /**
     * 根据用户的id更新用户信息
     * @param uid
     * @param phone
     * @param email
     * @param gender
     * @return
     */
    Integer updateInfoByUid(Integer uid,
                            String phone,
                            String email,
                            Integer gender,
                            String modifiedUser,
                            Date modifiedTime);

    /**
     * 通过用户id更新头像
     * @param uid 用户id
     * @param avatar 用户头像路径
     * @param modifiedUser 修改者
     * @param modifiedTime 修改时间
     * @return 修改的行数
     */
    Integer updateAvatarByUid(Integer uid,
                      String avatar,
                      String modifiedUser,
                      Date modifiedTime);
}
