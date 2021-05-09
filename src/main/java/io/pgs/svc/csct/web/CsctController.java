package io.pgs.svc.csct.web;

import io.pgs.svc.syst.dto.CodesDto;
import io.pgs.svc.syst.service.CodeDetailsService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/csct")
public class CsctController {

    @Resource
    private CodeDetailsService codeDetailsService;

    @GetMapping
    public ModelAndView main() {
        ModelAndView mav = new ModelAndView("svc/csct/main.html");
        return mav;
    }

    @GetMapping("/board")
    public ModelAndView board(String active) {
        log.debug("menu active: {}", active);
        CodesDto codesDto = new CodesDto();
        codesDto.setId(3000);
        List<CodesDto> sitetypes = this.codeDetailsService.listEnabled(codesDto);

        sitetypes = Optional.ofNullable(sitetypes).orElse(new ArrayList<>());
        ModelAndView mav = new ModelAndView("svc/csct/board.html");
        mav.addObject("active", active);
        mav.addObject("sitetypes", sitetypes);

        return mav;
    }

}
