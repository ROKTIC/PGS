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

    @GetMapping("/list")
    public ModelAndView list(UsersDto usersDto) {
        Map<String, Object> result = new HashMap<>();

        List<UnitsDto> pagelist = new ArrayList<>();

        result.put("list", pagelist);
        return response(new ResultMapper(result, ServiceStatus.Successful), "svc/syst/users-List.html");
    }
}
