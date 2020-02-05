package com.qingmei.agriculture.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * <p>FileName: SaleTable</p>
 * <p>Description: 售貨表</p>
 * <p>Email: zhangwb-s@qq.com</p>
 *
 * @author harper
 * @version 0.0.1
 * @date 2020/2/3
 */
@Entity
@Table(name = "sale")
public class Sale {
    @Id
    @Column(length = 36)
    private String id;
    private String code;
    @Column(nullable = false, length = 36)
    private String commodityId;
    @Column(nullable = false)
    private int quantity;
    @Column(nullable = false)
    private int price;
    @Column(nullable = false, length = 36)
    private String customerId;
    @Column(nullable = false, length = 36)
    private String measurementId;
    @Column(nullable = false)
    private Date date;
    @Column(nullable = false)
    private OrderStatus status;

    public Sale() {
    }

    public String getMeasurementId() {
        return measurementId;
    }

    public void setMeasurementId(String measurementId) {
        this.measurementId = measurementId;
    }

    public Sale(String id, String code, String commodityId, int quantity, int price, String customerId, String measurementId, Date date, OrderStatus status) {
        this.id = id;
        this.code = code;
        this.commodityId = commodityId;
        this.quantity = quantity;
        this.price = price;
        this.customerId = customerId;
        this.measurementId = measurementId;
        this.date = date;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", commodityId='" + commodityId + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", customerId='" + customerId + '\'' +
                ", measurementId='" + measurementId + '\'' +
                ", date=" + date +
                ", status=" + status +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
