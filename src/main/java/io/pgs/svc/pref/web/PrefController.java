package io.pgs.svc.pref.web;

import io.pgs.cmn.ServiceUtil;
import io.pgs.svc.pref.dto.DisplaysDto;
import io.pgs.svc.pref.dto.SectionUnitsDto;
import io.pgs.svc.pref.dto.SectionsDto;
import io.pgs.svc.pref.dto.UnitsDto;
import io.pgs.svc.pref.service.DisplaysService;
import io.pgs.svc.pref.service.SectionUnitsService;
import io.pgs.svc.pref.service.SectionsService;
import io.pgs.svc.pref.service.UnitsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/pref")
public class PrefController {

    @Resource
    private UnitsService unitsService;

    @Resource
    private SectionsService sectionsService;

    @Resource
    private SectionUnitsService sectionUnitsService;

    @Resource
    private DisplaysService displaysService;

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

    @GetMapping("/section/details")
    public ModelAndView sectionDetails(String active, SectionsDto section) {
        log.debug("menu active: {}", active);
        String sectionId = section.getId();
        section = this.sectionsService.info(sectionId);
        String sectionName = section.getName();

        List<SectionUnitsDto> units = this.sectionUnitsService.listBySectionId(sectionId);
        for(SectionUnitsDto sectionUnitsDto : units) {
            String incomingTime = ServiceUtil.trim(sectionUnitsDto.getIncoming_time());
            if(incomingTime != null && incomingTime.length() == 14) {
                LocalDateTime incomingTimeLDT = LocalDateTime.parse(incomingTime, DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
                incomingTime = incomingTimeLDT.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                sectionUnitsDto.setIncoming_time(incomingTime);
            }
        }

        ModelAndView mav = new ModelAndView("svc/pref/sectionDetails.html");
        mav.addObject("active", active);
        mav.addObject("units", units);
        mav.addObject("sectionName", sectionName);
        return mav;
    }

    @GetMapping("/displays")
    public ModelAndView displays(String active) {
        log.debug("menu active: {}", active);

        List<SectionsDto> allSections = this.sectionsService.all();// 모든 주차구획
        ModelAndView mav = new ModelAndView("svc/pref/displays.html");
        mav.addObject("active", active);
        mav.addObject("allSections", allSections);
        return mav;
    }

    @GetMapping("/display/details")
    public ModelAndView displayDetails(String active, DisplaysDto display) {

        String id = display.getId();

        display = this.displaysService.info(id);
        String section_ids = display.getSection_ids();
        String[] sectionIds = section_ids.split(",");
        List<SectionsDto> sectionList = new ArrayList<>();
        if(sectionIds != null && sectionIds.length > 0) {
            List<String> sectionIdList = Arrays.asList(sectionIds); // SECTION_ID
            sectionList = this.sectionsService.idNamelist(sectionIdList);
        }

        ModelAndView mav = new ModelAndView("svc/pref/displayDetails.html");
        mav.addObject("active", active);
        mav.addObject("display", display);
        mav.addObject("sectionList", sectionList);
        return mav;
    }
}
