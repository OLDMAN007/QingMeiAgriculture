package com.qingmei.agriculture.controller;

import cn.hutool.core.util.StrUtil;
import com.qingmei.agriculture.entity.Customer;
import com.qingmei.agriculture.repository.CustomerRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

/**
 * <p>FileName: CostomerController</p>
 * <p>Description: CostomerController</p>
 * <p>Email: zhangwb-s@qq.com</p>
 *
 * @author harper
 * @version 0.0.1
 * @date 2020/2/3
 */
@RestController
//@Controller
public class CustomerController {
    final
    CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    /**
     * 新增顧客
     * @param cusCode
     * @param cusName
     * @param telephone
     * @param address
     * @return
     */
    @GetMapping("insertCustomer")
    public boolean insertCustomer(String cusCode, String cusName, String telephone, String address){
        try {
            if (!StrUtil.isBlank(cusName) && !StrUtil.isBlank(telephone)){
                if(StrUtil.isBlank(cusCode)) cusCode = cusName;

                customerRepository.save(new Customer(UUID.randomUUID().toString(),cusCode,cusName,telephone,address));

                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 查詢所有顧客
     * @return
     */
    @GetMapping("findAllCustomer")
    public Iterable<Customer> findAllCustomer(){
        try {
            Iterable<Customer> customerIterable = customerRepository.findAll();

            return customerIterable;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 刪除顧客
     * @param name
     * @return
     */
    @RequestMapping(value = "deleteCustomer")
    public boolean deleteCustomer(String name){
        try {
            List<Customer> customerList = customerRepository.findByCusName(name);

            for (Customer customer : customerList) {
                customerRepository.delete(customer);
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 修改顧客信息
     * @param id
     * @param cusCode
     * @param cusName
     * @param telephone
     * @param address
     * @return
     */
    @RequestMapping(value = "changeCustomerInfo")
    public boolean changeCustomerInfo(String id, String cusCode, String cusName, String telephone, String address){
        try {
            if (!StrUtil.isBlank(cusName) && !StrUtil.isBlank(telephone)){
                if(StrUtil.isBlank(cusCode)) cusCode = cusName;

                Customer customer = customerRepository.findById(id).get();
                customer.setCusCode(cusCode);
                customer.setCusName(cusName);
                customer.setTelephone(telephone);
                customer.setAddress(address);

                customerRepository.save(customer);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

//    /**
//     *
//     * @return
//     */
//    @RequestMapping("customerCard")
//    public String customerCard(){
//        return "customerCard";
//    }
}
