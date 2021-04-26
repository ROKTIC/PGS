package io.pgs.svc.as.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AsController {


    @GetMapping("/as")
    public String main() {
        return "svc/as/main";
    }

}