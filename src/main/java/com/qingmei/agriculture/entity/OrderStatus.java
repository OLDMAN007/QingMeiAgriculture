package com.qingmei.agriculture.entity;

public enum OrderStatus {
    UNUSUAL("异常", 0), FINISH("完成",1), RETURNS("退货", 2);

    private String name;
    private int index;

    @Override
    public String toString() {
        return "SaleStatus{" +
                "name='" + name + '\'' +
                ", index=" + index +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    OrderStatus(String name, int index) {
        this.name = name;
        this.index = index;
    }

    OrderStatus() {
    }
}
