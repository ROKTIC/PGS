package io.pgs.svc.pref.web;

import io.pgs.cmn.Pagination;
import io.pgs.cmn.ResultMapper;
import io.pgs.cmn.ServiceStatus;
import io.pgs.cmn.ServiceUtil;
import io.pgs.svc.pref.dto.UnitsDto;
import io.pgs.svc.pref.service.UnitsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static io.pgs.cmn.ResponseUtil.empty;
import static io.pgs.cmn.ResponseUtil.response;

@Slf4j
@Controller
@RequestMapping("/pref/units")
public class UnitsController {

    @Resource
    private UnitsService unitsService;

    @PostMapping("/create")
    @ResponseBody
    public ModelAndView create(UnitsDto unitsDto) {
        log.info("Let's start " + getClass().getName());
        Map<String, Object> result = new HashMap<>();
        if (empty(unitsDto.getId())) {
            return response(new ResultMapper(result, ServiceStatus.MSG_4001));
        }

        int successfulCount = 0;

        try {

            LocalDateTime now = LocalDateTime.now();
            unitsDto.setCreatedAt(Timestamp.valueOf(now));
            unitsDto.setUpdatedAt(Timestamp.valueOf(now));

            successfulCount = this.unitsService.create(unitsDto); // 등록예외 또는 중복에러 발생
            if (successfulCount == 0) { // 처리실패
                return response(new ResultMapper(result, ServiceStatus.MSG_3001));
            } else if (successfulCount == ServiceUtil.DUPLICATE_COUNT) { // 중복
                return response(new ResultMapper(result, ServiceStatus.MSG_3005));
            }
        } catch (Exception e) { // 처리실패
            log.error("unitsDto: {}", unitsDto);
            return response(new ResultMapper(result, ServiceStatus.MSG_3001));
        }
        return response(new ResultMapper(result, ServiceStatus.Successful));
    }

    @PostMapping("/update")
    @ResponseBody
    public ModelAndView update(UnitsDto unitsDto) {
        log.info("Let's start " + getClass().getName());
        Map<String, Object> result = new HashMap<>();
        if (empty(unitsDto.getId())) {
            return response(new ResultMapper(result, ServiceStatus.MSG_4001));
        }

        int successfulCount = 0;

        try {

            LocalDateTime now = LocalDateTime.now();
            unitsDto.setUpdatedAt(Timestamp.valueOf(now));

            successfulCount = this.unitsService.update(unitsDto);
            if (successfulCount == 0) {
                return response(new ResultMapper(result, ServiceStatus.MSG_3002));
            }
        } catch (Exception e) {
            log.error("UnitsDto: {}", unitsDto);
            return response(new ResultMapper(result, ServiceStatus.MSG_3002));
        }
        return response(new ResultMapper(result, ServiceStatus.Successful));
    }

    @PostMapping("/delete")
    @ResponseBody
    public ModelAndView delete(UnitsDto unitDto) {
        log.info("Let's start " + getClass().getName());
        Map<String, Object> result = new HashMap<>();
        if (empty(unitDto.getId())) {
            return response(new ResultMapper(result, ServiceStatus.MSG_4001));
        }

        int successfulCount = 0;

        try {
            successfulCount = this.unitsService.delete(unitDto.getId());
            //if (successfulCount == 0) {
            //    return response(new ResultMapper(result, ServiceStatus.MSG_3003));
            //}
        } catch (Exception e) {
            log.error("unitDto: {}", unitDto);
            return response(new ResultMapper(result, ServiceStatus.MSG_3003));
        }
        return response(new ResultMapper(result, ServiceStatus.Successful));
    }

    @GetMapping("/pagelist")
    public ModelAndView pagelist(UnitsDto unitDto) {
        Map<String, Object> result = new HashMap<>();

        // 검색
        String searchCondition = unitDto.getSearchCondition();
        String searchValue = unitDto.getSearchValue();
        int curPage = unitDto.getCurPage();
        if (curPage == 0) {
            curPage = 1;
        }
        if(StringUtils.isEmpty(searchCondition)) {
            searchCondition = "car_no";
        }

        log.debug("curPage: {}", curPage);
        log.debug("searchCondition: {}", searchCondition);
        log.debug("searchValue: {}", searchValue);

        // 페이징처리
        int totalCount = this.unitsService.totalCount(unitDto);
        Pagination pagination = new Pagination(totalCount, curPage);
        pagination.copyTo(unitDto);
        List<UnitsDto> pagelist = this.unitsService.pagelist(unitDto);
        pagelist = Optional.ofNullable(pagelist).orElse(new ArrayList<>());
        for(UnitsDto unitsDto : pagelist) {
            String incomingTime = ServiceUtil.trim(unitsDto.getIncoming_time());
            if(incomingTime != null && incomingTime.length() == 14) {
                LocalDateTime incomingTimeLDT = LocalDateTime.parse(incomingTime, DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
                incomingTime = incomingTimeLDT.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                unitsDto.setIncoming_time(incomingTime);
            }
        }
        // -- 페이징 처리

        result.put("searchCondition", searchCondition);
        result.put("searchValue", searchValue);
        result.put("pagelist", pagelist);
        result.put("totalCount", totalCount);
        result.put("pagination", pagination);
        return response(new ResultMapper(result, ServiceStatus.Successful), "svc/pref/units-ListTemplate.html");
    }








}
