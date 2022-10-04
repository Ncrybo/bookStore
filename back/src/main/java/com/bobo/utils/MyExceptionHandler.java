package com.bobo.utils;

import com.bobo.entity.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result error(HttpServletRequest req, HttpServletResponse resp, Exception e) {
        return Result.fail(e.toString());
    }

    @ExceptionHandler(value = CommonException.class)
    @ResponseBody
    public Result error(HttpServletRequest req, HttpServletResponse resp, CommonException e) {
        CommonException ce = (CommonException)e;
        return ce.getResult();
    }
}