package com.titanic.dao;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by titanic on 15-6-10.
 */
@Repository
public class LoginDaoImpl implements LoginDao
{

    Logger log = Logger.getLogger(this.getClass());

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List  queryUser(String userName, String password)
    {

        log.info(userName);
        log.info(password);

        List list =  jdbcTemplate.queryForList("SELECT userId, userAccount, userAccountPassword, userAccountType, userType, userNickname, userSex, userSign, userPhone, userWechatNickName, userSinaNickName, userPhoto, userFocusNum, userFansNum, userClienType, userGTClientId, userLng, userLat, userAddr, userIsForbid, userCreated, userUpdated, userDeleted, userStatus FROM userInfo WHERE userAccount = ? AND userAccountPassword = ?", userName, password);
        return list;
    }
}
