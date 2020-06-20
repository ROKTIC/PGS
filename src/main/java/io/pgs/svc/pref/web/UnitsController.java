package io.pgs.svc.pref.web;

import io.pgs.cmn.Pagination;
import io.pgs.cmn.ResponseUtil;
import io.pgs.cmn.ResultMapper;
import io.pgs.cmn.ServiceStatus;
import io.pgs.svc.pref.dto.UnitDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import static org.thymeleaf.util.StringUtils.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/pref/units")
public class UnitsController {

    @PostMapping("/merge")
    @ResponseBody
    public ModelAndView merge(UnitDto unitDto) {
        Map<String, Object> result = new HashMap<>();
        if (isEmpty(unitDto.getId())) {
            return ResponseUtil.response(new ResultMapper(result, ServiceStatus.MSG_4001));
        }

        return ResponseUtil.response(new ResultMapper(result, ServiceStatus.Successful));
    }

    @GetMapping("/pagelist")
    public ModelAndView pagelist(UnitDto unitDto) {
        Map<String, Object> result = new HashMap<>();

        // 검색
        String searchCondition = unitDto.getSearchCondition();
        String searchValue = unitDto.getSearchValue();
        int curPage = unitDto.getCurPage();
        if (curPage == 0) {
            curPage = 1;
        }

        log.debug("curPage: {}", curPage);
        log.debug("searchCondition: {}", searchCondition);
        log.debug("searchValue: {}", searchValue);

        int totalCount = 112;

        // 페이징처리
        Pagination pagination = new Pagination(totalCount, curPage);
        pagination.copyTo(unitDto);

        List<UnitDto> pagelist = new ArrayList<>();
        for(int i = 0; i < 112; i++) {
            pagelist.add(new UnitDto());
        }

        result.put("searchCondition", searchCondition);
        result.put("searchValue", searchValue);
        result.put("pagelist", pagelist);
        result.put("totalCount", totalCount);
        result.put("pagination", pagination);
        return ResponseUtil.response(new ResultMapper(result, ServiceStatus.Successful), "svc/pref/units-ListTemplate.html");
    }








}
