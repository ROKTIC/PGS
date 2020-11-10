
package io.pgs.svc.mycar.web;

import io.pgs.cmn.Pagination;
import io.pgs.cmn.ResultMapper;
import io.pgs.cmn.ServiceStatus;
import io.pgs.cmn.ServiceUtil;
import io.pgs.svc.pref.dto.UnitsDto;
import io.pgs.svc.mycar.dto.CarsDto;
import io.pgs.svc.mycar.service.CarsService;
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
    public ModelAndView carlist(CarsDto carsDto) {
        Map<String, Object> result = new HashMap<>();


        // 검색
        String searchCondition = carsDto.getSearchCondition();
        String searchValue = carsDto.getSearchValue();

        if(StringUtils.isEmpty(searchCondition)) {
            searchValue = "";
        }

        log.debug("searchCondition: {}", searchCondition);
        log.debug("searchValue: {}", searchValue);

        // 페이징처리
        // -- 페이징 처리

        result.put("searchCondition", searchCondition);
        result.put("searchValue", searchValue);
        result.put("carlist", );
        return response(new ResultMapper(result, ServiceStatus.Successful), "svc/mycar/main.html");

    }

}
