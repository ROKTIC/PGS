package io.pgs.svc.pref.web;

import io.pgs.cmn.Pagination;
import io.pgs.cmn.ResultMapper;
import io.pgs.cmn.ServiceStatus;
import io.pgs.svc.pref.dto.UnitsDto;
import io.pgs.svc.pref.service.UnitsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.pgs.cmn.ResponseUtil.empty;
import static io.pgs.cmn.ResponseUtil.response;

@Slf4j
@Controller
@RequestMapping("/pref/units")
public class UnitsController {

    @Resource
    private UnitsService unitsService;

    @PostMapping("/merge")
    @ResponseBody
    public ModelAndView merge(UnitsDto unitDto) {
        Map<String, Object> result = new HashMap<>();
        if (empty(unitDto.getId())) {
            return response(new ResultMapper(result, ServiceStatus.MSG_4001));
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

        log.debug("curPage: {}", curPage);
        log.debug("searchCondition: {}", searchCondition);
        log.debug("searchValue: {}", searchValue);

        // 페이징처리
        int totalCount = this.unitsService.totalCount(unitDto);
        Pagination pagination = new Pagination(totalCount, curPage);
        pagination.copyTo(unitDto);
        List<UnitsDto> pagelist = this.unitsService.pagelist(unitDto);
        // -- 페이징 처리

        result.put("searchCondition", searchCondition);
        result.put("searchValue", searchValue);
        result.put("pagelist", pagelist);
        result.put("totalCount", totalCount);
        result.put("pagination", pagination);
        return response(new ResultMapper(result, ServiceStatus.Successful), "svc/pref/units-ListTemplate.html");
    }








}
