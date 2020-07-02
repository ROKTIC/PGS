package io.pgs.svc.pref.web;

import io.pgs.cmn.ResultMapper;
import io.pgs.cmn.ServiceStatus;
import io.pgs.svc.pref.dto.SectionUnitsDto;
import io.pgs.svc.pref.dto.SectionsDto;
import io.pgs.svc.pref.service.SectionUnitsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.pgs.cmn.ResponseUtil.empty;
import static io.pgs.cmn.ResponseUtil.response;

@Slf4j
@Controller
@RequestMapping("/pref/sectionUnits")
public class SectionUnitsController {

    @Resource
    private SectionUnitsService sectionUnitsService;

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

}
