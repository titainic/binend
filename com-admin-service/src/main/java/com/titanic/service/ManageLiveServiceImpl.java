package com.titanic.service;

import com.titanic.bean.ManageLiveBean;
import com.titanic.dao.ManageLiveDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 后台在线视屏管理
 */
@Service
public class ManageLiveServiceImpl implements ManageLiveService
{


    @Autowired
    ManageLiveDao manageLiveDao;

    public List<ManageLiveBean> queryNikNameOrUserId(ManageLiveBean mb)
    {

        List<ManageLiveBean> list = manageLiveDao.queryNikNameOrUserId(mb);



        if(list != null && list.size() >0)
        {
            return list;
        }

        list = new ArrayList<ManageLiveBean>();
        return list;

    }

    /**
     * 根基liveID修改
     *liveOrder
     *liveState
     *liveAudiences
     *liveShareUrl
     *liveSubscibes
     * @param m
     * @return
     */
    public int updateById(ManageLiveBean m)
    {
        if (m != null)
        {

            int x;
            return x = manageLiveDao.updateById(m);

        }
        return 0;
    }


}
