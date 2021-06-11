package com.epdemic.srm.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.epdemic.srm.core.pojo.entity.BorrowerAttach;
import com.epdemic.srm.core.mapper.BorrowerAttachMapper;
import com.epdemic.srm.core.pojo.vo.BorrowerAttachVO;
import com.epdemic.srm.core.service.BorrowerAttachService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 借款人上传资源表 服务实现类
 * </p>
 *
 * @author combine
 * @since 2021-05-31
 */
@Service
public class BorrowerAttachServiceImpl extends ServiceImpl<BorrowerAttachMapper, BorrowerAttach> implements BorrowerAttachService {

    @Override
    public List<BorrowerAttachVO> selectBorrowerAttachVolist(Long borrowerId) {

        QueryWrapper<BorrowerAttach> borrowerAttachQueryWrapper = new QueryWrapper<>();
        borrowerAttachQueryWrapper.eq("borrower_id", borrowerId);
        List<BorrowerAttach> borrowerAttachList = baseMapper.selectList(borrowerAttachQueryWrapper);
        List<BorrowerAttachVO> borrowerAttachVOList = new ArrayList<>();
        borrowerAttachList.forEach(borrowerAttach -> {
            BorrowerAttachVO borrowerAttachVO = new BorrowerAttachVO();
            borrowerAttachVO.setImageType(borrowerAttach.getImageType());
            borrowerAttachVO.setImageUrl(borrowerAttach.getImageUrl());

            borrowerAttachVOList.add(borrowerAttachVO);
        });

        return  borrowerAttachVOList;
    }
}
