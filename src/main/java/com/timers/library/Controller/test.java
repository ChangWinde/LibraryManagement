package com.timers.library.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class test
{
    @RequestMapping("/index")
    public String index()
    {
        return "home";
    }
}
