package io.pgs.svc;

import io.pgs.cmn.ResultMapper;
import io.pgs.cmn.ServiceStatus;
import io.pgs.svc.pref.dto.SectionsDto;
import io.pgs.svc.pref.service.SectionsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.*;

import static io.pgs.cmn.ResponseUtil.response;

@Slf4j
@Controller
public class HomeController {

    @Resource
    private SectionsService sectionsService;

    @GetMapping("/")
    public ModelAndView home() {
        Map<String, Object> result = new HashMap<>();
        List<SectionsDto> unitCountList = this.sectionsService.unitCountPerSection();
        log.debug("unitCountList >>"+ unitCountList);
        unitCountList = Optional.ofNullable(unitCountList).orElse(new ArrayList<>());

        for(SectionsDto section: unitCountList) {

            Integer unitCount = section.getUnitCount() == null? 0 : section.getUnitCount();
            Integer usedUnitCount = section.getUsedUnitCount() == null ? 0 : section.getUsedUnitCount();

            Integer usedRate = 0;
            if(unitCount > 0) {

                BigDecimal x = new BigDecimal(unitCount);
                BigDecimal y = new BigDecimal(usedUnitCount);

                BigDecimal multiply = y.divide(x, 2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
                log.debug("multiply >>"+ multiply);

                usedRate = multiply.intValue();
            } else {
                section.setUnitCount(0);
                section.setUsedUnitCount(0);
                section.setEnabledUnitCount(0);
                section.setUsedRate(0);
            }

            log.debug("usedRate >>"+ usedRate);
            section.setUsedRate(usedRate);
        }

        result.put("unitCountList", unitCountList);
        return response(new ResultMapper(result, ServiceStatus.Successful), "home.html");
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
