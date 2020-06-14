package io.pgs.svc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home";
    }


    @GetMapping("/signout")
    public String signout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login() {
        return "svc/user/login.html";
    }

}
