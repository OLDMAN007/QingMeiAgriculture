package com.qingmei.agriculture.entity;

public enum ComKind {
    FERTILIZER("化肥", 0), PESTICIDE("农药", 1);

    private String name;
    private int index;

    ComKind() {
    }

    ComKind(String name, int index) {
        this.name = name;
        this.index = index;
    }

    @Override
    public String toString() {
        return "CumKind{" +
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
}
