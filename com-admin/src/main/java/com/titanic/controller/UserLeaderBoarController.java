package com.titanic.controller;

import com.titanic.service.UserLeaderBoarService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.SQLException;

/**
 * 推荐用户,liveUser表数据更新到userLeaderBoar,就是后台推荐用户
 */
@Controller
@RequestMapping("userLeaderBoar")
public class UserLeaderBoarController
{

    Logger log = Logger.getLogger(this.getClass());


    @Autowired
    UserLeaderBoarService userLeaderBoarService;


    /**
     * 根据主键查询   json key 为 userId
     * @param jsonInput
     * @return
     * @throws SQLException
     * @throws UnsupportedEncodingException
     */
    @ResponseBody
    @RequestMapping(value = "queryInfo", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    public String queryInfo(@RequestBody String jsonInput) throws SQLException, UnsupportedEncodingException
    {
        URLDecoder decoder = new URLDecoder();
        jsonInput =  decoder.decode(jsonInput, "utf-8");

        jsonInput = jsonInput.substring(0,jsonInput.length() -1);
        log.info(jsonInput);

        String json = userLeaderBoarService.queryByParam(jsonInput);
        return json;
    }


    /**
     * 主要修改seqNum,根据seqNum置顶 json key 为sqlNum , id
     * @param jsonInput
     * @return
     * @throws SQLException
     * @throws UnsupportedEncodingException
     */
    @ResponseBody
    @RequestMapping(value = "updateInfo", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    public int updateInfo(@RequestBody String jsonInput) throws SQLException, UnsupportedEncodingException
    {
        URLDecoder decoder = new URLDecoder();
        jsonInput = decoder.decode(jsonInput, "utf-8");

        jsonInput = jsonInput.substring(0,jsonInput.length() -1);
        log.info(jsonInput);

        int x =  userLeaderBoarService.updateSeqNum(jsonInput);
        return x;
    }

    /**
     * 根据userId删除推荐的用户 json key 为userId
     * @param jsonInput
     * @return
     * @throws SQLException
     * @throws UnsupportedEncodingException
     */
    @ResponseBody
    @RequestMapping(value = "deleteByUserId", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    public int deleteByIf(@RequestBody String jsonInput) throws SQLException, UnsupportedEncodingException
    {

        URLDecoder decoder = new URLDecoder();
        jsonInput = decoder.decode(jsonInput, "utf-8");

        jsonInput = jsonInput.substring(0,jsonInput.length() -1);
        log.info(jsonInput);

        int x =  userLeaderBoarService.deleteById(jsonInput);
        return x;
    }
}
