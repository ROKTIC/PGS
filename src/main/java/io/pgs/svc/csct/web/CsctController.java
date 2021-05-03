package io.pgs.svc.csct.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CsctController {


    @GetMapping("/as")
    public String main() {
        return "svc/as/main";
    }

}

