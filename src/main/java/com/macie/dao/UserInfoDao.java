package com.macie.dao;

import com.macie.bean.vo.UserInfoVo;

/**
 * @author Macie
 * @date 2020/9/30 -20:43
 */
public interface UserInfoDao {

    public UserInfoVo retrieveUserInfoByUserName(String userName);
    /**
     *
     * @param userName
     * @return
     */
    public String retrievePwdByUserName(String userName);

    /**
     *
     * @param userInfoVo
     * @param oldUserName
     * @return
     */
    public int updateUserInfo(UserInfoVo userInfoVo, String oldUserName);

    /**
     *
     * @param userName
     * @param newPwd
     * @return
     */
    public int updatePwd(String userName, String newPwd);
}
