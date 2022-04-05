package com.atguigu.srb.core.service.impl;

import com.atguigu.srb.core.enums.BorrowerStatusEnum;
import com.atguigu.srb.core.mapper.BorrowerAttachMapper;
import com.atguigu.srb.core.mapper.UserInfoMapper;
import com.atguigu.srb.core.mapper.UserIntegralMapper;
import com.atguigu.srb.core.pojo.Vo.BorrowerApprovalVO;
import com.atguigu.srb.core.pojo.Vo.BorrowerDetailVO;
import com.atguigu.srb.core.pojo.Vo.BorrowerVO;
import com.atguigu.srb.core.pojo.entity.Borrower;
import com.atguigu.srb.core.mapper.BorrowerMapper;
import com.atguigu.srb.core.pojo.entity.BorrowerAttach;
import com.atguigu.srb.core.pojo.entity.UserInfo;
import com.atguigu.srb.core.service.BorrowerAttachService;
import com.atguigu.srb.core.service.BorrowerService;
import com.atguigu.srb.core.service.DictService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 借款人 服务实现类
 * </p>
 *
 * @author Helen
 * @since 2021-11-25
 */
@Service
public class BorrowerServiceImpl extends ServiceImpl<BorrowerMapper, Borrower> implements BorrowerService {


    @Resource
    private UserInfoMapper userInfoMapper;

    @Resource
    private BorrowerAttachMapper borrowerAttachMapper;

    @Resource
    private BorrowerAttachService borrowerAttachService;

    @Resource
    private DictService dictService;

    @Resource
    private UserIntegralMapper userIntegralMapper;

   @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveBorrowerVOByUserId(BorrowerVO borrowerVO, Long userId) {

       //获取用户基本信息
       UserInfo userInfo = userInfoMapper.selectById(userId);

       //保存借款人信息
       Borrower borrower = new Borrower();
       BeanUtils.copyProperties(borrowerVO, borrower);
       borrower.setUserId(userId);
       borrower.setName(userInfo.getName());
       borrower.setIdCard(userInfo.getIdCard());
       borrower.setMobile(userInfo.getMobile());
       borrower.setStatus(BorrowerStatusEnum.AUTH_RUN.getStatus()); //认证中
       baseMapper.insert(borrower);

       //保存附件
       List<BorrowerAttach> borrowerAttachList = borrowerVO.getBorrowerAttachList();
       borrowerAttachList.forEach(borrowerAttach -> {
           borrowerAttach.setBorrowerId(borrower.getId());
           borrowerAttachMapper.insert(borrowerAttach);
       });

       //更新userInfo中的借款人认证状态
       userInfo.setBorrowAuthStatus(BorrowerStatusEnum.AUTH_RUN.getStatus());
       userInfoMapper.updateById(userInfo);
   }

    @Override
    public Integer getStatusByUserId(Long userId) {
        return null;
    }

    @Override
    public IPage<Borrower> listPage(Page<Borrower> pageParam, String keyword) {
        return null;
    }

    @Override
    public BorrowerDetailVO getBorrowerDetailVOById(Long id) {
        return null;
    }

    @Override
    public void approval(BorrowerApprovalVO borrowerApprovalVO) {

    }
}
