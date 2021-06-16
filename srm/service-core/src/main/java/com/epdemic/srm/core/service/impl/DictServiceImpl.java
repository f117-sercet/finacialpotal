package com.epdemic.srm.core.service.impl;

import com.epdemic.srm.core.pojo.dto.ExcelDictDTO;
import com.epdemic.srm.core.pojo.entity.Dict;
import com.epdemic.srm.core.mapper.DictMapper;
import com.epdemic.srm.core.service.DictService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

/**
 * <p>
 * 数据字典 服务实现类
 * </p>
 *
 * @author combine
 * @since 2021-05-31
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {

    @Override
    public void importData(InputStream inputStream) {

    }

    @Override
    public List<ExcelDictDTO> listDictData() {
        return null;
    }

    @Override
    public List<Dict> listByParentId(Long parentId) {
        return null;
    }

    @Override
    public List<Dict> findByDictCode(String dictCode) {
        return null;
    }

    @Override
    public String getNameByParentDictCodeAndValue(String dictCode, Integer value) {
        return null;
    }
}
