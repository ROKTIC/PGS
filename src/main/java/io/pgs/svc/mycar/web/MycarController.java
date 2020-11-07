package io.pgs.svc.mycar.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Slf4j
@Controller
@RequestMapping("/mycar") // 내 차 찾기 mapping
public class MycarController {

    @GetMapping
    public ModelAndView main() {
        ModelAndView mav = new ModelAndView("svc/mycar/main.html"); //mycar의 main.html
        return mav;
    }

}