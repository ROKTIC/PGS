package io.pgs.svc.pref.web;

import io.pgs.cmn.ResultMapper;
import io.pgs.cmn.ServiceStatus;
import io.pgs.cmn.ServiceUtil;
import io.pgs.svc.pref.dto.DisplaysDto;
import io.pgs.svc.pref.service.DisplaysService;
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
@RequestMapping("/pref/displays")
public class DisplaysController {

    @Resource
    private DisplaysService displaysService;

    @PostMapping("/create")
    @ResponseBody
    public ModelAndView create(DisplaysDto displaysDto) {
        log.info("Let's Create start " + getClass().getName());
        Map<String, Object> result = new HashMap<>();
        if (empty(displaysDto.getId())) {
            return response(new ResultMapper(result, ServiceStatus.MSG_4001));
        }

        int successfulCount = 0;

        try {

            LocalDateTime now = LocalDateTime.now();
            displaysDto.setCreatedAt(Timestamp.valueOf(now));
            displaysDto.setUpdatedAt(Timestamp.valueOf(now));

            successfulCount = this.displaysService.create(displaysDto); // 등록예외 또는 중복에러 발생
            if (successfulCount == 0) { // 처리실패
                return response(new ResultMapper(result, ServiceStatus.MSG_3001));
            } else if (successfulCount == ServiceUtil.DUPLICATE_COUNT) { // 중복
                return response(new ResultMapper(result, ServiceStatus.MSG_3005));
            }
        } catch (Exception e) { // 처리실패
            log.error("displaysDto: {}", displaysDto, e);
            return response(new ResultMapper(result, ServiceStatus.MSG_3001));
        }
        return response(new ResultMapper(result, ServiceStatus.Successful));
    }

    @PostMapping("/update")
    @ResponseBody
    public ModelAndView update(DisplaysDto displaysDto) {
        log.info("Let's Update start " + getClass().getName());
        Map<String, Object> result = new HashMap<>();
        if (empty(displaysDto.getId())) {
            return response(new ResultMapper(result, ServiceStatus.MSG_4001));
        }

        int successfulCount = 0;

        try {

            LocalDateTime now = LocalDateTime.now();
            displaysDto.setUpdatedAt(Timestamp.valueOf(now));

            successfulCount = this.displaysService.update(displaysDto);
            if (successfulCount == 0) {
                return response(new ResultMapper(result, ServiceStatus.MSG_3002));
            }
        } catch (Exception e) {
            log.error("displaysDto: {}", displaysDto);
            return response(new ResultMapper(result, ServiceStatus.MSG_3002));
        }
        return response(new ResultMapper(result, ServiceStatus.Successful));
    }

    @PostMapping("/delete")
    @ResponseBody
    public ModelAndView delete(DisplaysDto displaysDto) {
        log.info("Let's start " + getClass().getName());
        Map<String, Object> result = new HashMap<>();
        if (empty(displaysDto.getId())) {
            return response(new ResultMapper(result, ServiceStatus.MSG_4001));
        }

        int successfulCount = 0;

        try {
            successfulCount = this.displaysService.delete(displaysDto.getId());
            if (successfulCount == 0) {
                return response(new ResultMapper(result, ServiceStatus.MSG_3003));
            }
        } catch (Exception e) {
            log.error("displaysDto: {}", displaysDto);
            return response(new ResultMapper(result, ServiceStatus.MSG_3003));
        }
        return response(new ResultMapper(result, ServiceStatus.Successful));
    }

    @GetMapping("/list")
    public ModelAndView list(DisplaysDto displaysDto) {
        Map<String, Object> result = new HashMap<>();

        List<DisplaysDto> list = this.displaysService.list(displaysDto);
        list = Optional.ofNullable(list).orElse(new ArrayList<>());
        for(DisplaysDto element: list) {
            String sectionIds = element.getSection_ids();
            String[] arraySectionIds = sectionIds.split(",");
            if(arraySectionIds != null && arraySectionIds.length > 1) {
                int totalCount = arraySectionIds.length;
                log.debug("arraySectionIds totalCount: "+ totalCount);
                String sectionIdsDisp = arraySectionIds[0] + "외 " + (totalCount - 1);
                log.debug("sectionIdsDisp: "+ sectionIdsDisp);
                element.setSection_ids_disp(sectionIdsDisp);
            } else if(arraySectionIds != null && arraySectionIds.length == 1) {
                element.setSection_ids_disp(sectionIds);
            }
        }

        result.put("list", list);
        return response(new ResultMapper(result, ServiceStatus.Successful), "svc/pref/displays-ListTemplate.html");
    }

}
