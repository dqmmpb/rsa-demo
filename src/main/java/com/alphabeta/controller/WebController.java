package com.alphabeta.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/web")
public class WebController extends BaseController {

    @RequestMapping(value = "/v1/index", method = RequestMethod.GET)
    public String index(HttpServletRequest request) {
        return "index";
    }

    @RequestMapping(value = "/v1/jsrsasign", method = RequestMethod.GET)
    public String jsrsasign(HttpServletRequest request) {
        return "jsrsasign";
    }

    @RequestMapping(value = "/v1/jsencrypt", method = RequestMethod.GET)
    public String jsencrypt(HttpServletRequest request) {
        return "jsencrypt";
    }

    @RequestMapping(value = "/v1/node-rsa", method = RequestMethod.GET)
    public String nodersa(HttpServletRequest request) {
        return "node-rsa";
    }
}
