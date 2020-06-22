package io.pgs.svc.pref.web;

import io.pgs.cmn.Pagination;
import io.pgs.cmn.ResultMapper;
import io.pgs.cmn.ServiceStatus;
import io.pgs.svc.pref.dto.DisplaysDto;
import io.pgs.svc.pref.dto.SectionsDto;
import io.pgs.svc.pref.dto.UnitsDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.pgs.cmn.ResponseUtil.empty;
import static io.pgs.cmn.ResponseUtil.response;

@Slf4j
@Controller
@RequestMapping("/pref/displays")
public class DisplaysController {

    @PostMapping("/merge")
    @ResponseBody
    public ModelAndView merge(DisplaysDto displaysDto) {
        Map<String, Object> result = new HashMap<>();
        if (empty(displaysDto.getId()) || empty(displaysDto.getName())) {
            return response(new ResultMapper(result, ServiceStatus.MSG_4001));
        }

        return response(new ResultMapper(result, ServiceStatus.Successful));
    }

    @GetMapping("/pagelist")
    public ModelAndView pagelist(DisplaysDto displaysDto) {
        Map<String, Object> result = new HashMap<>();

        // 검색
        String searchCondition = displaysDto.getSearchCondition();
        String searchValue = displaysDto.getSearchValue();
        int curPage = displaysDto.getCurPage();
        if (curPage == 0) {
            curPage = 1;
        }

        log.debug("curPage: {}", curPage);
        log.debug("searchCondition: {}", searchCondition);
        log.debug("searchValue: {}", searchValue);

        int totalCount = 112;

        // 페이징처리
        Pagination pagination = new Pagination(totalCount, curPage);
        pagination.copyTo(displaysDto);

        List<UnitsDto> pagelist = new ArrayList<>();
        for(int i = 0; i < 112; i++) {
            pagelist.add(new UnitsDto());
        }

        result.put("searchCondition", searchCondition);
        result.put("searchValue", searchValue);
        result.put("pagelist", pagelist);
        result.put("totalCount", totalCount);
        result.put("pagination", pagination);
        return response(new ResultMapper(result, ServiceStatus.Successful), "svc/pref/displays-ListTemplate.html");
    }

}
