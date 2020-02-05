package com.qingmei.agriculture.controller;

import com.qingmei.agriculture.repository.CountInfo;
import com.qingmei.agriculture.repository.CountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>FileName: CountController</p>
 * <p>Description: 統計數據</p>
 * <p>Email: zhangwb-s@qq.com</p>
 *
 * @author harper
 * @version 0.0.1
 * @date 2020/2/4
 */
//@RestController
@Controller
public class CountController {

    final
    CountRepository countRepository;

    public CountController(CountRepository countRepository) {
        this.countRepository = countRepository;
    }

    /**
     * 獲取某顧客指定日期內的購物信息
     * @param startDate
     * @param endDate
     * @param cusName
     * @return
     */
    @RequestMapping(value = "getCustomerSales")
    public List<CountInfo> getCustomerSales(String startDate, String endDate, String cusName){
        try {
            return countRepository.countCustomer(startDate, endDate, cusName);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 某商品指定日期內的銷售信息
     * @param startDate
     * @param endDate
     * @param comName
     * @return
     */
    @RequestMapping(value = "getCommoditySales")
    public List<CountInfo> getCommoditySales(String startDate, String endDate, String comName){
        try {
            return countRepository.countCommodity(startDate, endDate, comName);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 指定日期內的銷售信息
     * @param startDate
     * @param endDate
     * @return
     */
    @RequestMapping(value = "getDateSales")
    public List<CountInfo> getDateSales(String startDate, String endDate){
        try {
            return countRepository.countDate(startDate, endDate);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
