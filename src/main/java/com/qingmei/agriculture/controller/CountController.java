package com.qingmei.agriculture.controller;

import com.qingmei.agriculture.repository.CountInfo;
import com.qingmei.agriculture.repository.CountRepository;
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
    public String getCustomerSales(String startDate, String endDate, String cusName, HttpSession session){
        try {
            startDate = startDate + " 00:00:00";
            endDate = endDate + " 23:59:59";
            List<CountInfo> countCustomer = countRepository.countCustomer(startDate, endDate, cusName);
            session.setAttribute("countCustomer", countCustomer);
            return "countCustomer";
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
    public String getCommoditySales(String startDate, String endDate, String comName, HttpSession session){
        try {
            startDate = startDate + " 00:00:00";
            endDate = endDate + " 23:59:59";
            List<CountInfo> countCommodity = countRepository.countCommodity(startDate, endDate, comName);
            session.setAttribute("countCommodity", countCommodity);
            return "countCommodity";
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
    public String getDateSales(String startDate, String endDate, HttpSession session){
        try {
            startDate = startDate + " 00:00:00";
            endDate = endDate + " 23:59:59";
            List<CountInfo> countDate = countRepository.countDate(startDate, endDate);
            session.setAttribute("countDate",countDate);
            return "countDate";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     *
     * @return
     */
    @RequestMapping("countCommodity")
    public String countCommodity(){
        return "countCommodity";
    }

    /**
     *
     * @return
     */
    @RequestMapping("countCustomer")
    public String countCustomer(){
        return "countCustomer";
    }

    /**
     *
     * @return
     */
    @RequestMapping("countDate")
    public String countDate(){
        return "countDate";
    }
}
