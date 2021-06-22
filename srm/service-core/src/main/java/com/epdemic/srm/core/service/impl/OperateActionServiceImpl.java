package com.epdemic.srm.core.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.epdemic.srm.core.mapper.OperateActionMapper;
import com.epdemic.srm.core.pojo.entity.OperateAction;
import com.epdemic.srm.core.service.OperateActionService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 操作行为记录表 服务实现类
 * </p>
 *
 * @author conbine
 * @since 2021-02-20
 */
@Service
public class OperateActionServiceImpl extends ServiceImpl<OperateActionMapper, OperateAction> implements OperateActionService {

}
