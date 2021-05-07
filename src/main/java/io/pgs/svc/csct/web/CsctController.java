package io.pgs.svc.csct.web;

import lombok.extern.slf4j.Slf4j;

import io.pgs.svc.csct.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Slf4j
@Controller
@RequestMapping("/csct") // 내 차 찾기 mapping
public class CsctController {

    @GetMapping
    public ModelAndView main() {
        ModelAndView mav = new ModelAndView("svc/csct/main.html");
        return mav;
    }

}
