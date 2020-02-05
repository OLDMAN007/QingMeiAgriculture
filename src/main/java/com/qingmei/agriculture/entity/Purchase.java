package com.qingmei.agriculture.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * <p>FileName: PurchaseTable</p>
 * <p>Description: 進貨表</p>
 * <p>Email: zhangwb-s@qq.com</p>
 *
 * @author harper
 * @version 0.0.1
 * @date 2020/2/3
 */
@Entity
@Table(name = "purchase")
public class Purchase {
    @Id
    @Column(length = 36)
    private String id;
    @Column(nullable = false)
    private String code;
    @Column(nullable = false, length = 36)
    private String commodityId;
    @Column(nullable = false)
    private int quantity;
    @Column(nullable = false, length = 36)
    private String measurementId;
    @Column(nullable = false)
    private int price;
    @Column(nullable = false)
    private Date date;
    @Column(nullable = false)
    private OrderStatus status;

    @Override
    public String toString() {
        return "Purchase{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", commodityId='" + commodityId + '\'' +
                ", quantity=" + quantity +
                ", measurementId='" + measurementId + '\'' +
                ", price=" + price +
                ", date=" + date +
                ", status=" + status +
                '}';
    }

    public Purchase(String id, String code, String commodityId, int quantity, String measurementId, int price, Date date, OrderStatus status) {
        this.id = id;
        this.code = code;
        this.commodityId = commodityId;
        this.quantity = quantity;
        this.measurementId = measurementId;
        this.price = price;
        this.date = date;
        this.status = status;
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

    public String getMeasurementId() {
        return measurementId;
    }

    public void setMeasurementId(String measurementId) {
        this.measurementId = measurementId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
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

    public Purchase() {
    }
}
