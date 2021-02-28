package com.burderly.topranking.controller;

import java.util.Map;
import com.google.common.collect.Maps;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class RestfulApiExceptionHandler {
    
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    @ResponseBody
    public Map<String, Object> requestExceptionHandler(HttpServletRequest request, MissingServletRequestParameterException exception) {
        Map<String, Object> error = Maps.newHashMap();
        error.put("code", 400);
        error.put("error", "parameter " + exception.getParameterName() + " error");
        return error;
    }
 
    
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Map<String, Object> exceptionHandler(HttpServletRequest request, Exception exception) throws Exception {
        Map<String, Object> error = Maps.newHashMap();
        error.put("code", 500);
        error.put("error", "System error");
        return error;
    }
}
