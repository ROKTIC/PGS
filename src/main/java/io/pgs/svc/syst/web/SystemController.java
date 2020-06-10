package io.pgs.svc.syst.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SystemController {

    @GetMapping("/syst")
    public ModelAndView syst() {
        ModelAndView mav = new ModelAndView("svc/syst/main.html");
        return mav;
    }

}
