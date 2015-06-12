package com.titanic.service;


/**
 * Created by titanic on 15-6-12.
 */
public interface UserLeaderBoarService
{
    public String queryByParam(String bean);

    public int updateSeqNum(String bean);

    public int deleteById(String id);
}
