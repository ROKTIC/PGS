package io.pgs.svc.pref.web;

import io.pgs.cmn.Pagination;

import io.pgs.cmn.ResultMapper;
import io.pgs.cmn.ServiceStatus;
import io.pgs.cmn.ServiceUtil;
import io.pgs.svc.pref.dto.SectionsDto;
import io.pgs.svc.pref.dto.UnitsDto;
import io.pgs.svc.pref.service.SectionsService;
import io.pgs.svc.pref.service.UnitsService;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static io.pgs.cmn.ResponseUtil.*;

@Slf4j
@Controller
@RequestMapping("/pref/sections")
public class SectionsController {

    @Resource
    private SectionsService sectionsService;

    @PostMapping("/create")
    @ResponseBody
    public ModelAndView create(SectionsDto sectionsDto) {
        log.info("Let's Create start " + getClass().getName());
        Map<String, Object> result = new HashMap<>();
        if (empty(sectionsDto.getId())) {
            return response(new ResultMapper(result, ServiceStatus.MSG_4001));
        }

        int successfulCount = 0;

        try {

            LocalDateTime now = LocalDateTime.now();
            sectionsDto.setCreatedAt(Timestamp.valueOf(now));
            sectionsDto.setUpdatedAt(Timestamp.valueOf(now));

            successfulCount = this.sectionsService.create(sectionsDto); // 등록예외 또는 중복에러 발생
            if (successfulCount == 0) { // 처리실패
                return response(new ResultMapper(result, ServiceStatus.MSG_3001));
            } else if (successfulCount == ServiceUtil.DUPLICATE_COUNT) { // 중복
                return response(new ResultMapper(result, ServiceStatus.MSG_3005));
            }
        } catch (Exception e) { // 처리실패
            log.error("SectionsDto: {}", sectionsDto);
            return response(new ResultMapper(result, ServiceStatus.MSG_3001));
        }
        return response(new ResultMapper(result, ServiceStatus.Successful));
    }

    @PostMapping("/update")
    @ResponseBody
    public ModelAndView update(SectionsDto sectionsDto) {
        log.info("Let's Update start " + getClass().getName());
        Map<String, Object> result = new HashMap<>();
        if (empty(sectionsDto.getId())) {
            return response(new ResultMapper(result, ServiceStatus.MSG_4001));
        }

        int successfulCount = 0;

        try {

            LocalDateTime now = LocalDateTime.now();
            sectionsDto.setUpdatedAt(Timestamp.valueOf(now));

            successfulCount = this.sectionsService.update(sectionsDto);
            if (successfulCount == 0) {
                return response(new ResultMapper(result, ServiceStatus.MSG_3002));
            }
        } catch (Exception e) {
            log.error("SectionsDto: {}", sectionsDto);
            return response(new ResultMapper(result, ServiceStatus.MSG_3002));
        }
        return response(new ResultMapper(result, ServiceStatus.Successful));
    }

    @PostMapping("/delete")
    @ResponseBody
    public ModelAndView delete(SectionsDto sectionDto) {
        log.info("Let's start " + getClass().getName());
        Map<String, Object> result = new HashMap<>();
        if (empty(sectionDto.getId())) {
            return response(new ResultMapper(result, ServiceStatus.MSG_4001));
        }

        int successfulCount = 0;

        try {
            successfulCount = this.sectionsService.delete(sectionDto.getId());
            if (successfulCount == 0) {
                return response(new ResultMapper(result, ServiceStatus.MSG_3003));
            }
        } catch (Exception e) {
            log.error("SectionsDto: {}", sectionDto);
            return response(new ResultMapper(result, ServiceStatus.MSG_3003));
        }
        return response(new ResultMapper(result, ServiceStatus.Successful));
    }

    @GetMapping("/list")
    public ModelAndView list(SectionsDto sectionDto) {
        Map<String, Object> result = new HashMap<>();

        List<SectionsDto> list = this.sectionsService.list(sectionDto);

        result.put("list", list);
        return response(new ResultMapper(result, ServiceStatus.Successful), "svc/pref/sections-ListTemplate.html");
    }
}
