package com.titanic.dao;

import com.titanic.bean.UserInfoBean;

import java.util.List;

/**
 * Created by titanic on 15-6-12.
 */
public interface UserManagerDao
{
    public List queryUserINfo(UserInfoBean bean);
}
