package io.pgs.svc.syst.web;

import io.pgs.cmn.Pagination;
import io.pgs.cmn.ResultMapper;
import io.pgs.cmn.ServiceStatus;
import io.pgs.svc.pref.dto.UnitsDto;
import io.pgs.svc.syst.dto.CodesDto;
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
import static io.pgs.svc.syst.service.CodesService.DUPLICATE_COUNT;

@Slf4j
@Controller
@RequestMapping("/syst/codes")
public class CodesController {

    @Resource
    private CodesService codesService;

    @PostMapping("/update")
    @ResponseBody
    public ModelAndView update(CodesDto codesDto) {
        log.info("Let's start " + getClass().getName());
        Map<String, Object> result = new HashMap<>();
        if (codesDto.getId() == 0 || empty(codesDto.getName())) {
            return response(new ResultMapper(result, ServiceStatus.MSG_4001));
        }

        int successfulCount = 0;

        try {
            LocalDateTime now = LocalDateTime.now();
            codesDto.setUpdatedAt(Timestamp.valueOf(now));

            successfulCount = this.codesService.update(codesDto);
            if (successfulCount == 0) {
                return response(new ResultMapper(result, ServiceStatus.MSG_3004));
            }
        } catch (Exception e) {
            log.error("CodesDto: {}", codesDto);
            return response(new ResultMapper(result, ServiceStatus.MSG_3004));
        }
        return response(new ResultMapper(result, ServiceStatus.Successful));
    }

    @PostMapping("/create")
    @ResponseBody
    public ModelAndView create(CodesDto codesDto) {
        log.info("Let's start " + getClass().getName());
        Map<String, Object> result = new HashMap<>();
        if (codesDto.getId() == 0 || empty(codesDto.getName())) {
            return response(new ResultMapper(result, ServiceStatus.MSG_4001));
        }

        int successfulCount = 0;

        try {

            LocalDateTime now = LocalDateTime.now();
            codesDto.setCreatedAt(Timestamp.valueOf(now));
            codesDto.setUpdatedAt(Timestamp.valueOf(now));

            successfulCount = this.codesService.create(codesDto); // 등록에러 또는 중복에러 발생
            if (successfulCount == 0) { // 처리실패
                return response(new ResultMapper(result, ServiceStatus.MSG_3001));
            } else if(successfulCount == DUPLICATE_COUNT) { // 중복
                return response(new ResultMapper(result, ServiceStatus.MSG_3005));
            }
        } catch (Exception e) { // 처리실패|예외
            log.error("CodesDto: {}", codesDto);
            return response(new ResultMapper(result, ServiceStatus.MSG_3001));
        }
        return response(new ResultMapper(result, ServiceStatus.Successful));
    }

    @PostMapping("/delete")
    @ResponseBody
    public ModelAndView delete(CodesDto codesDto) {
        log.info("Let's start " + getClass().getName());
        Map<String, Object> result = new HashMap<>();
        if (codesDto.getId() == 0) {
            return response(new ResultMapper(result, ServiceStatus.MSG_4001));
        }

        int successfulCount = 0;

        try {
            successfulCount = this.codesService.delete(codesDto.getId());
            if (successfulCount == 0) {
                return response(new ResultMapper(result, ServiceStatus.MSG_3003));
            }
        } catch (Exception e) {
            log.error("CodesDto: {}", codesDto);
            return response(new ResultMapper(result, ServiceStatus.MSG_3003));
        }
        return response(new ResultMapper(result, ServiceStatus.Successful));
    }

    @GetMapping("/pagelist")
    public ModelAndView pagelist(CodesDto codesDto) {
        Map<String, Object> result = new HashMap<>();

        int curPage = codesDto.getCurPage();
        if (curPage == 0) {
            curPage = 1;
        }

        log.debug("curPage: {}", curPage);

        // 페이징처리
        int totalCount = this.codesService.totalCount(codesDto);
        Pagination pagination = new Pagination(totalCount, curPage);
        pagination.copyTo(codesDto);
        List<CodesDto> pagelist = this.codesService.pagelist(codesDto);
        // -- 페이징 처리

        result.put("pagelist", pagelist);
        result.put("totalCount", totalCount);
        result.put("pagination", pagination);
        return response(new ResultMapper(result, ServiceStatus.Successful), "svc/syst/codes-List.html");
    }

}
