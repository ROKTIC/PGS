package io.pgs.svc.csct.web;

import io.pgs.cmn.Pagination;
import io.pgs.cmn.ResultMapper;
import io.pgs.cmn.ServiceStatus;
import io.pgs.cmn.ServiceUtil;
import io.pgs.svc.csct.dto.CsctDto;
import io.pgs.svc.csct.service.CsctService;
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
@RequestMapping("/csct/board")
public class BoardController {

    @Resource
    private CsctService csctService;

    @PostMapping("/create")
    @ResponseBody
    public ModelAndView create(CsctDto csctDto) {
        log.info("Let's start " + getClass().getName());
        Map<String, Object> result = new HashMap<>();
        if (empty(csctDto.getId())) {
            return response(new ResultMapper(result, ServiceStatus.MSG_4001));
        }

        int successfulCount = 0;

        try {

            log.debug("csct.update: {}", csctDto);

            LocalDateTime now = LocalDateTime.now();
            csctDto.setCreatedAt(Timestamp.valueOf(now));
            csctDto.setUpdatedAt(Timestamp.valueOf(now));

            successfulCount = this.csctService.create(csctDto); // 등록예외 또는 중복에러 발생
            if (successfulCount == 0) { // 처리실패
                return response(new ResultMapper(result, ServiceStatus.MSG_3001));
            } else if (successfulCount == ServiceUtil.DUPLICATE_COUNT) { // 중복
                return response(new ResultMapper(result, ServiceStatus.MSG_3005));
            }
        } catch (Exception e) { // 처리실패
            log.error("csctDto: {}", csctDto);
            return response(new ResultMapper(result, ServiceStatus.MSG_3001));
        }
        return response(new ResultMapper(result, ServiceStatus.Successful));
    }
}

    @PostMapping("/update")
    @ResponseBody
    public ModelAndView update(CsctDto csctDto) {
        log.info("Let's start " + getClass().getName());
        Map<String, Object> result = new HashMap<>();
        if (empty(csctDto.getId())) {
            return response(new ResultMapper(result, ServiceStatus.MSG_4001));
        }

        int successfulCount = 0;

        try {

            log.debug("unit.update: {}", csctDto);
            LocalDateTime now = LocalDateTime.now();
            csctDto.setUpdatedAt(Timestamp.valueOf(now));

            successfulCount = this.csctService.update(csctDto);
            log.debug("update.successfulCount: {}", successfulCount);
            if (successfulCount == 0) {
                return response(new ResultMapper(result, ServiceStatus.MSG_3002));
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("CsctDto: {}", csctDto);
            return response(new ResultMapper(result, ServiceStatus.MSG_3002));
        }
        return response(new ResultMapper(result, ServiceStatus.Successful));
    }

    @PostMapping("/delete")
    @ResponseBody
    public ModelAndView delete(CsctDto csctDto) {
        log.info("Let's start " + getClass().getName());
        Map<String, Object> result = new HashMap<>();
        if (empty(csctDto.getId())) {
            return response(new ResultMapper(result, ServiceStatus.MSG_4001));
        }

        int successfulCount = 0;

        try {
            successfulCount = this.csctService.delete(csctDto.getId());
            //if (successfulCount == 0) {
            //    return response(new ResultMapper(result, ServiceStatus.MSG_3003));
            //}
        } catch (Exception e) {
            log.error("unitDto: {}", csctDto);
            return response(new ResultMapper(result, ServiceStatus.MSG_3003));
        }
        return response(new ResultMapper(result, ServiceStatus.Successful));
    }

    @GetMapping("/pagelist")
    public ModelAndView pagelist(CsctDto csctDto) {
        Map<String, Object> result = new HashMap<>();

        // 검색
        String searchCondition = csctDto.getSearchCondition();
        int curPage = csctDto.getCurPage();
        if (curPage == 0) {
            curPage = 1;
        }


        log.debug("curPage: {}", curPage);
        log.debug("searchCondition: {}", searchCondition);

        // 페이징처리
        int totalCount = this.csctService.totalCount(csctDto);
        Pagination pagination = new Pagination(totalCount, curPage);
        pagination.copyTo(csctDto);
        List<CsctDto> pagelist = this.csctService.pagelist(csctDto);
        pagelist = Optional.ofNullable(pagelist).orElse(new ArrayList<>());
        for (CsctDto csctsDto : pagelist) {
            String incomingTime = ServiceUtil.trim(csctsDto.getIncoming_time());
            if (incomingTime != null && incomingTime.length() == 14) {
                LocalDateTime incomingTimeLDT = LocalDateTime.parse(incomingTime, DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
                incomingTime = incomingTimeLDT.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                csctsDto.setIncoming_time(incomingTime);
            }
        }
        result.put("searchCondition", searchCondition);
        result.put("pagelist", pagelist);
        result.put("totalCount", totalCount);
        result.put("pagination", pagination);
        return response(new ResultMapper(result, ServiceStatus.Successful), "svc/csct/board-ListTemplate.html");
    }


}