package com.epdemic.srm.core.pojo.bo;

import com.epdemic.srm.core.enums.TransTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
/**
 * @author:estic
 * @date
 * 投标类型
 */
public class TransFlowBO {

    private String agentBillNo;
    private String bindCode;
    private BigDecimal amount;
    private TransTypeEnum transTypeEnum;
    private String memo;
}
