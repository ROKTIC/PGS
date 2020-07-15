package io.pgs.svc.syst.web;

import io.pgs.cmn.Pagination;
import io.pgs.cmn.ResultMapper;
import io.pgs.cmn.ServiceStatus;
import io.pgs.cmn.ServiceUtil;
import io.pgs.svc.pref.dto.UnitsDto;
import io.pgs.svc.syst.dto.CodesDto;
import io.pgs.svc.syst.service.CodeDetailsService;
import io.pgs.svc.syst.service.CodesService;
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

import static io.pgs.cmn.ResponseUtil.empty;
import static io.pgs.cmn.ResponseUtil.response;

@Slf4j
@Controller
@RequestMapping("/syst/code/details")
public class CodeDetailsController {

    @Resource
    private CodeDetailsService codeDetailsService;

    @Resource
    private CodesService codesService;

    @PostMapping("/create")
    @ResponseBody
    public ModelAndView create(CodesDto codesDto) {
        log.info("Let's start " + getClass().getName());
        Map<String, Object> result = new HashMap<>();
        if (codesDto.getId() == 0 || codesDto.getSid() == 0) {
            return response(new ResultMapper(result, ServiceStatus.MSG_4001));
        }

        int successfulCount = 0;

        try {

            LocalDateTime now = LocalDateTime.now();
            codesDto.setCreatedAt(Timestamp.valueOf(now));
            codesDto.setUpdatedAt(Timestamp.valueOf(now));

            successfulCount = this.codeDetailsService.create(codesDto); // 등록에러 또는 중복에러 발생
            if (successfulCount == 0) { // 처리실패
                return response(new ResultMapper(result, ServiceStatus.MSG_3001));
            } else if(successfulCount == ServiceUtil.DUPLICATE_COUNT) { // 중복
                return response(new ResultMapper(result, ServiceStatus.MSG_3005));
            }
        } catch (Exception e) { // 처리실패|예외
            log.error("CodesDto: {}", codesDto);
            return response(new ResultMapper(result, ServiceStatus.MSG_3001));
        }
        return response(new ResultMapper(result, ServiceStatus.Successful));
    }

    @PostMapping("/update")
    @ResponseBody
    public ModelAndView update(CodesDto codesDto) {
        log.info("Let's start " + getClass().getName());
        Map<String, Object> result = new HashMap<>();
        if (codesDto.getId() == 0 || codesDto.getSid() == 0) {
            return response(new ResultMapper(result, ServiceStatus.MSG_4001));
        }

        int successfulCount = 0;

        try {
            LocalDateTime now = LocalDateTime.now();
            codesDto.setUpdatedAt(Timestamp.valueOf(now));

            successfulCount = this.codeDetailsService.update(codesDto);
            if (successfulCount == 0) {
                return response(new ResultMapper(result, ServiceStatus.MSG_3004));
            }
        } catch (Exception e) {
            log.error("CodesDto: {}", codesDto);
            return response(new ResultMapper(result, ServiceStatus.MSG_3004));
        }
        return response(new ResultMapper(result, ServiceStatus.Successful));
    }

    @PostMapping("/delete")
    @ResponseBody
    public ModelAndView delete(CodesDto codesDto) {
        log.info("Let's start " + getClass().getName());
        Map<String, Object> result = new HashMap<>();
        if (codesDto.getId() == 0 || codesDto.getSid() == 0) {
            return response(new ResultMapper(result, ServiceStatus.MSG_4001));
        }

        int successfulCount = 0;

        try {
            successfulCount = this.codeDetailsService.delete(codesDto);
            if (successfulCount == 0) {
                return response(new ResultMapper(result, ServiceStatus.MSG_3003));
            }
        } catch (Exception e) {
            log.error("CodesDto: {}", codesDto);
            return response(new ResultMapper(result, ServiceStatus.MSG_3003));
        }
        return response(new ResultMapper(result, ServiceStatus.Successful));
    }

    @GetMapping("/list")
    public ModelAndView list(CodesDto codesDto) {
        log.info("Let's start " + getClass().getName());
        Map<String, Object> result = new HashMap<>();

        log.debug("codesDto: {}", codesDto);
        int id = codesDto.getId();

        List<CodesDto> list = this.codeDetailsService.list(codesDto);
        codesDto = this.codesService.info(id);

        result.put("list", list);
        result.put("code", codesDto);
        return response(new ResultMapper(result, ServiceStatus.Successful), "svc/syst/codeDetails-List.html");
    }



}
