package com.titanic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by jiawa on 15/11/4.
 */
@Controller
public class IndexController
{

    @RequestMapping("/index.htm")
    public String index()
    {
        return "index";
    }
}
