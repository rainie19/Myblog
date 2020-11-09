package com.macie.service;

import com.macie.bean.vo.UserInfoVo;
import com.macie.dao.UserInfoDao;
import com.macie.dao.daoImpl.UserInfoDaoImpl;

/**
 * @author Macie
 * @date 2020/9/30 -21:08
 */
public class UserInfoService implements UserInfoDao {
    UserInfoDaoImpl userInfoDaoImpl = new UserInfoDaoImpl();
    @Override
    public UserInfoVo retrieveUserInfoByUserName(String userName) {
        return userInfoDaoImpl.retrieveUserInfoByUserName(userName);
    }

    @Override
    public String retrievePwdByUserName(String userName) {
        return userInfoDaoImpl.retrievePwdByUserName(userName);
    }

    @Override
    public int updateUserInfo(UserInfoVo userInfoVo, String oldUserName) {
        return userInfoDaoImpl.updateUserInfo(userInfoVo, oldUserName);
    }

    @Override
    public int updatePwd(String userName, String newPwd) {
        return userInfoDaoImpl.updatePwd(userName, newPwd);
    }
}
