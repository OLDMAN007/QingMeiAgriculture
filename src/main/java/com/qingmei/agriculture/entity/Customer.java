package com.qingmei.agriculture.entity;

import javax.persistence.*;

/**
 * <p>FileName: CustomerEntity</p>
 * <p>Description: CustomerEntity</p>
 * <p>Email: zhangwb-s@qq.com</p>
 *
 * @author harper
 * @version 0.0.1
 * @date 2020/2/3
 */
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @Column(length = 36)
    private String id;
    private String cusCode;
    @Column(unique = true, nullable = false)
    private String cusName;
    @Column(unique = true, nullable = false, length = 11)
    private String telephone;
    private String address;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCusCode() {
        return cusCode;
    }

    public void setCusCode(String cusCode) {
        this.cusCode = cusCode;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", cusCode='" + cusCode + '\'' +
                ", cusName='" + cusName + '\'' +
                ", telephone='" + telephone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public Customer() {
    }

    public Customer(String id, String cusCode, String cusName, String telephone, String address){
        this.id = id;
        this.cusCode = cusCode;
        this.cusName = cusName;
        this.telephone = telephone;
        this.address = address;
    }
}
