package io.pgs.svc.pref.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@RequestMapping("/pref")
public class IndexController {

    @GetMapping
    public ModelAndView main() {
        ModelAndView mav = new ModelAndView("svc/pref/main.html");
        return mav;
    }

    @GetMapping("/units")
    public ModelAndView units(String active) {
        log.debug("menu active: {}", active);

        ModelAndView mav = new ModelAndView("svc/pref/units.html");
        mav.addObject("active", active);
        return mav;
    }

    @GetMapping("/sectionUnits")
    public ModelAndView sectionUnits(String active) {
        log.debug("menu active: {}", active);

        ModelAndView mav = new ModelAndView("svc/pref/sectionUnits.html");
        mav.addObject("active", active);
        return mav;
    }

    @GetMapping("/sections")
    public ModelAndView sections(String active) {
        log.debug("menu active: {}", active);
        ModelAndView mav = new ModelAndView("svc/pref/sections.html");
        mav.addObject("active", active);
        return mav;
    }

    @GetMapping("/displays")
    public ModelAndView displays(String active) {
        log.debug("menu active: {}", active);
        ModelAndView mav = new ModelAndView("svc/pref/displays.html");
        mav.addObject("active", active);
        return mav;
    }
}
