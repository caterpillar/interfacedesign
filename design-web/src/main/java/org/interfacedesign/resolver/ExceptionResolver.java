package org.interfacedesign.resolver;

import com.fasterxml.jackson.annotation.JsonValue;
import org.apache.commons.lang3.StringUtils;
import org.interfacedesign.auth.domain.model.PasswordAuthUser;
import org.interfacedesign.base.exception.DesignException;
import org.interfacedesign.converter.SpringMvcJsonConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExceptionResolver extends org.springframework.web.servlet.handler.SimpleMappingExceptionResolver {
    private static Logger logger = LoggerFactory.getLogger(ExceptionResolver.class);

    private String errorView = "jsonView";

    @Autowired
    private SpringMvcJsonConverter springMvcJsonConverter;

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response,
                                              Object handler, Exception ex) {
        response.setContentType("application/json;charset=UTF-8");
        logger.error("ExceptionResolver异常处理" , ex);
        //异常跳转 视图
//        ModelAndView modelAndView = new ModelAndView(errorView);
        if (ex instanceof DesignException) {//业务异常
            response.setStatus(400);
            try {
                springMvcJsonConverter.getMapper().writeValue(response.getOutputStream(), new SimpleResponse().putMessage("message", ex.getMessage()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return returnValue(response, ex.getMessage());
        } else if (ex instanceof IllegalArgumentException) {//业务异常
            String msg = ex.getMessage();
            if(StringUtils.isEmpty(msg)) {
                msg  = "网络出现异常，请稍后再试";
            }
            return returnValue(response, msg);
        } else {//其他异常
            String otherErrorMessage = "网络出现异常，请稍后再试";
            logger.error(otherErrorMessage, ex);
            return returnValue(response, otherErrorMessage);
        }
    }

    private ModelAndView returnValue(HttpServletResponse response, Object returnValueData){
        try {
            BaseResponse baseResponse = new BaseResponse("001");
            baseResponse.setResponseData(returnValueData);
            springMvcJsonConverter.getMapper().writeValue(response.getOutputStream(), baseResponse);
        } catch (IOException e) {
            logger.error("返回错误", e);
        }
        return null;
    }

    @Override
    public int getOrder() {
        return 0;
    }


    public String getErrorView() {
        return errorView;
    }

    public void setErrorView(String errorView) {
        this.errorView = errorView;
    }
}

class SimpleResponse {
    private final Map<String, Object> _this = new HashMap<String, Object>();

    public SimpleResponse putMessage(String key, Object value) {
        _this.put(key, value);
        return this;
    }

    @JsonValue
    public Map<String, Object> toMap() {
        return _this;
    }
}

class BaseResponse {
    private final String status;
    private final Map<String, Object> _this = new HashMap<String, Object>();

    public BaseResponse(String status) {
        this.status = status;
        _this.put("status", status);
    }

    public void setResponseData(Object data) {
        _this.put("message", data);
        _this.put("data", new PasswordAuthUser());
    }

    @JsonValue
    public Map<String,Object> toMap(){
        return _this;
    }
}