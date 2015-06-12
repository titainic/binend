package com.titanic.controller;

import com.titanic.service.UserManagerService;
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
 *用户管理
 */
@Controller
@RequestMapping("UserManager")
public class UserManagerController
{
    Logger log = Logger.getLogger(this.getClass());

    @Autowired
    UserManagerService userManagerService;

    /**
     * 查询用户信息   json key为 nickName  根据nickName模糊查询  json key 为nickName
     * @param jsonInput
     * @return
     * @throws SQLException
     * @throws UnsupportedEncodingException
     */
    @ResponseBody
    @RequestMapping(value = "queryUserInfo", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    public String queryUserInfo(@RequestBody String jsonInput) throws SQLException, UnsupportedEncodingException
    {
        URLDecoder decoder = new URLDecoder();
        jsonInput =  decoder.decode(jsonInput, "utf-8");

        jsonInput = jsonInput.substring(0,jsonInput.length() -1);
        log.info(jsonInput);

        String json = userManagerService.queryUserInfo(jsonInput);
        return json;
    }

    /**
     * 把查询到的用户信息放到推荐表里面  json key为 userId
     * @param jsonInput
     * @return
     * @throws SQLException
     * @throws UnsupportedEncodingException
     */
    @ResponseBody
    @RequestMapping(value = "addUserLeaderBoard", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    public String addUserLeaderBoard(@RequestBody String jsonInput) throws SQLException, UnsupportedEncodingException
    {
        URLDecoder decoder = new URLDecoder();
        jsonInput =  decoder.decode(jsonInput, "utf-8");

        jsonInput = jsonInput.substring(0,jsonInput.length() -1);
        log.info(jsonInput);


        String str =  userManagerService.addUserLeaderBoard(jsonInput);

        return str;
    }
}
