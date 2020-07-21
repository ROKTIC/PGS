package io.pgs.svc.syst.web;

import io.pgs.cmn.Pagination;
import io.pgs.cmn.ResultMapper;
import io.pgs.cmn.ServiceStatus;
import io.pgs.cmn.ServiceUtil;
import io.pgs.svc.pref.dto.UnitsDto;
import io.pgs.svc.syst.dto.CodesDto;
import io.pgs.svc.syst.dto.UsersDto;
import io.pgs.svc.syst.service.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.pgs.cmn.ResponseUtil.empty;
import static io.pgs.cmn.ResponseUtil.response;

@Slf4j
@Controller
@RequestMapping("/syst/users")
public class UsersController {

    @Resource
    private UsersService usersService;

    @Resource
    private PasswordEncoder passwordEncoder;

    @PostMapping("/create")
    @ResponseBody
    public ModelAndView create(UsersDto usersDto) {
        log.info("Let's start " + getClass().getName());
        Map<String, Object> result = new HashMap<>();
        if (empty(usersDto.getUsername()) || empty(usersDto.getAuthority())) {
            return response(new ResultMapper(result, ServiceStatus.MSG_4001));
        }

        int successfulCount = 0;

        try {

            LocalDateTime now = LocalDateTime.now();
            usersDto.setCreatedAt(Timestamp.valueOf(now));

            successfulCount = this.usersService.create(usersDto); // 등록예외 또는 중복에러 발생
            if (successfulCount == 0) { // 처리실패
                return response(new ResultMapper(result, ServiceStatus.MSG_3001));
            } else if (successfulCount == ServiceUtil.DUPLICATE_COUNT) { // 중복
                return response(new ResultMapper(result, ServiceStatus.MSG_3005));
            }
        } catch (Exception e) { // 처리실패
            log.error("usersDto: {}", usersDto);
            return response(new ResultMapper(result, ServiceStatus.MSG_3001));
        }
        return response(new ResultMapper(result, ServiceStatus.Successful));
    }

    @PostMapping("/delete")
    @ResponseBody
    public ModelAndView delete(UsersDto usersDto) {
        log.info("Let's start " + getClass().getName());
        Map<String, Object> result = new HashMap<>();
        if (empty(usersDto.getUsername())) {
            return response(new ResultMapper(result, ServiceStatus.MSG_4001));
        }

        int successfulCount = 0;

        try {
            successfulCount = this.usersService.delete(usersDto.getUsername());
            if (successfulCount == 0) {
                return response(new ResultMapper(result, ServiceStatus.MSG_3003));
            }
        } catch (Exception e) {
            log.error("UsersDto: {}", usersDto);
            return response(new ResultMapper(result, ServiceStatus.MSG_3003));
        }
        return response(new ResultMapper(result, ServiceStatus.Successful));
    }

    @PostMapping("/update")
    @ResponseBody
    public ModelAndView update(UsersDto usersDto) {
        log.info("Let's start " + getClass().getName());
        Map<String, Object> result = new HashMap<>();
        if (empty(usersDto.getUsername())) {
            return response(new ResultMapper(result, ServiceStatus.MSG_4001));
        }

        int successfulCount = 0;

        try {

            successfulCount = this.usersService.update(usersDto);
            if (successfulCount == 0) {
                return response(new ResultMapper(result, ServiceStatus.MSG_3002));
            }
        } catch (Exception e) {
            log.error("UsersDto: {}", usersDto);
            return response(new ResultMapper(result, ServiceStatus.MSG_3002));
        }
        return response(new ResultMapper(result, ServiceStatus.Successful));
    }

    @GetMapping("/list")
    public ModelAndView list(UsersDto usersDto) {
        Map<String, Object> result = new HashMap<>();

        List<UsersDto> list = this.usersService.list(usersDto);

        result.put("list", list);
        return response(new ResultMapper(result, ServiceStatus.Successful), "svc/syst/users-List.html");
    }

    @PostMapping("/password/change")
    @ResponseBody
    public ModelAndView changePassword(UsersDto usersDto) {
        log.info("Let's start " + getClass().getName());
        log.info("Params: {}", usersDto);
        Map<String, Object> result = new HashMap<>();
        if (empty(usersDto.getUsername()) || empty(usersDto.getPassword())) {
            return response(new ResultMapper(result, ServiceStatus.MSG_4001));
        }

        int successfulCount = 0;

        try {

            String encodedPassword = this.passwordEncoder.encode(usersDto.getPassword());
            log.debug("encodedPassword>>>"+ encodedPassword);

            usersDto.setPassword(encodedPassword);
            successfulCount = this.usersService.changePassword(usersDto);

        } catch (Exception e) {
            log.error("UsersDto: {}", usersDto);
            return response(new ResultMapper(result, ServiceStatus.MSG_3002));
        }
        return response(new ResultMapper(result, ServiceStatus.Successful));
    }
}
