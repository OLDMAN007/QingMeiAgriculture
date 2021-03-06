package com.qingmei.agriculture.entity;

public enum ComStatus {
    SOLDOUT("售罄", 0), NORMAL("正常", 1);

    private String name;
    private int index;

    ComStatus() {
    }

    ComStatus(String name, int index) {
        this.name = name;
        this.index = index;
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
}
