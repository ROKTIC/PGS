package io.pgs.svc.csct.web;

import io.pgs.svc.syst.dto.CodesDto;
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

    @GetMapping
    public ModelAndView main() {
        ModelAndView mav = new ModelAndView("svc/csct/main.html");
        return mav;
    }

    @GetMapping("/board")
    public ModelAndView board(String active) {
        log.debug("menu active: {}", active);

        ModelAndView mav = new ModelAndView("svc/csct/board.html");
        mav.addObject("active", active);

        return mav;
    }

}
