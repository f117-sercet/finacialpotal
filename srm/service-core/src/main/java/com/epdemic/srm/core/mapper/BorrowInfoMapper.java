package com.epdemic.srm.core.mapper;

import com.epdemic.srm.core.pojo.entity.BorrowInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 借款信息表 Mapper 接口
 * </p>
 *
 * @author combine
 * @since 2021-05-31
 */
public interface BorrowInfoMapper extends BaseMapper<BorrowInfo> {
    /**
     * 借款人信息
     * @return
     */
    List<BorrowInfo> selectBorrowInfoList();
}
