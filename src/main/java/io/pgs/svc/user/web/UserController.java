package io.pgs.svc.user.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @GetMapping("/user")
    public ModelAndView user() {
        ModelAndView mav = new ModelAndView("svc/user/main.html");
        return mav;
    }
}
