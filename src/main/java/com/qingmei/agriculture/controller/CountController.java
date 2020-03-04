package com.qingmei.agriculture.controller;

import com.qingmei.agriculture.entity.Commodity;
import com.qingmei.agriculture.entity.Customer;
import com.qingmei.agriculture.repository.CommodityRepository;
import com.qingmei.agriculture.repository.CountInfo;
import com.qingmei.agriculture.repository.CountRepository;
import com.qingmei.agriculture.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
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
@RestController
//@Controlleroller
public class CountController {

    final
    CountRepository countRepository;
    final
    CommodityRepository commodityRepository;
    final
    CustomerRepository customerRepository;

    public CountController(CountRepository countRepository, CommodityRepository commodityRepository, CustomerRepository customerRepository) {
        this.countRepository = countRepository;
        this.commodityRepository = commodityRepository;
        this.customerRepository = customerRepository;
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
            startDate = startDate + " 00:00:00";
            endDate = endDate + " 23:59:59";
            List<CountInfo> countCustomer = countRepository.countCustomer(startDate, endDate, cusName);
            return countCustomer;
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
            startDate = startDate + " 00:00:00";
            endDate = endDate + " 23:59:59";
            List<CountInfo> countCommodity = countRepository.countCommodity(startDate, endDate, comName);
            return countCommodity;
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
            startDate = startDate + " 00:00:00";
            endDate = endDate + " 23:59:59";
            List<CountInfo> countDate = countRepository.countDate(startDate, endDate);
            return countDate;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

//    /**
//     *
//     * @return
//     */
//    @RequestMapping("countCommodity")
//    public String countCommodity(HttpSession session){
//        Iterable<Commodity> commodities = commodityRepository.findAll();
//        session.setAttribute("commodity", commodities);
//        return "countCommodity";
//    }

//    /**
//     *
//     * @return
//     */
//    @RequestMapping("countCustomer")
//    public String countCustomer(HttpSession session){
//        Iterable<Customer> customers = customerRepository.findAll();
//        session.setAttribute("customer", customers);
//        return "countCustomer";
//    }
//
//    /**
//     *
//     * @return
//     */
//    @RequestMapping("countDate")
//    public String countDate(){
//        return "countDate";
//    }

}
