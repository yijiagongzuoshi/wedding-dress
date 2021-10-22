package cn.weddingdress.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    /**
     * 登陆页面
     *
     * @return
     */
    @RequestMapping("/user/login")
    public String login() {
        return "views/user/login";
    }
}
