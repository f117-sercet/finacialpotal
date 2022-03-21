package com.heepy.config;

import com.heepy.util.HfbException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Description： 全员异常处理
 *
 * @author: 段世超
 * @aate: Created in 2022/3/21 9:30
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String error(Exception e){
        e.printStackTrace();
        return "error";
    }

    /**
     * 自定义异常处理方法
     * @param e
     * @return
     */
    @ExceptionHandler(HfbException.class)
    public String error(HfbException e, Model model){
        model.addAttribute("message", e.getMessage());
        return "error";
    }
}
