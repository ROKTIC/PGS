
package io.pgs.svc.mycar.web;

import io.pgs.cmn.Pagination;
import io.pgs.cmn.ResultMapper;
import io.pgs.cmn.ServiceStatus;
import io.pgs.cmn.ServiceUtil;
import io.pgs.svc.pref.dto.UnitsDto;
import io.pgs.svc.mycar.dto.CarsDto;
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
@RequestMapping("/mycar/cars")

public class CarsController {

    @GetMapping("/carlist")
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
            searchValue = "";
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
