package com.epdemic.srm.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.epdemic.srm.common.exception.Assert;
import com.epdemic.srm.common.result.ResponseEnum;
import com.epdemic.srm.core.enums.BorrowInfoStatusEnum;
import com.epdemic.srm.core.enums.BorrowerStatusEnum;
import com.epdemic.srm.core.enums.UserBindEnum;
import com.epdemic.srm.core.mapper.BorrowerMapper;
import com.epdemic.srm.core.mapper.IntegralGradeMapper;
import com.epdemic.srm.core.mapper.UserInfoMapper;
import com.epdemic.srm.core.pojo.entity.*;
import com.epdemic.srm.core.mapper.BorrowInfoMapper;
import com.epdemic.srm.core.pojo.vo.BorrowInfoApprovalVO;
import com.epdemic.srm.core.pojo.vo.BorrowerDetailVO;
import com.epdemic.srm.core.service.BorrowInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.epdemic.srm.core.service.BorrowerService;
import com.epdemic.srm.core.service.DictService;
import com.epdemic.srm.core.service.LendService;
import io.lettuce.core.ScriptOutputType;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 借款信息表 服务实现类
 * </p>
 *
 * @author combine
 * @since 2021-05-31
 */
@Service
public class BorrowInfoServiceImpl extends ServiceImpl<BorrowInfoMapper, BorrowInfo> implements BorrowInfoService {


    @Resource
    private UserInfoMapper userInfoMapper;

    @Resource
    private IntegralGradeMapper integralGradeMapper;

    @Resource
    private DictService dictService;

    @Resource
    private BorrowerMapper borrowerMapper;

    @Resource
    private BorrowerService borrowerService;

    @Resource
    private LendService lendService;

    @Override
    public BigDecimal getBorrowAmount(Long userId) {

        //获取用户积分
        UserInfo userInfo = userInfoMapper.selectById(userId);
        Assert.notNull(userInfo, ResponseEnum.LOGIN_MOBILE_ERROR);
        Integer integral = userInfo.getIntegral();

        //根据积分查询额度
        QueryWrapper<IntegralGrade> integralGradeQueryWrapper = new QueryWrapper<>();
        integralGradeQueryWrapper.le("integral_start",integral)
                .ge("integral_end",integral);
        IntegralGrade integralGrade = integralGradeMapper.selectOne(integralGradeQueryWrapper);

        if (integralGrade == null){
            return new BigDecimal("0");
        }
        System.out.println(integralGrade.getBorrowAmount());
        return integralGrade.getBorrowAmount();
    }

    @Override
    public void saveBorrowInfo(BorrowInfo borrowInfo, Long userId) {

        //获取userinfo信息
        UserInfo userInfo = userInfoMapper.selectById(userId);

        //判断用户绑定状态
        Assert.isTrue(userInfo.getBindStatus().intValue() == UserBindEnum.BIND_OK.getStatus()
        .intValue(),ResponseEnum.USER_NO_BIND_ERROR);

        //判断借款人额度申请状态

        Assert.isTrue(userInfo.getBorrowAuthStatus().intValue()==
                BorrowerStatusEnum.AUTH_OK.getStatus().intValue(),ResponseEnum
        .USER_NO_AMOUNT_ERROR);

        //存储borrowInfo数据
        borrowInfo.setUserId(userId);
        //百分比转小数
        borrowInfo.setBorrowYearRate(borrowInfo.
                getBorrowYearRate().
                divide(new BigDecimal(100)));
        //设置借款申请的审核状态
        borrowInfo.setStatus(BorrowInfoStatusEnum.
                CHECK_RUN.getStatus());
        baseMapper.insert(borrowInfo);
        System.out.println(borrowInfo.toString());


    }

    @Override
    public Integer getStatusByUserId(Long userId) {

        QueryWrapper<BorrowInfo> borrowInfoQueryWrapper = new QueryWrapper<>();
        borrowInfoQueryWrapper.select("status")
                              .eq("user_id",userId);
        List<Object> objects = baseMapper.selectObjs(borrowInfoQueryWrapper);
        if (objects.size()==0){
            return BorrowInfoStatusEnum.NO_AUTH.getStatus();
        }

        return (Integer) objects.get(0);
    }

    @Override
    public List<BorrowInfo> selectList() {

        List<BorrowInfo> borrowInfoList = baseMapper.selectBorrowInfoList();
        for (BorrowInfo borrowInfo:borrowInfoList
             ) {
            String returnMethod =
                    dictService.getNameByParentDictCodeAndValue("returnMethod", borrowInfo.getReturnMethod());
            String moneyUse = dictService.
                    getNameByParentDictCodeAndValue("moneyUse",
                            borrowInfo.getMoneyUse());
            String status = BorrowInfoStatusEnum.
                    getMsgByStatus(borrowInfo.getStatus());
            borrowInfo.getParam().put("returnMethod", returnMethod);
            borrowInfo.getParam().put("moneyUse", moneyUse);
            borrowInfo.getParam().put("status", status);

        }
        System.out.println(borrowInfoList.toString());
        return borrowInfoList;
    }

    @Override
    public Map<String, Object> getBorrowInfoDetail(Long id) {

        //查询借款对象:BorrwoInfo
        BorrowInfo borrowInfo = baseMapper.selectById(id);
        String returnMethod =dictService.getNameByParentDictCodeAndValue(
                "returnMethod",borrowInfo.getReturnMethod()
        );
        String moneyUse = dictService.
                getNameByParentDictCodeAndValue(
                        "moneyUse",
                        borrowInfo.getMoneyUse());
        String status = BorrowInfoStatusEnum.
                getMsgByStatus(borrowInfo.getStatus());
        borrowInfo.getParam().
                put("returnMethod",
                        returnMethod);
        borrowInfo.getParam().
                put("moneyUse",
                        moneyUse);
        borrowInfo.getParam().
                put("status", status);

        // 查询借款人对象，Borrower(BorrowerDetailVo)
       QueryWrapper<Borrower> borrowerQueryWrapper = new QueryWrapper<>();
       borrowerQueryWrapper.eq("user_id",borrowInfo.getUserId());
        Borrower borrower = borrowerMapper.selectOne(borrowerQueryWrapper);
        BorrowerDetailVO borrowerDetailVO = borrowerService.getBorrowerDetailVOById(borrower.getId());

        //组装结果
        Map<String,Object> result = new HashMap<>();
        result.put("borrowInfo",borrowInfo);
        result.put("borrower",borrowerDetailVO);
        System.out.println(result.toString());
        return result;
    }

    @Override
    public void approval(BorrowInfoApprovalVO borrowInfoApprovalVO) {

        // 修改借款审核的状态，borrow_info
        Long borrowInfoId = borrowInfoApprovalVO.getId();
        BorrowInfo borrowInfo = baseMapper.selectById(borrowInfoId);
        borrowInfo.setStatus(borrowInfoApprovalVO.getStatus());
        baseMapper.updateById(borrowInfo);

        //若通过审核，则产生新的标的记录lend
        if (borrowInfoApprovalVO.getStatus().intValue() ==BorrowInfoStatusEnum
        .CHECK_OK.getStatus().intValue()){

            // 创建新的标的
            lendService.createLend(borrowInfoApprovalVO,borrowInfo);
            System.out.println("创建标的成功");
        }

    }
}
