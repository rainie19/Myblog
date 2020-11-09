package com.macie.dao.daoImpl;

import com.macie.bean.vo.UserInfoVo;
import com.macie.dao.UserInfoDao;
import com.macie.dao.constant.DatabaseConsts;
import com.macie.util.dbutil.DbChangeUtils;
import com.macie.util.dbutil.DbRetrieveUtils;

import java.util.ArrayList;

/**
 * @author Macie
 * @date 2020/9/25 -16:53
 */
public class UserInfoDaoImpl implements UserInfoDao {
    @Override
    public UserInfoVo retrieveUserInfoByUserName(String userName) {
        String sql = "SELECT user_id, user_name, user_phone, user_email, user_nick_name,user_age, user_avatar_path FROM user_info WHERE user_name = ?";
        ArrayList<Object> list = new ArrayList<>();
        list.add(userName);
        return DbRetrieveUtils.retrieveBeanByParams(sql, list, UserInfoVo.class);
    }

    @Override
    public String retrievePwdByUserName(String userName) {
        String sql = "SELECT user_password FROM user_info WHERE user_name = ?";
        ArrayList<Object> list = new ArrayList<>();
        list.add(userName);
        return DbRetrieveUtils.retrieveBasicTypeByParams(sql, list, String.class);
    }

    @Override
    public int updateUserInfo(UserInfoVo userInfoVo, String oldUserName) {
        String sql = "UPDATE " + DatabaseConsts.TABLE_USER_INFO + " SET " +
                DatabaseConsts.USER_NAME + " = ?, " +
                DatabaseConsts.USER_NICK_NAME + " = ?, " +
                DatabaseConsts.USER_PHONE + " = ?, " +
                DatabaseConsts.USER_EMAIL + " = ? ";
        ArrayList<Object> list = new ArrayList<>();
        list.add(userInfoVo.getUserName());
        list.add(userInfoVo.getUserNickName());
        list.add(userInfoVo.getUserPhone());
        list.add(userInfoVo.getUserEmail());
        String userAvatarPath = userInfoVo.getUserAvatarPath();
        if(userAvatarPath != null) {
            sql += ", " + DatabaseConsts.USER_AVATAR_PATH + " = ? ";
            list.add(userInfoVo.getUserAvatarPath());
        }
        list.add(oldUserName);
        sql += " WHERE " + DatabaseConsts.USER_NAME + " = ? ";

        return DbChangeUtils.changeDatabase(sql, list);
    }

    @Override
    public int updatePwd(String userName, String newPwd) {
        String sql = "UPDATE " + DatabaseConsts.TABLE_USER_INFO + " SET " +
                DatabaseConsts.USER_PASSWORD + " = ? " +
                " WHERE " + DatabaseConsts.USER_NAME + " = ? ";
        ArrayList<Object> list = new ArrayList<>();
        list.add(newPwd);
        list.add(userName);
        return DbChangeUtils.changeDatabase(sql, list);
    }
}
