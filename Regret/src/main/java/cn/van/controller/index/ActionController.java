package cn.van.controller.index;

import cn.van.domain.user.LoginParamVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 登陆相关等控制器
 */
@RestController
@RequestMapping("/")
public class ActionController {

    @GetMapping("/login")
    public ModelAndView loginView() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("action/LoginView");
        return mv;
    }
    @GetMapping("/register")
    public ModelAndView registerView() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("action/RegisterView");
        return mv;
    }

    @PostMapping("/toRegister")
    public String toRegister(LoginParamVO paramVO) {

        return null;
    }
}
