package com.titanic.dao;

import com.titanic.bean.UserInfoBean;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by titanic on 15-6-12.
 */
@Repository
public class UserManagerDaoImpl implements UserManagerDao
{

    Logger log = Logger.getLogger(this.getClass());

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List queryUserINfo(UserInfoBean bean)
    {
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT " +
                "userId, " +
                "userAccount, " +
                "userAccountPassword, " +
                "userAccountType, " +
                "userType, " +
                "userNickname, " +
                "userSex, " +
                "userSign, " +
                "userPhone, " +
                "userWechatNickName, " +
                "userSinaNickName, " +
                "userPhoto, " +
                "userFocusNum, " +
                "userFansNum, " +
                "userClienType, " +
                "userGTClientId, " +
                "userLng, userLat, " +
                "userAddr, " +
                "userIsForbid, " +
                "userCreated, " +
                "userUpdated, " +
                "userDeleted, " +
                "userStatus" +
                " FROM " +
                " userInfo " +
                "WHERE 1 = 1 ");
        if(bean!= null)
        {
            sql.append(" AND userNickname LIKE '%"+bean.getUserNickname()+"%'");
        }

        log.info(sql.toString());
        List list = jdbcTemplate.queryForList(sql.toString());
        return list;
    }
}
