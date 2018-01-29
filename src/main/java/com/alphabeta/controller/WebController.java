package com.alphabeta.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/web")
public class WebController extends BaseController {

    @RequestMapping(value = "/v1/index", method = RequestMethod.GET)
    public String index(HttpServletRequest request) {
        return "index";
    }

}
