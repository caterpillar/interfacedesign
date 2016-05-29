package org.interfacedesign.resolver;

import org.apache.commons.lang3.StringUtils;
import org.interfacedesign.SimpleResponse;
import org.interfacedesign.base.exception.DesignException;
import org.interfacedesign.base.exception.ResponseStatusCode;
import org.interfacedesign.converter.SpringMvcJsonConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ExceptionResolver extends org.springframework.web.servlet.handler.SimpleMappingExceptionResolver {
    private static Logger logger = LoggerFactory.getLogger(ExceptionResolver.class);

    @Autowired
    private SpringMvcJsonConverter springMvcJsonConverter;

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response,
                                              Object handler, Exception ex) {
        response.setContentType("application/json;charset=UTF-8");
        logger.error("ExceptionResolver异常处理" , ex);
        if (ex instanceof DesignException) {//业务异常
            DesignException designException = (DesignException) ex;
            writeToResponse(response, designException.getStatus(), designException.getMessage());
        } else if (ex instanceof IllegalArgumentException) {//非法参数异常
            String msg = ex.getMessage();
            if(StringUtils.isEmpty(msg)) {
                msg = "非法的请求，请重试";
            }
            writeToResponse(response, ResponseStatusCode.BAD_REQUEST, msg);
        } else {//其他异常
            String otherErrorMessage = "网络出现异常，请稍后再试";
            writeToResponse(response, ResponseStatusCode.INTERNAL_SERVER_ERROR, otherErrorMessage);
        }

        return null;
    }

    private void writeToResponse(HttpServletResponse response, int status, String message) {
        try {
            response.setStatus(status);
            springMvcJsonConverter.getMapper().writeValue(response.getOutputStream(),
                    new SimpleResponse().putMessage("message", message));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getOrder() {
        return 0;
    }

}
