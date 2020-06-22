package io.pgs.svc.syst.web;

import io.pgs.cmn.Pagination;
import io.pgs.cmn.ResultMapper;
import io.pgs.cmn.ServiceStatus;
import io.pgs.svc.pref.dto.UnitsDto;
import io.pgs.svc.syst.dto.UsersDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.pgs.cmn.ResponseUtil.response;

@Slf4j
@Controller
@RequestMapping("/syst/users")
public class UsersController {

    @GetMapping("/pagelist")
    public ModelAndView pagelist(UsersDto usersDto) {
        Map<String, Object> result = new HashMap<>();

        int curPage = usersDto.getCurPage();
        if (curPage == 0) {
            curPage = 1;
        }

        log.debug("curPage: {}", curPage);

        int totalCount = 60;

        // 페이징처리
        Pagination pagination = new Pagination(totalCount, curPage);
        pagination.copyTo(usersDto);

        List<UnitsDto> pagelist = new ArrayList<>();
        for(int i = 0; i < 60; i++) {
            pagelist.add(new UnitsDto());
        }

        result.put("pagelist", pagelist);
        result.put("totalCount", totalCount);
        result.put("pagination", pagination);
        return response(new ResultMapper(result, ServiceStatus.Successful), "svc/syst/users-List.html");
    }
}
