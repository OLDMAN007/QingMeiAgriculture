package com.qingmei.agriculture.controller;

import cn.hutool.core.util.StrUtil;
import com.qingmei.agriculture.entity.*;
import com.qingmei.agriculture.repository.CommodityRepository;
import com.qingmei.agriculture.repository.CustomerRepository;
import com.qingmei.agriculture.repository.MeasurementRepository;
import com.qingmei.agriculture.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
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
    final
    CommodityRepository commodityRepository;
    final
    CustomerRepository customerRepository;
    final
    MeasurementRepository measurementRepository;

    public SaleController(SaleRepository saleRepository, CommodityRepository commodityRepository, CustomerRepository customerRepository, MeasurementRepository measurementRepository) {
        this.saleRepository = saleRepository;
        this.commodityRepository = commodityRepository;
        this.customerRepository = customerRepository;
        this.measurementRepository = measurementRepository;
    }

    /**
     * 新增銷售訂單
     * @param code
     * @param commodityId
     * @param quantity
     * @param price
     * @param customerId
     * @param measurementId
     * @return
     */
    @RequestMapping(value = "insertSale")
    public String insertSale(String code, String commodityId, int quantity, int price, String customerId, String measurementId, HttpSession session){
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
//                if (status == 0){
//                    sale.setStatus(OrderStatus.UNUSUAL);
//                } else if (status == 1){
                    sale.setStatus(OrderStatus.FINISH);
//                } else if (status == 2){
//                    sale.setStatus(OrderStatus.RETURNS);
//                }

                saleRepository.save(sale);
                session.setAttribute("successInsertSale", "保存成功！");
                return "saleCard";
            } else {
                session.setAttribute("errorInsertSale", "保存失敗！");
                return "saleCard";
            }
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("errorInsertSale", "保存失敗！");
            return "saleCard";
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
    public String findAllSale(HttpSession session){
        Iterable<Sale> sales = saleRepository.findAll();
        Iterable<Commodity> commodities = commodityRepository.findAll();
        Iterable<Customer> customers = customerRepository.findAll();
        Iterable<Measurement> measurements = measurementRepository.findAll();

        for (Sale sale : sales){
            for (Commodity commodity : commodities){
                if (sale.getCommodityId().equals(commodity.getId())){
                    sale.setCommodityId(commodity.getComName());
                }
            }
            for (Customer customer : customers){
                if (sale.getCustomerId().equals(customer.getId())){
                    sale.setCustomerId(customer.getCusName());
                }
            }
            for (Measurement measurement : measurements){
                if (sale.getMeasurementId().equals(measurement.getId().toString())){
                    sale.setMeasurementId(measurement.getName());
                }
            }
        }

        session.setAttribute("sale", sales);
        return "saleList";
    }

    /**
     *
     * @return
     */
    @RequestMapping("saleCard")
    public String saleCard(HttpSession session){
        Iterable<Commodity> commodities = commodityRepository.findAll();
        Iterable<Measurement> measurements = measurementRepository.findAll();
        Iterable<Customer> customers = customerRepository.findAll();
        session.setAttribute("measurement", measurements);
        session.setAttribute("commodity", commodities);
        session.setAttribute("customer", customers);
        return "saleCard";
    }
}
