package com.stock.dao.repository;

import com.stock.dao.po.StockBehaviourPO;
import com.stock.dao.po.StockPriceChangePO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author lei
 * @date 2019/3/18
 **/
@Repository
public interface StockPriceChangeRepository extends JpaRepository<StockPriceChangePO, Long>, JpaSpecificationExecutor<StockPriceChangePO> {

}
