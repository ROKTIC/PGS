package io.pgs.svc.syst.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@RequestMapping("/syst")
public class SystController {

    @GetMapping
    public ModelAndView main() {
        ModelAndView mav = new ModelAndView("svc/syst/main.html");
        return mav;
    }

    @GetMapping("/codes")
    public ModelAndView codes(String active) {
        log.debug("menu active: {}", active);

        ModelAndView mav = new ModelAndView("svc/syst/codes.html");
        mav.addObject("active", active);
        return mav;
    }

    @GetMapping("/code/details")
    public ModelAndView details(String active) {
        log.debug("menu active: {}", active);

        ModelAndView mav = new ModelAndView("svc/syst/codeDetails.html");
        mav.addObject("active", active);
        return mav;
    }
}
