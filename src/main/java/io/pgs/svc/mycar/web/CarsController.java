
package io.pgs.svc.mycar.web;

import io.pgs.cmn.Pagination;
import io.pgs.cmn.ResultMapper;
import io.pgs.cmn.ServiceStatus;
import io.pgs.cmn.ServiceUtil;
import io.pgs.svc.mycar.dto.CarsDto;
import io.pgs.svc.mycar.service.CarsService;
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
import java.util.*;

import static io.pgs.cmn.ResponseUtil.empty;
import static io.pgs.cmn.ResponseUtil.response;

@Slf4j
@Controller
@RequestMapping("/mycar/cars")
public class CarsController {

    @Resource
    private CarsService carsService;

    @GetMapping("/carList")
    public ModelAndView carList(CarsDto carDto) {
        Map<String, Object> result = new HashMap<>();

        String searchValue = carDto.getSearchValue();
        String searchCondition = carDto.getSearchCondition();
        log.debug("searchValue: {}", searchValue);
        log.debug("searchCondition: {}", searchCondition);

        List<CarsDto> carList = this.carsService.carList(carDto);
        log.debug("carList: "+ carList);
        if(carList == null) {
            carList = new ArrayList<>();
        }

        result.put("carList", carList);
        result.put("searchCondition", searchCondition);
        result.put("searchValue", searchValue);
        return response(new ResultMapper(result, ServiceStatus.Successful), "svc/mycar/carList.html"); //mycar.html 안의 차량리스트 페이지

    }

}
