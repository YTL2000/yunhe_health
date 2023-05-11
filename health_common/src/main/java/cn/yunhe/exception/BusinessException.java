package cn.yunhe.exception;

import lombok.Data;

/**
 * 自定义异常
 * @version 1.0
 * @auther YTL
 * @className BusinessException
 * since 1.0
 */
@Data
public class BusinessException extends RuntimeException{
    private String message;

    public BusinessException(String message){
        super(message);
        this.message = message;
    }
}
