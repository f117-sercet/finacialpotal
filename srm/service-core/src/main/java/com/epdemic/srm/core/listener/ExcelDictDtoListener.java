package com.epdemic.srm.core.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.epdemic.srm.core.mapper.DictMapper;
import com.epdemic.srm.core.pojo.dto.ExcelDictDTO;
import com.epdemic.srm.core.pojo.entity.Dict;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:estic
 * @Date: 2021/6/18 9:51
 */
@Slf4j
@NoArgsConstructor
public class ExcelDictDtoListener extends AnalysisEventListener<ExcelDictDTO> {

    private DictMapper dictMapper;

    /**数据列表**/
    private List<ExcelDictDTO> list = new ArrayList<>();

    /**每个5条记录批量存储一次**/
    private static final int BATCH_COUNT = 5;

    public ExcelDictDtoListener(DictMapper dictMapper){
        this.dictMapper = dictMapper;
    }

    @Override
    public void invoke(ExcelDictDTO data, AnalysisContext context) {

        log.info("解析到一条记录：{}",data);
        //将数据存入数据列表
        list.add(data);
        if (list.size() >= BATCH_COUNT){
            saveData();
            list.clear();
        }
    }

    private void saveData() {

        log.info("{} 条数据被存储到数据库。。。。。",list.size());
        //调用mapper层额批量save方法:save list 对象
        dictMapper.insertBatch(list);
        log.info("{} 条数被存储到数据库成功！", list.size());
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

        //当最后剩余的数据记录数不足BATCH_COUNT时，我们最终一次性存储剩余数据
        saveData();
        log.info("所有数据解析完成！");
    }

}
