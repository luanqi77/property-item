package com.qf.config;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by 张正 on 2019-11-15.
 */
@ControllerAdvice
@ResponseBody
public class ExceptionController {

    @ExceptionHandler(value = UnauthorizedException.class)
    public String defaultErrorHandler(Exception e){
        System.out.println(e.getMessage());
        return "权限不足";
    }

    @ExceptionHandler(value =UnauthenticatedException.class )
    public String UnauthenticatedExceptionHandle(Exception e){
        System.out.println(e.getMessage());
        return "未登录";
    }
    @ExceptionHandler(value = AuthorizationException.class)
    public String AuthorizationExceptionHandle(Exception e){
        System.out.println(e.getMessage());
        return "权限验证出错";
    }

}
