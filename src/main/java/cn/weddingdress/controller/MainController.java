package cn.weddingdress.controller;

import cn.weddingdress.model.SysUser;
import cn.weddingdress.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @RequestMapping("index")
    public String index(Model model, Authentication authentication){
        SysUser principal = (SysUser) authentication.getPrincipal();
        SysUser sysUser = userService.selectUserById(principal.getId());
        model.addAttribute("name",sysUser.getName());
        return "views/index";
    }
}
