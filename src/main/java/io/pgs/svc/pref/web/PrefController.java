package io.pgs.svc.pref.web;

import io.pgs.svc.pref.dto.SectionsDto;
import io.pgs.svc.pref.dto.UnitsDto;
import io.pgs.svc.pref.service.SectionsService;
import io.pgs.svc.pref.service.UnitsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/pref")
public class PrefController {

    @Resource
    private UnitsService unitsService;

    @Resource
    private SectionsService sectionsService;

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

        List<UnitsDto> allUnits = this.unitsService.all();// 모든 주차면
        List<SectionsDto> allSections = this.sectionsService.all();// 모든 주차구획

        ModelAndView mav = new ModelAndView("svc/pref/sectionUnits.html");
        mav.addObject("active", active);
        mav.addObject("allUnits", allUnits);
        mav.addObject("allSections", allSections);
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
