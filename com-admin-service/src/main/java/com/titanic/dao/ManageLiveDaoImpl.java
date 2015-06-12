package com.titanic.dao;

import com.titanic.bean.ManageLiveBean;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 后台在线视屏管理
 */
@Repository
public class ManageLiveDaoImpl implements ManageLiveDao
{


    Logger log = Logger.getLogger(this.getClass());

    @Autowired
    JdbcTemplate jdbcTemplate;


    /**
     * 根据
     * @param mb
     * @return
     */
    public List queryNikNameOrUserId(ManageLiveBean mb)
    {
        StringBuffer sql = new StringBuffer("SELECT ");
        sql.append(
                                "liveId, " +
                                "liveUserId, " +
                                "liveNickname, " +
                                "liveUserPhoto, " +
                                "liveDownloadUrl, " +
                                "liveOrder, " +
                                "liveStreamId, " +
                                "liveRtmpPublishUrl, " +
                                "liveHlsPublishUrl, " +
                                "liveRtmpPlayUrl, " +
                                "liveHlsPlayUrl, " +
                                "liveShareUrl, " +
                                "liveGroupId," +
                                " liveSubscibes, " +
                                "liveShares, " +
                                "liveLikes, " +
                                "liveAudiences, " +
                                "liveTitle, " +
                                "liveDescribe, " +
                                "liveImg, " +
                                "liveTags," +
                                "liveScope, " +
                                "liveState, " +
                                "liveReserved, " +
                                "liveBegin, " +
                                "liveEnd, " +
                                "liveCreated, " +
                                "liveUpdated, " +
                                "liveDeleted, " +
                                "liveStatus " +
                  "FROM live WHERE 1 = 1 ");


        /**
         * 判断一下字段是否为空,来拼接查询条件
         */

        String nickName = mb.getLiveNickName();
        //判断昵称是否为空
        if(!StringUtils.isBlank(nickName))
        {
            String whereNicNmae = " AND liveNickname LIKE '%"+nickName+"%' ";
            sql.append(whereNicNmae);
        }

//
//        if(!StringUtils.isBlank(userId))
//        {
//            String whereUserId = " AND liveUserId ="+userId;
//            sql.append(whereUserId);
//        }
//
//        if(!StringUtils.isBlank(liveTitle))
//        {
//            String whereUserId = " AND liveTitle LIKE '&"+liveTitle+"%'";
//            sql.append(whereUserId);
//        }
       String liveCreated = mb.getLiveCreated();
        if(!StringUtils.isBlank(liveCreated))
        {
            String whereUserId = " AND DATE_FORMAT(liveCreated,'%Y-%m-%d') = "+"'"+str2Data(liveCreated)+"'";
            sql.append(whereUserId);
        }

        String liveBegin = mb.getLiveBegin();
        if(!StringUtils.isBlank(liveBegin))
        {
            String whereUserId = " AND DATE_FORMAT(liveBegin,'%Y-%m-%d') = "+"'"+str2Data(liveBegin)+"'";
            sql.append(whereUserId);
        }

        String liveOrder = mb.getLiveOrder();
        if(!StringUtils.isBlank(liveOrder))
        {
            String whereUserId = " AND liveOrder ="+liveOrder;
            sql.append(whereUserId);
        }

        String liveState = mb.getLiveState();
        if(!StringUtils.isBlank(liveState))
        {
            String whereUserId = " AND liveState ="+liveState;
            sql.append(whereUserId);
        }


        log.info(sql.toString());
        String sqlParam = sql.toString().toString();
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sqlParam);


        return list;
    }

    /**
     * 根据id修改
     * @param m
     * @return
     */
    public int updateById(ManageLiveBean m)
    {

        String liveOrder = m.getLiveOrder();
        String liveShareUrl = m.getLiveShareUrl();
        String liveAudiences = m.getLiveAudiences();
        String liveState = m.getLiveState();
        String liveSubscibes = m.getLiveSubscibes();
        String liveId = m.getLiveId();

        StringBuffer sql = new StringBuffer();
        sql.append("UPDATE live SET " );

        /**
         * 根据一下条件是否为空,更新数据
         */
        if(!StringUtils.isBlank(liveOrder))
        {

            sql.append("liveOrder ="+liveOrder+",");
        }

        if(!StringUtils.isBlank(liveShareUrl))
        {
            sql.append( "liveShareUrl = "+liveShareUrl+"," );
        }

        if(!StringUtils.isBlank(liveAudiences))
        {
            sql.append("liveAudiences ="+liveAudiences+",");
        }

        if(!StringUtils.isBlank(liveState))
        {
            sql.append("liveState ="+liveState+",");
        }

        if(!StringUtils.isBlank(liveSubscibes))
        {
            sql.append("liveSubscibes ="+liveSubscibes+",");
        }

        //消除最后的,号.在更新时报错
        String sqldd = sql.substring(0,sql.length()-1);

        StringBuffer sqlParam = new StringBuffer();

        sqlParam.append(sqldd);
        sqlParam.append("  WHERE liveId =" + liveId);

        log.info(sqlParam);

        int x = jdbcTemplate.update(sqlParam.toString());

        return x;
    }


    /**
     * 字符串转换日期
     * @param date
     * @return
     */
    private String str2Data(String date)
    {
        Date d = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try
        {
            d = sdf.parse(date);
        } catch (ParseException e)
        {
            e.printStackTrace();
        }
        String dd = sdf.format(d);
        return dd;
    }
}
