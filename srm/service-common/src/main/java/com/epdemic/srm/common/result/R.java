package com.epdemic.srm.common.result;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 统一结果处理
 * @Author:estic
 * @Date: 2021/6/1 10:01
 */
@Data
public class R {

    private Integer code;
    private String messgae;
    private Map<String,Object> data = new HashMap<>();

    /**
     * 构造函数私有化
     */
    private R(){}

    /**
     * 返回成功结果
     * @return
     */
    public static R ok(){

        R r = new R();
        r.setCode(ResponseEnum.SUCCESS.getCode());
        r.setMessgae(ResponseEnum.SUCCESS.getMessage());
        return r;

    }

    /**
     * 返回失败结果
     * @return
     */
    public static R error(){
        R r = new R();
        r.setCode(ResponseEnum.ERROR.getCode());
        r.setMessgae(ResponseEnum.ERROR.getMessage());
        return r;
    }

    /**
     * 设置特定结果
     * @param responseEnum
     * @return
     */
    public static R setResult(ResponseEnum responseEnum){
        R r = new R();
        r.setCode(responseEnum.getCode());
        r.setMessgae(responseEnum.getMessage());
        return r;
    }

    /**
     * 特定结果
     * @param key
     * @param value
     * @return
     */
    public R data(String key,Object value){
        this.data.put(key, value);
        return this;
    }

    public R data(Map<String,Object> map){

        this.setData(map);
        return this;
    }

    /**
     * 设置特定的响应信息
     * @param messgae
     * @return
     */
    public R message(String messgae){
         this.setMessgae(messgae);
         return this;
    }

    /**
     * 设置特定的响应码
     * @param code
     * @return
     */
    public  R code(Integer code){

        this.setCode(code);
        return this;
    }

}
