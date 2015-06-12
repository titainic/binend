package com.titanic.dao;

import com.titanic.bean.UserLeaderBoardBean;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by titanic on 15-6-12.
 */
@Repository
public class UserLeaderBoarDaoImpl implements UserLeaderBoarDao
{
    Logger log = Logger.getLogger(this.getClass());

    @Autowired
    JdbcTemplate jdbcTemplate;


    /**
     * 根据userid 查询,如果没userid就查询全部
     * @param bean
     * @return
     */
    public List queryByParam(UserLeaderBoardBean bean)
    {
        String sql ="SELECT " +
                            "id, " +
                            "userId, " +
                            "seqNum, " +
                            "operateUserId, " +
                            "remark, " +
                            "createDate, " +
                            "updateDate, " +
                            "deleteDate, " +
                            "STATUS" +
                    " FROM " +
                            "userLeaderBoard" +
                    " WHERE 1 =1 ";
        if(!StringUtils.isBlank(bean.getUserId()))
        {
            sql  = sql + " AND userId ="+bean.getUserId();
        }

        log.info(sql);

        List list = jdbcTemplate.queryForList(sql);
        return  list;

    }

    /**
     * 新增
     * @param bean
     */
    public int save(UserLeaderBoardBean bean)
    {
        String sql = "INSERT INTO " +
                     "userLeaderBoard ( " +
                                        "userId, " +
                                        "seqNum, " +
                                        "remark, " +
                                        "createDate, " +
                                        "STATUS) " +
                    "VALUES(" +
                                        bean.getUserId() +", " +
                                        "0, " +
                                        "0, " +
                                        newDate()+"," +
                                        " 0)";
        log.info(sql);
        int x =  jdbcTemplate.update(sql);
        return x;
    }

    /**
     * 修改
     * @param bean
     */
    public int update(UserLeaderBoardBean bean)
    {
        String sql = "UPDATE" +
                        " userLeaderBoard " +
                    " SET " +
                        " seqNum =" +bean.getSeqNum() +
                    " WHERE id ="+bean.getId();

        log.info(sql);
         int  x = jdbcTemplate.update(sql);
        return x;
    }

    public int deleteByUserId(String id)
    {
        if(!StringUtils.isBlank(id))
        {
            String sql = "DELETE FROM  " +
                         "userLeaderBoard" +
                         " WHERE userId="+id;
            int x = jdbcTemplate.update(sql);

        }
        return 0;

    }



    private String newDate()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = new Date();
        String strDate = null;
        strDate = sdf.format(date);
        return "'"+strDate+"'";

    }
}
