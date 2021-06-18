package com.epdemic.srm.core.mapper;

import com.epdemic.srm.core.pojo.dto.ExcelDictDTO;
import com.epdemic.srm.core.pojo.entity.Dict;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 数据字典 Mapper 接口
 * </p>
 *
 * @author combine
 * @since 2021-05-31
 */
public interface DictMapper extends BaseMapper<Dict> {
    /**
     * 批量插入信息
     * @param list
     */
    void insertBatch(List<ExcelDictDTO> list);
}
