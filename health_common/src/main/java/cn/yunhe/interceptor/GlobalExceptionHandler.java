package cn.yunhe.interceptor;

import cn.yunhe.entity.Result;
import cn.yunhe.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 处理自定义异常
 * @version 1.0
 * @auther YTL
 * @className GlobalExceptionHandler
 * since 1.0
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(value= BusinessException.class)
    @ResponseBody
    public Result processBusinessExcetion(BusinessException e){
        log.error("=====>>>发生了自定义异常信息："+e.getMessage());
        return new Result(false, e.getMessage());
    }

    /**
     * 处理系统异常
     */
    @ExceptionHandler(value= Exception.class)
    @ResponseBody
    public Result processExcetion(Exception e){
        e.printStackTrace();
        log.error("=====>>>发生了系统异常信息："+e.getMessage());
        return new Result(false, "发生未知异常！");
    }
}