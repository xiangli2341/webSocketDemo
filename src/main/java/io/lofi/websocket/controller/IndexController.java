package io.lofi.websocket.controller;

import io.lofi.websocket.ProductWebSocket;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class IndexController {


    //    @RequestMapping(value = "/")
//    @ResponseBody
//    public String index() {
//        return "webSocket测试";
//    }

    @RequestMapping(value = "/")
    public String index() {
        return "index";
    }

    @ResponseBody
    @GetMapping("test1")
    public String test(String userId, String message) throws Exception {
        if (userId == "" || userId == null) {
            return "发送用户id不能为空";
        }
        if (message == "" || message == null) {
            return "发送信息不能为空";
        }
        new ProductWebSocket().systemSendToUser(userId, message);
        return "发送成功！";
    }

    @ResponseBody
    @GetMapping("test2")
    public String test2(String message) throws Exception {
        if (message == "" || message == null) {
            return "发送信息不能为空";
        }
        new ProductWebSocket().sendAll(message);
        return "发送成功！";
    }


    @RequestMapping(value = "/ws")
    public String ws() {
        return "ws";
    }

    @RequestMapping(value = "/ws1")
    public String ws1() {
        return "ws1";
    }


    @PostMapping("/login")
    public ModelAndView login(@RequestParam(value = "username") String username,
                              @RequestParam(value = "password") String password, HttpServletResponse response, Map<String, Object> map, HttpSession session) {
        //非空校验
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            map.put("msg", "抱歉，用户名和密码不可为空");
            return new ModelAndView("index", map);
        }

        /**此处为临时验证*/
        if (username.equalsIgnoreCase("123") && password.equalsIgnoreCase("123")) {
            map.put("username", "123");
            map.put("msg", "登录成功！！");
            return new ModelAndView("ws", map);
        } else if (username.equalsIgnoreCase("1234") && password.equalsIgnoreCase("1234")) {
            map.put("username", "1234");
            map.put("msg", "登录成功！！");
            return new ModelAndView("ws", map);
        } else {
            map.put("msg", "账号密码不正确");
            return new ModelAndView("index", map);
        }
    }
}