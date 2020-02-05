package com.qingmei.agriculture.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p>FileName: CommodityEntity</p>
 * <p>Description: 商品實體類</p>
 * <p>Email: zhangwb-s@qq.com</p>
 *
 * @author harper
 * @version 0.0.1
 * @date 2020/2/3
 */
@Entity
@Table(name = "commodity")
public class Commodity {

    @Id
    @Column(length = 36)
    private String id;
    private String comCode;
    @Column(unique = true, nullable = false)
    private String comName;
    @Column(nullable = false)
    private ComKind kind;//種類
    @Column(nullable = false)
    private int quantity;//數量
    @Column(nullable = false)
    private String measurement;//計量單位
    @Column(nullable = false)
    private int price;//單價
    private String note;
    @Column(nullable = false)
    private ComStatus status;

    @Override
    public String toString() {
        return "Commodity{" +
                "id='" + id + '\'' +
                ", comCode='" + comCode + '\'' +
                ", comName='" + comName + '\'' +
                ", kind=" + kind +
                ", quantity=" + quantity +
                ", measurement='" + measurement + '\'' +
                ", price=" + price +
                ", note='" + note + '\'' +
                ", state=" + status +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getComCode() {
        return comCode;
    }

    public void setComCode(String comCode) {
        this.comCode = comCode;
    }

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
    }

    public ComKind getKind() {
        return kind;
    }

    public void setKind(ComKind kind) {
        this.kind = kind;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getMeasurement() {
        return measurement;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public ComStatus getStatus() {
        return status;
    }

    public void setStatus(ComStatus status) {
        this.status = status;
    }

    public Commodity(String id, String comCode, String comName, ComKind kind, int quantity, String measurement, int price, String note, ComStatus status) {
        this.id = id;
        this.comCode = comCode;
        this.comName = comName;
        this.kind = kind;
        this.quantity = quantity;
        this.measurement = measurement;
        this.price = price;
        this.note = note;
        this.status = status;
    }

    public Commodity() {
    }
}
