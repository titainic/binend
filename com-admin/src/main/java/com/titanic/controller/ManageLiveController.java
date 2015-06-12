package com.titanic.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.titanic.bean.ManageLiveBean;
import com.titanic.service.ManageLiveService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 后台在线视屏管理
 */

@Controller
@RequestMapping("managetLive")
public class ManageLiveController
{
    Logger log = Logger.getLogger(this.getClass());


    @Autowired
    ManageLiveService manageLiveService;


    /**
     * 更具用户昵称或创建时间liveorder,或livestate查询, json key为  nickName , liveCreated ,liveOrder ,liveState
     * @param jsonInput
     * @return
     * @throws SQLException
     * @throws UnsupportedEncodingException
     */
    @ResponseBody
    @RequestMapping(value = "queryParam", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    public String queryParam(@RequestBody String jsonInput) throws SQLException, UnsupportedEncodingException
    {
        if(!StringUtils.isBlank(jsonInput))
        {
            URLDecoder decoder = new URLDecoder();
            jsonInput =  decoder.decode(jsonInput, "utf-8");

//            jsonInput = jsonInput.substring(0,jsonInput.length() -1);
            log.info(jsonInput);

//            JSONObject json = JSONObject.parseObject(jsonInput);

//        log.info(jsonInput);
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

            String nickName =  request.getParameter("nickName");
            String liveCreated =   request.getParameter("liveCreated");
            String liveOrder = request.getParameter("liveOrder");
            String liveState = request.getParameter("liveState");



            ManageLiveBean mb =new ManageLiveBean();

            mb.setLiveState(liveState);
            mb.setLiveNickName(nickName);

            /**
             * liveState 为1时查询开始时间.不为1是创建时间
             */
            if(!StringUtils.isBlank(liveState))
            {
                if(liveState.equals("1"))
                {
                    mb.setLiveBegin(liveCreated);
                }
                else
                {
                    mb.setLiveCreated(liveCreated);
                }
            }

            mb.setLiveOrder(liveOrder);

            List list =  manageLiveService.queryNikNameOrUserId(mb);
            Map map = new HashMap();
            map.put("aaData",list);
            Gson g = new Gson();
            String result = g.toJson(map);
            log.info(result);
            return result;
        }



//        log.info(result);
        return null;
    }


    /**
     * 根据id修改直播数据 json key为 liveSubscibes , liveOrder , liveShareUrl ,liveAudiences , liveState ,liveId
     * @param jsonInput
     * @return
     * @throws SQLException
     */
    @ResponseBody
    @RequestMapping(value = "updateById", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    public String updateById(@RequestBody String jsonInput) throws SQLException, UnsupportedEncodingException
    {
        URLDecoder decoder = new URLDecoder();
        jsonInput =  decoder.decode(jsonInput, "utf-8");

        jsonInput = jsonInput.substring(0,jsonInput.length() -1);
        log.info(jsonInput);

        JSONObject json = JSONObject.parseObject(jsonInput);
        String liveSubscibes = json.getString("liveSubscibes");
        String liveOrder = json.getString("liveOrder");
        String liveShareUrl = json.getString("liveShareUrl");
        String liveAudiences = json.getString("liveAudiences");
        String liveState = json.getString("liveState");
        String liveId = json.getString("liveId");

        if(StringUtils.isBlank(liveId))
        {
            return "liveId 不能为空";
        }

        ManageLiveBean mb =new ManageLiveBean();
        mb.setLiveSubscibes(liveSubscibes);
        mb.setLiveId(liveId);
        mb.setLiveOrder(liveOrder);
        mb.setLiveShareUrl(liveShareUrl);
        mb.setLiveAudiences(liveAudiences);
        mb.setLiveState(liveState);


        log.info(jsonInput);
        int x = manageLiveService.updateById(mb);
        return x+"";
    }
}
