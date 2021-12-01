package com.atguigu.srb.core.mapper;

import com.atguigu.srb.core.pojo.dto.ExcelDictDTO;
import com.atguigu.srb.core.pojo.entity.Dict;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 数据字典 Mapper 接口
 * </p>
 *
 * @author Helen
 * @since 2021-11-25
 */
public interface DictMapper extends BaseMapper<Dict> {
    /**
     * 批量插入
     * @param list
     */
    void insertBatch(List<ExcelDictDTO> list);

}
