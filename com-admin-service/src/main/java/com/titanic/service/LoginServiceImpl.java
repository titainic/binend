package com.titanic.service;

import com.titanic.dao.LoginDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by titanic on 15-6-10.
 */
@Service
public class LoginServiceImpl implements LoginService
{

    @Autowired
    LoginDao loginDao;

    public int queryUser(String userName, String password)
    {
        List list = loginDao.queryUser(userName,password);
        if(list != null && list.size() > 0)
        {
            return 10000;
        }
        else
        {
            return 50000;
        }
    }
}
