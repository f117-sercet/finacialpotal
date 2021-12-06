package com.atguigu.srb.core.service;

import com.atguigu.srb.core.pojo.entity.Dict;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.InputStream;

/**
 * <p>
 * 数据字典 服务类
 * </p>
 *
 * @author Helen
 * @since 2021-11-25
 */
public interface DictService extends IService<Dict> {

    /**
     * @author:estic
     * @param inputStream
     * 导入数据
     */
    void importData(InputStream inputStream);

}
