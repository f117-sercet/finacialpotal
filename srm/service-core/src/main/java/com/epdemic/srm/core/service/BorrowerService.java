package com.epdemic.srm.core.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.epdemic.srm.core.pojo.entity.Borrower;
import com.baomidou.mybatisplus.extension.service.IService;
import com.epdemic.srm.core.pojo.vo.BorrowerApprovalVO;
import com.epdemic.srm.core.pojo.vo.BorrowerDetailVO;
import com.epdemic.srm.core.pojo.vo.BorrowerVO;

/**
 * <p>
 * 借款人 服务类
 * </p>
 *
 * @author combine
 * @since 2021-05-31
 */
public interface BorrowerService extends IService<Borrower> {
    /**
     * 保存借款人信息
     * @param borrowerVO
     * @param userId
     */
    void saveBorrowerVOByUserId(BorrowerVO borrowerVO, Long userId);

    /**
     * 通过用户id获取当前状态
     * @param userId
     * @return
     */
    Integer getStatusByUserId(Long userId);

    /**
     * 分页
     * @param pageParam
     * @param keyword
     * @return
     */

    IPage<Borrower> listPage(Page<Borrower> pageParam, String keyword);

    /**
     * 获取借款人信息
     * @param id
     * @return
     */
    BorrowerDetailVO getBorrowerDetailVOById(Long id);

    /**
     * 获取借款人信息
     * @param borrowerApprovalVO
     */
    void approval(BorrowerApprovalVO borrowerApprovalVO);

}
