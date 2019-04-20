package com.stock.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author lei
 * @date 2019/4/20
 **/
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Data
public class AddStocksRequestVO extends BatchStocksRequestVO {

    private Boolean hot;

   public static AddStocksRequestVO of(BatchStocksRequestVO batchStocksRequestVO) {
       AddStocksRequestVO addStocksRequestVO = new AddStocksRequestVO();
       addStocksRequestVO.setStockCodes(batchStocksRequestVO.getStockCodes());
       return addStocksRequestVO;
   }

}
