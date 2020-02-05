package com.qingmei.agriculture.repository;

import java.util.Date;

public interface CountInfo {
    String getCusName();
    String getComName();
    int getQuantity();
    int getPrice();
    String getMeasurement();
    Date getDate();
}
