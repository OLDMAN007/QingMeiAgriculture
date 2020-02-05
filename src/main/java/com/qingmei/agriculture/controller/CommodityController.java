package com.qingmei.agriculture.controller;


import cn.hutool.core.util.StrUtil;
import com.qingmei.agriculture.entity.ComKind;
import com.qingmei.agriculture.entity.ComStatus;
import com.qingmei.agriculture.entity.Commodity;
import com.qingmei.agriculture.entity.Measurement;
import com.qingmei.agriculture.repository.CommodityRepository;
import com.qingmei.agriculture.repository.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

/**
 * <p>FileName: CommodityController</p>
 * <p>Description: CommodityController</p>
 * <p>Email: zhangwb-s@qq.com</p>
 *
 * @author harper
 * @version 0.0.1
 * @date 2020/2/3
 */
//@RestController
@Controller
public class CommodityController {
    final
    CommodityRepository commodityRepository;
    final
    MeasurementRepository measurementRepository;

    public CommodityController(CommodityRepository commodityRepository, MeasurementRepository measurementRepository) {
        this.commodityRepository = commodityRepository;
        this.measurementRepository = measurementRepository;
    }

    /**
     * 新增商品
     * @param comCode
     * @param comName
     * @param kind
     * @param quantity
     * @param measurement
     * @param price
     * @param note
     * @param status
     * @return
     */
    @RequestMapping(value = "insertCommodity")
    public String insertCommodity(String comCode, String comName, int kind, int quantity, String measurement, int price, String note, int status, HttpSession session){
        try {
            if (!StrUtil.isBlank(comName) && quantity >= 0 && !StrUtil.isBlank(measurement) && price > 0){
                if (StrUtil.isBlank(comCode)) comCode = comName;

                Commodity commodity = new Commodity();
                commodity.setId(UUID.randomUUID().toString());
                commodity.setComCode(comCode);
                commodity.setComName(comName);
                commodity.setQuantity(quantity);
                commodity.setMeasurement(measurement);
                commodity.setPrice(price);
                commodity.setNote(note);
                if (kind == 0){
                    //商品類型為"化肥"
                    commodity.setKind(ComKind.FERTILIZER);
                } else if (kind == 1) {
                    //商品類型為"農藥"
                    commodity.setKind(ComKind.PESTICIDE);
                }
                if (status == 0){
                    //商品狀態為"正常"
                    commodity.setStatus(ComStatus.SOLDOUT);
                } else if (status == 1){
                    //商品狀態為"售罄"
                    commodity.setStatus(ComStatus.NORMAL);
                }

                commodityRepository.save(commodity);

                session.setAttribute("successCom", "保存成功！");
            } else {
                session.setAttribute("errorCom", "保存失敗！");
            }
            return "commodityCard";
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("errorCom", "保存失敗！");
            return "commodityCard";
        }
    }

    /**
     * 刪除商品
     * @param comName
     * @return
     */
    @RequestMapping(value = "deleteCommodity")
    public boolean deleteCommodity(String comName){
        try {
            List<Commodity> commodityList = commodityRepository.findByComName(comName);

            for (Commodity commodity : commodityList) {
                commodityRepository.delete(commodity);
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 查詢所有商品
     * @return
     */
    @RequestMapping(value = "findAllCommodity")
    public String findAllCommodity(HttpSession session){
        Iterable<Commodity> commodities = commodityRepository.findAll();
        Iterable<Measurement> measurements = measurementRepository.findAll();

        for (Commodity commodity: commodities) {
            for (Measurement measurement : measurements){
                if (commodity.getMeasurement().equals(measurement.getId().toString())){
                    commodity.setMeasurement(measurement.getName());
                }
            }
        }
        session.setAttribute("commodity", commodities);
        return "commodityList";
    }

    /**
     * 修改商品信息
     * @param id
     * @param comCode
     * @param comName
     * @param kind
     * @param quantity
     * @param measurement
     * @param price
     * @param note
     * @param status
     * @return
     */
    @RequestMapping(value = "changeCommodityInfo")
    public boolean changeCommodityInfo(String id,String comCode, String comName, int kind, int quantity, String measurement, int price, String note, int status){
        try {
            Commodity commodity = commodityRepository.findById(id).get();

            commodity.setComCode(comCode);
            commodity.setComName(comName);
            commodity.setQuantity(quantity);
            commodity.setMeasurement(measurement);
            commodity.setPrice(price);
            commodity.setNote(note);
            if (kind == 0){
                //商品類型為"化肥"
                commodity.setKind(ComKind.FERTILIZER);
            } else if (kind == 1) {
                //商品類型為"農藥"
                commodity.setKind(ComKind.PESTICIDE);
            }
            if (status == 0){
                //商品狀態為"正常"
                commodity.setStatus(ComStatus.SOLDOUT);
            } else if (status == 1){
                //商品狀態為"售罄"
                commodity.setStatus(ComStatus.NORMAL);
            }

            commodityRepository.save(commodity);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     *
     * @return
     */
    @RequestMapping("commodityCard")
    public String commodityCard(HttpSession session){
        Iterable<Measurement> measurements = measurementRepository.findAll();
        session.setAttribute("measurement", measurements);
        return "commodityCard";
    }
}
