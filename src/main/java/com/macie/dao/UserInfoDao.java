package com.macie.dao;

import com.macie.bean.vo.UserInfoVo;

/**
 * @author Macie
 * @date 2020/9/30 -20:43
 */
public interface UserInfoDao {

    /**
     * 获取用户信息
     *
     * @param userName
     * @return
     */
    public UserInfoVo retrieveUserInfoByUserName(String userName);

    /**
     * 获取密码
     *
     * @param userName
     * @return
     */
    public String retrievePwdByUserName(String userName);

    /**
     * 更新用户信息
     *
     * @param userInfoVo
     * @param oldUserName
     * @return
     */
    public int updateUserInfo(UserInfoVo userInfoVo, String oldUserName);

    /**
     * 更新密码
     *
     * @param userName
     * @param newPwd
     * @return
     */
    public int updatePwd(String userName, String newPwd);
}
