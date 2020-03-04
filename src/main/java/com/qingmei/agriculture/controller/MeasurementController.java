package com.qingmei.agriculture.controller;

import cn.hutool.core.util.StrUtil;
import com.qingmei.agriculture.entity.Measurement;
import com.qingmei.agriculture.repository.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>FileName: MeasurementController</p>
 * <p>Description: MeasurementController</p>
 * <p>Email: zhangwb-s@qq.com</p>
 *
 * @author harper
 * @version 0.0.1
 * @date 2020/2/3
 */
@RestController
//@Controller
public class MeasurementController {

    final
    MeasurementRepository measurementRepository;

    public MeasurementController(MeasurementRepository measurementRepository) {
        this.measurementRepository = measurementRepository;
    }

    /**
     * 新增計量單位
     * @param code
     * @param name
     * @return
     */
    @RequestMapping(value = "insertMeasurement")
    public boolean insertMeasurement(String code, String name){
        try {
            if (!StrUtil.isBlank(name)){
                if (StrUtil.isBlank(code)) code = name;
                measurementRepository.save(new Measurement(code, name));
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 查詢所有計量單位
     * @return
     */
    @RequestMapping(value = "findAllMeasurement")
    public Iterable<Measurement> findAllMeasurement(){
        try {
            Iterable<Measurement> measurements = measurementRepository.findAll();
            return measurements;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 刪除計量單位
     * @param name
     * @return
     */
    @RequestMapping(value = "deleteMeasurement")
    public boolean deleteMeasurement(String name){
        try {
            List<Measurement> measurementList = measurementRepository.findByName(name);

            if (measurementList.size() <= 0){
                return false;
            }

            for (Measurement measurement : measurementList) {
                measurementRepository.delete(measurement);
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 修改計量單位信息
     * @param id
     * @param code
     * @param name
     * @return
     */
    @RequestMapping(value = "changeMeasurement")
    public boolean changeMeasurement(Integer id, String code, String name){
        try {
            if (!StrUtil.isBlank(name)) {
                if (!StrUtil.isBlank(code)) code = name;

                Measurement measurement = measurementRepository.findById(id).get();
                measurement.setCode(code);
                measurement.setName(name);

                measurementRepository.save(measurement);
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
//    @GetMapping("measurementCard")
//    public String measurementCard(){
//        return "measurementCard";
//    }
}
