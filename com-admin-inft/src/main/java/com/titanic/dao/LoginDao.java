package com.titanic.dao;

import java.util.List;

/**
 * Created by titanic on 15-6-10.
 */
public interface LoginDao
{
    public List queryUser(String userName, String password);
}
