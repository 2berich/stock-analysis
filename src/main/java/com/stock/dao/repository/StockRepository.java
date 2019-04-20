package com.stock.dao.repository;

import com.stock.dao.po.StockPO;
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
public interface StockRepository extends JpaRepository<StockPO, String>, JpaSpecificationExecutor<StockPO> {

    @Query("SELECT stock.stockcode FROM StockPO stock WHERE stock.hot = true")
    List<String> findHotStockCodes();


    StockPO findByIdAndProfitLessThanEqual(String stockCode, Integer profit);

}
