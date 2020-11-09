package com.macie.service;

import com.macie.bean.vo.UserInfoVo;

/**
 * @author Macie
 * @date 2020/11/2 -20:52
 */
public interface UserInfoUpdateService {
    /**
     * 更新用户个人信息
     * @param userInfoVo
     * @param oldUserName
     * @return
     */
    Boolean updateProfile(UserInfoVo userInfoVo, String oldUserName);

//    /**
//     * 保存用户头像
//     * @param userAvatar
//     * @param avatarName
//     * @param rootDir
//     * @return 返回头像相对路径
//     */
//    String saveAvatar(byte[] userAvatar, String avatarName, String rootDir);

    /**
     * 更新用户密码
     *
     * @param userName
     * @param oldPwd
     * @param newPwd
     * @return
     */
    Boolean updatePassWord(String userName, String oldPwd, String newPwd);
}
