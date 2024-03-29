package io.pgs.cmn;

import org.springframework.web.servlet.ModelAndView;
import static org.thymeleaf.util.StringUtils.isEmpty;

public final class ResponseUtil {
    private ResponseUtil(){}

    static final public <T>  ModelAndView response(ResultMapper<T> resultMapper) {
        return response(resultMapper, null);
    }

    static final public <T>  ModelAndView response(ResultMapper<T> resultMapper, String viewName) {
        ModelAndView modelAndView = null;
        if (viewName != null) {
            modelAndView = new ModelAndView(viewName);
        } else {
            modelAndView = new ModelAndView();
        }

        String status = resultMapper.getStatus();
        if (ServiceStatus.Successful.code.equals(status)) { // 성공 시 상태, 응답 값을 담아준다.
            modelAndView.addObject("status", resultMapper.getStatus());
            modelAndView.addObject("response", resultMapper.getResult());
        } else {
            modelAndView.addObject("status", resultMapper.getStatus());
            modelAndView.addObject("message", resultMapper.getMsg());
        }
        return modelAndView;
    }

    static final public boolean empty(final String target) {
        return isEmpty(target);
    }

}
