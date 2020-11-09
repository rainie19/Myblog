package com.macie.service.serviceImpl;

import com.macie.bean.vo.UserInfoVo;
import com.macie.dao.daoImpl.UserInfoDaoImpl;
import com.macie.service.UserInfoUpdateService;

/**
 * @author Macie
 * @date 2020/12/2 -21:08
 */
public class UserInfoUpdateServiceImpl implements UserInfoUpdateService {
    UserInfoDaoImpl userInfoDao =  new UserInfoDaoImpl();

    @Override
    public Boolean updateProfile(UserInfoVo userInfoVo, String oldUserName){
        userInfoDao.updateUserInfo(userInfoVo, oldUserName);
        return true;
    }

    @Override
    public Boolean updatePassWord(String userName, String oldPwd, String newPwd) {
        String currentPwd = userInfoDao.retrievePwdByUserName(userName);
        if(currentPwd != null && currentPwd.equals(oldPwd)){
            userInfoDao.updatePwd(userName, newPwd);
            return true;
        }
        return false;
    }
}
