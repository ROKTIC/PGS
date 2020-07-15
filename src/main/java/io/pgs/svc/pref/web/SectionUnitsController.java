package io.pgs.svc.pref.web;

import io.pgs.cmn.ResultMapper;
import io.pgs.cmn.ServiceStatus;
import io.pgs.cmn.ServiceUtil;
import io.pgs.svc.pref.dto.SectionUnitsDto;
import io.pgs.svc.pref.dto.SectionsDto;
import io.pgs.svc.pref.service.SectionUnitsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

import static io.pgs.cmn.ResponseUtil.empty;
import static io.pgs.cmn.ResponseUtil.response;

@Slf4j
@Controller
@RequestMapping("/pref/sectionUnits")
public class SectionUnitsController {

    @Resource
    private SectionUnitsService sectionUnitsService;

    @PostMapping("/save")
    @ResponseBody
    public ModelAndView save(String sectionId, String[] unitIds) {
        Map<String, Object> result = new HashMap<>();

        List<SectionUnitsDto> list = new ArrayList<>();
        if(unitIds != null) {
            LocalDateTime now = LocalDateTime.now();

            for(String unitId: unitIds) {

                SectionUnitsDto element = new SectionUnitsDto();
                element.setSection_id(sectionId);
                element.setUnit_id(unitId);
                element.setCreatedAt(Timestamp.valueOf(now));
                element.setUpdatedAt(Timestamp.valueOf(now));

                list.add(element);
            }
        }

        int successfulCount = this.sectionUnitsService.save(sectionId, list);
        log.debug("successfulCount: {}", successfulCount);

        return response(new ResultMapper(result, ServiceStatus.Successful));
    }

    @GetMapping("/list")
    public ModelAndView list(SectionUnitsDto sectionUnitsDto) {
        Map<String, Object> result = new HashMap<>();

        log.debug("sectionUnitsDto >>>" + sectionUnitsDto);
        List<SectionUnitsDto> list = new ArrayList<>();
        if (!empty(sectionUnitsDto.getSection_id())) {
            list = this.sectionUnitsService.listBySectionId(sectionUnitsDto.getSection_id());
        }

        result.put("list", list);
        return response(new ResultMapper(result, ServiceStatus.Successful), "svc/pref/sectionUnits-ListTemplate.html");
    }

    @GetMapping("/list2")
    public ModelAndView list2(SectionUnitsDto sectionUnitsDto) {
        Map<String, Object> result = new HashMap<>();

        log.debug("sectionUnitsDto >>>" + sectionUnitsDto);
        List<SectionUnitsDto> list = new ArrayList<>();
        if (!empty(sectionUnitsDto.getSection_id())) {
            list = this.sectionUnitsService.listBySectionId(sectionUnitsDto.getSection_id());
        }

        result.put("list", list);
        return response(new ResultMapper(result, ServiceStatus.Successful), "svc/pref/sectionUnits-ListTemplate2.html");
    }

}
