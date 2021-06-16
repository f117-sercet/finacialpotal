package com.epdemic.srm.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.epdemic.srm.core.mapper.LendItemMapper;
import com.epdemic.srm.core.mapper.LendMapper;
import com.epdemic.srm.core.mapper.LendReturnMapper;
import com.epdemic.srm.core.pojo.entity.Lend;
import com.epdemic.srm.core.pojo.entity.LendItem;
import com.epdemic.srm.core.pojo.entity.LendItemReturn;
import com.epdemic.srm.core.mapper.LendItemReturnMapper;
import com.epdemic.srm.core.pojo.entity.LendReturn;
import com.epdemic.srm.core.service.LendItemReturnService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.epdemic.srm.core.service.UserBindService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 标的出借回款记录表 服务实现类
 * </p>
 *
 * @author combine
 * @since 2021-05-31
 */
@Service
public class LendItemReturnServiceImpl extends ServiceImpl<LendItemReturnMapper, LendItemReturn> implements LendItemReturnService {


    @Resource
    private LendMapper lendMapper;

    @Resource
    private LendReturnMapper lendReturnMapper;

    @Resource
    private LendItemMapper lendItemMapper;

    @Resource
    private UserBindService userBindService;

    @Override
    public List<LendItemReturn> selectByLendId(Long lendId, Long userId) {
        QueryWrapper<LendItemReturn> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("lend_id",lendId)
                .eq("invest_user_id",userId)
                .orderByDesc("current_period");
        return baseMapper.selectList(queryWrapper);
    }

    /**
     * 通过还款计划的id,找到对应的回款计划数据，组装data参数中需要的List<Map>
     * @param lendReturnId
     * @return
     */
    @Override
    public List<Map<String, Object>> addReturnDetail(Long lendReturnId) {

          // 还款记录
        LendReturn lendReturn = lendReturnMapper.selectById(lendReturnId);
        //获取标的
        Lend lend = lendMapper.selectById(lendReturn.getLendId());

        List<LendItemReturn> lendItemReturnList = this.selectLendItemReturnList(lendReturnId);
        List<Map<String,Object>> lendItemReturnDetailList = new ArrayList<>();
        for (LendItemReturn lendItemReturn : lendItemReturnList){

            //获取投资记录
            Long LendItemId = lendItemReturn.getLendItemId();
            LendItem lendItem = lendItemMapper.selectById(LendItemId);

            //获取投资人id
            Long investUserId = lendItem.getInvestUserId();
            String bindCode = userBindService.getBindCodeByUserId(investUserId);

            Map<String,Object> map = new HashMap<>();
            /**下项目编号**/
            map.put("agentProjectCode",lend.getLendNo());
            /**投资编号**/
            map.put("voteBillNo",lendItem.getLendItemNo());
            map.put("toBindCode",bindCode);
            /**还款总额**/
            map.put("transitAmt",lendItemReturn.getTotal());
            /**利息**/
            map.put("benifitAmt",lendItemReturn.getInterest());
            map.put("feeAmt",new BigDecimal(0));

            lendItemReturnDetailList.add(map);
        }


        return lendItemReturnDetailList;
    }

    @Override
    public List<LendItemReturn> selectLendItemReturnList(Long lendReturnId) {
        QueryWrapper<LendItemReturn> lendItemReturnQueryWrapper = new QueryWrapper<>();
        lendItemReturnQueryWrapper
                .eq("lend_return_id",lendReturnId);
        return baseMapper.selectList(lendItemReturnQueryWrapper);
    }
}
