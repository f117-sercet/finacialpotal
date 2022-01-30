package com.atguigu.srb.core.service;

import com.atguigu.srb.core.pojo.Vo.BorrowerAttachVO;
import com.atguigu.srb.core.pojo.entity.BorrowerAttach;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 借款人上传资源表 服务类
 * </p>
 *
 * @author Helen
 * @since 2021-11-25
 */
public interface BorrowerAttachService extends IService<BorrowerAttach> {

    List<BorrowerAttachVO> selectBorrowerAttachVoList(Long borrowerId);
}
