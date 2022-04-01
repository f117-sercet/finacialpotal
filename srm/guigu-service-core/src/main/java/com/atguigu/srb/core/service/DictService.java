package com.atguigu.srb.core.service;

import com.atguigu.srb.core.pojo.dto.ExcelDictDTO;
import com.atguigu.srb.core.pojo.entity.Dict;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.InputStream;
import java.util.List;

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

    /**
     * 导出数据列表
     * @return
     */
    List<ExcelDictDTO> listDictData();

    /**
     * 展示父节点
     * @param parentId
     * @return
     */
    List<Dict> listByParentId(Long parentId);

    /**
     * 根据数字码查询
     * @param dictCode
     * @return
     */
    List<Dict> findByDictCode(String dictCode);

    /**
     * 根据父节点码和值获取名称
     * @param dictCode
     * @param value
     * @return
     */
    String getNameByParentDictCodeAndValue(String dictCode, Integer value);
}
