package com.titanic.controller;

import com.titanic.remote.SparkRemoteSubmitJob;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by jiawa on 15/11/4.
 */
@Controller
@RequestMapping("spark")
public class IndexController
{

    @ResponseBody
    @RequestMapping(value = "sparkSubmit", method = { RequestMethod.GET, RequestMethod.POST })
    public String index()
    {
        SparkRemoteSubmitJob.submitJob();
        return "index";
    }
}
