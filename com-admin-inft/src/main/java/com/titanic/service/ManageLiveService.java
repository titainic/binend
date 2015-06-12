package com.titanic.service;

import com.titanic.bean.ManageLiveBean;

import java.sql.SQLException;
import java.util.List;

/**
 * 后台在线视屏管理
 */
public interface ManageLiveService
{
    public List queryNikNameOrUserId(ManageLiveBean mb );

    public int updateById( ManageLiveBean mlb);
}
