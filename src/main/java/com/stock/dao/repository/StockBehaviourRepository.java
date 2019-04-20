package com.stock.dao.repository;

import com.stock.dao.po.StockBehaviourPO;
import com.stock.dao.po.StockPO;
import com.stock.enums.TradeNatureEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lei
 * @date 2019/3/18
 **/
@Repository
public interface StockBehaviourRepository extends JpaRepository<StockBehaviourPO, Long>, JpaSpecificationExecutor<StockBehaviourPO> {

    @Query("FROM StockBehaviourPO behaviour WHERE behaviour.stockcode = ?1 AND behaviour.tradeNature = ?2 AND behaviour.volume >= ?3")
    List<StockBehaviourPO> suggestBehevioursByStockCode(String stockCode, TradeNatureEnum tradeNature, int hands);

}
