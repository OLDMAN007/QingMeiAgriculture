package com.qingmei.agriculture.controller;

import cn.hutool.core.util.StrUtil;
import com.qingmei.agriculture.entity.OrderStatus;
import com.qingmei.agriculture.entity.Purchase;
import com.qingmei.agriculture.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

/**
 * <p>FileName: PurchaseController</p>
 * <p>Description: PurchaseController</p>
 * <p>Email: zhangwb-s@qq.com</p>
 *
 * @author harper
 * @version 0.0.1
 * @date 2020/2/3
 */
@Controller
public class PurchaseController {
    final
    PurchaseRepository purchaseRepository;

    public PurchaseController(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    /**
     * 新增進貨訂單
     * @param id
     * @param code
     * @param commodityId
     * @param quantity
     * @param measurementId
     * @param price
     * @param status
     * @return
     */
    @RequestMapping(value = "insertPurchase")
    public boolean insertPurchase(String id, String code, String commodityId, int quantity, String measurementId, int price, int status){
        try {
            if (!StrUtil.isBlank(commodityId) && quantity > 0 && !StrUtil.isBlank(measurementId) && price > 0){
                if (StrUtil.isBlank(code)){
                    code = new Date().toString();
                }

                Purchase purchase = new Purchase();
                purchase.setId(UUID.randomUUID().toString());
                purchase.setCode(code);
                purchase.setCommodityId(commodityId);
                purchase.setPrice(price);
                purchase.setQuantity(quantity);
                purchase.setMeasurementId(measurementId);
                purchase.setDate(new Date());
                if (status == 0){
                    purchase.setStatus(OrderStatus.UNUSUAL);
                } else if (status == 1){
                    purchase.setStatus(OrderStatus.FINISH);
                } else if (status == 2){
                    purchase.setStatus(OrderStatus.RETURNS);
                }

                purchaseRepository.save(purchase);
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
     * 更改進貨訂單狀態
     * @param id
     * @param status
     * @return
     */
    @RequestMapping(value = "changePurchaseStatus")
    public boolean changePurchaseStatus(String id, int status){
        try {
            Purchase purchase = purchaseRepository.findById(id).get();

            if (status == 0){
                purchase.setStatus(OrderStatus.UNUSUAL);
            } else if (status == 1){
                purchase.setStatus(OrderStatus.FINISH);
            } else if (status == 2){
                purchase.setStatus(OrderStatus.RETURNS);
            }

            purchaseRepository.save(purchase);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 查詢所有進貨訂單
     * @return
     */
    @RequestMapping(value = "findAllPurchase")
    public Iterable<Purchase> findAllPurchase(){
        return purchaseRepository.findAll();
    }
}
