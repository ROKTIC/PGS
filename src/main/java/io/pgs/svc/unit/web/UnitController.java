package io.pgs.svc.unit.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UnitController {

    @GetMapping("/unit")
    public ModelAndView unit() {
        ModelAndView mav = new ModelAndView("svc/unit/main.html");
        return mav;
    }
}
