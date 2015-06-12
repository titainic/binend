package com.titanic.service;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.titanic.bean.UserLeaderBoardBean;
import com.titanic.dao.UserLeaderBoarDao;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by titanic on 15-6-12.
 */
@Service
public class UserLeaderBoarServiceImpl implements UserLeaderBoarService
{
    Logger log = Logger.getLogger(this.getClass());

    @Autowired
    UserLeaderBoarDao userLeaderBoarDao;

    /**
     * 根据用户Id查询UserLeaderBoar
     * @param jsonInput
     * @return
     */
    public String queryByParam(String jsonInput)
    {
        log.info(jsonInput);
        JSONObject jsonObject = JSONObject.parseObject(jsonInput);
        String userId = jsonObject.getString("userId");
        UserLeaderBoardBean bean = new UserLeaderBoardBean();
        bean.setUserId(userId);
        List list =  userLeaderBoarDao.queryByParam(bean);
        Gson g = new Gson();
        String result = g.toJson(list);
        return result;
    }

    /**
     * 修改seqNum
     * @param bean
     */
    public int updateSeqNum(String bean)
    {
        log.info(bean);
        JSONObject jsonObject = JSONObject.parseObject(bean);
        String sqlNum = jsonObject.getString("sqlNum");
        String id = jsonObject.getString("id");
        UserLeaderBoardBean ubb = new UserLeaderBoardBean();
        ubb.setId(id);
        ubb.setSeqNum(sqlNum);
        int x = userLeaderBoarDao.update(ubb);
        return x;
    }

    public int deleteById(String jsonInput)
    {
        log.info(jsonInput);
        JSONObject jsonObject = JSONObject.parseObject(jsonInput);
        String id = jsonObject.getString("userId");
        return userLeaderBoarDao.deleteByUserId(id);
    }
}
