package com.titanic.dao;

import com.titanic.bean.ManageLiveBean;

import java.util.List;

/**
 * 后台在线视屏管理
 */
public interface ManageLiveDao
{
    public List queryNikNameOrUserId(ManageLiveBean mb);

    public int updateById(ManageLiveBean m);
}
