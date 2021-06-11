package com.epdemic.srm.core.service;

import com.epdemic.srm.core.pojo.entity.BorrowerAttach;
import com.baomidou.mybatisplus.extension.service.IService;
import com.epdemic.srm.core.pojo.vo.BorrowerAttachVO;

import java.util.List;

/**
 * <p>
 * 借款人上传资源表 服务类
 * </p>
 *
 * @author combine
 * @since 2021-05-31
 */
public interface BorrowerAttachService extends IService<BorrowerAttach> {
    /**
     * 选择某一借款信息
     * @param borrowerId
     * @return
     */
    List<BorrowerAttachVO> selectBorrowerAttachVolist(Long borrowerId);

}
