package com.zmy.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zmy.init.log4jInit;
import com.zmy.pojo.User;
import com.zmy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.json.JsonString;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

@Controller

public class UserController {
    static {
        log4jInit.initLog();
    }

    @Autowired(required = false)
    @Qualifier("userService")
    private UserService userService;

    @RequestMapping("/login")
    public String login() {
        System.out.println("用户登录界面跳转");
        return "user/login";
    }

    @PostMapping("/loginUser")
    public String loginUser(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if("".equals(username)||username==null){
            //没有输入用户名，返回错误信息
            request.setAttribute("msg", "用户名或密码错误");
            return "user/login";
        }
        User user = userService.selectUserByUsername(username);
        if (username.equals(user.getUsername())) {
            if (user.getPassword().equals(password)) {
                request.getSession().setAttribute("user", user);
                return "user/login_success";
            }
        }
        return "user/login";
    }

    @RequestMapping("/ToRegister")
    public String register() {
        return "user/regist";
    }

    @PostMapping("/register")
    public String registerUser(User user,
                               HttpServletResponse response,
                               HttpServletRequest request,
                               @RequestParam(value = "code", required = true, defaultValue = "123456") String code) throws Exception, IOException {
        String token = (String) request.getSession().getAttribute(KAPTCHA_SESSION_KEY);
//        //获取确认密码
//        String rpwd = request.getParameter("rpwd");
        //验证码是否正确

        if (token.equals(code)) {
            //数据库中是否存在该用户名
            if (!userService.existUsername(user.getUsername())&&user.getUsername()!=null) {
                //不存在跳回注册成功页面
                userService.saveUser(user);
                return "user/regist_success";
            } else {
//存在，注册失败跳回注册页面
                System.out.println("用户名[" + user.getUsername() + "]已存在！");
                request.setAttribute("msg", "用户名已存在");
                request.setAttribute("username", user.getUsername());

                return "forward:ToRegister";
            }
        }
        //把回显信息保存到request域中
        request.setAttribute("msg", "验证码错误");
        request.setAttribute("code", code);
        request.setAttribute("username", user.getUsername());
        request.setAttribute("email", user.getEmail());
        return "forward:ToRegister";
    }

    @GetMapping("/ajax/register")
    public void ajaxRegisterUser(Map<String,Object> map,
                                                HttpServletRequest request,
                                                HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        boolean existUsername = userService.existUsername(username);
        map.put("existUsername", existUsername);
        response.getWriter().write(JSON.toJSONString(map));
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();//销毁session
        return "user/login";
    }
}
