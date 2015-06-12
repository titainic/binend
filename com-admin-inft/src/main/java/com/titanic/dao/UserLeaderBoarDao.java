package com.titanic.dao;

import com.titanic.bean.UserLeaderBoardBean;

import java.util.List;

/**
 * Created by titanic on 15-6-12.
 */
public interface UserLeaderBoarDao
{
    public int save(UserLeaderBoardBean bean);

    public int update(UserLeaderBoardBean bean);

    public List queryByParam(UserLeaderBoardBean bean);

    public int deleteByUserId(String id);
}
