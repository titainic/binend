package com.titanic.service;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.titanic.bean.UserInfoBean;
import com.titanic.bean.UserLeaderBoardBean;
import com.titanic.dao.UserLeaderBoarDao;
import com.titanic.dao.UserManagerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by titanic on 15-6-12.
 */
@Service
public class UserManagerServiceImpl implements UserManagerService
{


    @Autowired
    UserLeaderBoarDao userLeaderBoarDao;

    @Autowired
    UserManagerDao userManagerDao;

    public String queryUserInfo(String jsonInput)
    {
        JSONObject json = JSONObject.parseObject(jsonInput);
        String nickName = json.getString("nickName");
        UserInfoBean bean = new UserInfoBean();
        bean.setUserNickname(nickName);
        List list = userManagerDao.queryUserINfo(bean);

        Gson g = new Gson();
        String result = g.toJson(list);
        return result;
    }

    public String addUserLeaderBoard(String jsonInput)
    {
        JSONObject json = JSONObject.parseObject(jsonInput);
        String userId = json.getString("userId");

        UserLeaderBoardBean bean = new UserLeaderBoardBean();
        bean.setUserId(userId);

        //不能插入同一个用户多次,一个用户在推荐表里面只能有一条
        List list = userLeaderBoarDao.queryByParam(bean);
        if(list != null && list.size() >0)
        {
            return "不能重复插入相同的用户";
        }
        int x =  userLeaderBoarDao.save(bean);
        return x+"";
    }
}
