package com.epdemic.srm.core.service;

import com.epdemic.srm.core.pojo.dto.ExcelDictDTO;
import com.epdemic.srm.core.pojo.entity.Dict;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.InputStream;
import java.util.List;

/**
 * <p>
 * 数据字典 服务类
 * </p>
 *
 * @author combine
 * @since 2021-05-31
 */
public interface DictService extends IService<Dict> {
    /**
     * 导入数据
     * @param inputStream
     */
    void importData(InputStream inputStream);

    /**
     * 呈数据
     * @return
     */
    List<ExcelDictDTO> listDictData();

    /**
     * 父id呈现数据
     * @param parentId
     * @return
     */

    List<Dict> listByParentId(Long parentId);

    /**
     * 铜鼓字典代号查找数据
     * @param dictCode
     * @return
     */

    List<Dict> findByDictCode(String dictCode);

    /**
     * 通过父数据和值来获取名字
     * @param dictCode
     * @param value
     * @return
     */

    String getNameByParentDictCodeAndValue(String dictCode, Integer value);

}
