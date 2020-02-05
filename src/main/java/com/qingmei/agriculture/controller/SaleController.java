package com.qingmei.agriculture.controller;

import cn.hutool.core.util.StrUtil;
import com.qingmei.agriculture.entity.Measurement;
import com.qingmei.agriculture.entity.OrderStatus;
import com.qingmei.agriculture.entity.Purchase;
import com.qingmei.agriculture.entity.Sale;
import com.qingmei.agriculture.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

/**
 * <p>FileName: SaleController</p>
 * <p>Description: SaleController</p>
 * <p>Email: zhangwb-s@qq.com</p>
 *
 * @author harper
 * @version 0.0.1
 * @date 2020/2/3
 */
@Controller
public class SaleController {
    final
    SaleRepository saleRepository;

    public SaleController(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    /**
     * 新增銷售訂單
     * @param code
     * @param commodityId
     * @param quantity
     * @param price
     * @param customerId
     * @param measurementId
     * @param status
     * @return
     */
    @RequestMapping(value = "insertSale")
    public boolean insertSale(String code, String commodityId, int quantity, int price, String customerId, String measurementId, int status){
        try {
            if (!StrUtil.isBlank(commodityId) && quantity > 0 && price > 0 && !StrUtil.isBlank(customerId) && !StrUtil.isBlank(measurementId)){
                if (StrUtil.isBlank(code)){
                    code = new Date().toString();
                }

                Sale sale = new Sale();
                sale.setId(UUID.randomUUID().toString());
                sale.setCode(code);
                sale.setCommodityId(commodityId);
                sale.setQuantity(quantity);
                sale.setPrice(price);
                sale.setMeasurementId(measurementId);
                sale.setCustomerId(customerId);
                sale.setDate(new Date());
                if (status == 0){
                    sale.setStatus(OrderStatus.UNUSUAL);
                } else if (status == 1){
                    sale.setStatus(OrderStatus.FINISH);
                } else if (status == 2){
                    sale.setStatus(OrderStatus.RETURNS);
                }

                saleRepository.save(sale);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 更改銷售訂單狀態
     * @param id
     * @param status
     * @return
     */
    @RequestMapping(value = "changeSaleStatus")
    public boolean changeSaleStatus(String id, int status){
        try {
            Sale sale = saleRepository.findById(id).get();

            if (status == 0){
                sale.setStatus(OrderStatus.UNUSUAL);
            } else if (status == 1){
                sale.setStatus(OrderStatus.FINISH);
            } else if (status == 2){
                sale.setStatus(OrderStatus.RETURNS);
            }

            saleRepository.save(sale);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 查詢所有銷售訂單
     * @return
     */
    @RequestMapping(value = "findAllSale")
    public Iterable<Sale> findAllSale(){
        return saleRepository.findAll();
    }
}
