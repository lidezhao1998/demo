package com.ruoyi.zaihai.analysis.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.zaihai.caiji.domain.CTaskChfz;
import com.ruoyi.zaihai.caiji.domain.CTaskSczj;
import com.ruoyi.zaihai.caiji.service.ICTaskChfzService;
import com.ruoyi.zaihai.caiji.service.ICTaskSczjService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

/**
 * @author
 * @version 1.0
 * @date 2020/6/15 17:57
 * 生产力分析
 */
@Controller
@RequestMapping("/analysis/trendForecast")
public class TrendForecastController extends BaseController {

    @Autowired
    private ICTaskChfzService cTaskChfzService;

    @Autowired
    private ICTaskSczjService cTaskSczjService;

    private String prefix = "analysis/trendForecast";


    @GetMapping
    public String ProductionAnalyze(CTaskSczj cTaskSczj,CTaskChfz cTaskChfz, String xs,String type, ModelMap mmap){
        double zs;
        String ty;
        if(null==xs){
            zs=0.9;
        }else{
            try {
                zs=Double.parseDouble(xs);
            }catch (Exception e){
                zs=0.9;
                mmap.put("message","请输入0到1之间的数");
            }
        }
        if(null==type){
            List<CTaskChfz> list = cTaskChfzService.selectCTaskChfzAnalyze(cTaskChfz);

            List<String> years=new ArrayList<String>();
            List<Double> values=new ArrayList<Double>();
            list.forEach(pas->{
                years.add(pas.getYear());
                values.add(pas.getHarmTotalarea());
            });
            years.add(Integer.parseInt(years.get(years.size()-1))+1+"");
            mmap.put("years", years);
            mmap.put("values", values);
            List<Double> anaData=new ArrayList<Double>();
            List<Double> baseData = new ArrayList<Double>();
            anaData.add(values.get(0));
            for (int i = 0; i < values.size(); i++) {
                baseData.add(values.get(i));
                double result=new BigDecimal(getExpect(baseData,1,zs)).setScale(2, RoundingMode.UP).doubleValue();
                anaData.add(result);

            }
            mmap.put("xs",zs);
            mmap.put("anaData",anaData);
        }
        else if(type.equals("鼠害")){
            List<CTaskSczj> list = cTaskSczjService.selectCTaskSczjAnalyze(cTaskSczj);

            List<String> years=new ArrayList<String>();
            List<Double> values=new ArrayList<Double>();
            list.forEach(pas->{
                years.add(pas.getYear());
                values.add(pas.getHarmTotalarea());
            });
            years.add(Integer.parseInt(years.get(years.size()-1))+1+"");
            mmap.put("years", years);
            mmap.put("values", values);
            List<Double> anaData=new ArrayList<Double>();
            List<Double> baseData = new ArrayList<Double>();
            anaData.add(values.get(0));
            for (int i = 0; i < values.size(); i++) {
                baseData.add(values.get(i));
                double result=new BigDecimal(getExpect(baseData,1,zs)).setScale(2, RoundingMode.UP).doubleValue();
                anaData.add(result);

            }
            mmap.put("xs",zs);
            mmap.put("anaData",anaData);
        }else{
            List<CTaskChfz> list = cTaskChfzService.selectCTaskChfzAnalyze(cTaskChfz);

            List<String> years=new ArrayList<String>();
            List<Double> values=new ArrayList<Double>();
            list.forEach(pas->{
                years.add(pas.getYear());
                values.add(pas.getHarmTotalarea());
            });
            years.add(Integer.parseInt(years.get(years.size()-1))+1+"");
            mmap.put("years", years);
            mmap.put("values", values);
            List<Double> anaData=new ArrayList<Double>();
            List<Double> baseData = new ArrayList<Double>();
            anaData.add(values.get(0));
            for (int i = 0; i < values.size(); i++) {
                baseData.add(values.get(i));
                double result=new BigDecimal(getExpect(baseData,1,zs)).setScale(2, RoundingMode.UP).doubleValue();
                anaData.add(result);
            }
            mmap.put("xs",zs);
            mmap.put("anaData",anaData);
        }
        return prefix+"/analyze";
    }

    @GetMapping("/list22")
    @ResponseBody
    public AjaxResult ProductionAnalyzes(CTaskSczj cTaskSczj, CTaskChfz cTaskChfz, String xs, String type){
        double zs;
        String ty;
        AjaxResult ajax = AjaxResult.success();
        if(null==xs){
            zs=0.9;
        }else{
            try {
                zs=Double.parseDouble(xs);
            }catch (Exception e){
                zs=0.9;
                ajax.put("message","请输入0到1之间的数");
            }
        }
        if(null==type){
            List<CTaskChfz> list = cTaskChfzService.selectCTaskChfzAnalyze(cTaskChfz);

            List<String> years=new ArrayList<String>();
            List<Double> values=new ArrayList<Double>();
            list.forEach(pas->{
                years.add(pas.getYear());
                values.add(pas.getHarmTotalarea());
            });
            years.add(Integer.parseInt(years.get(years.size()-1))+1+"");
            ajax.put("years", years);
            ajax.put("values", values);
            List<Double> anaData=new ArrayList<Double>();
            List<Double> baseData = new ArrayList<Double>();
            anaData.add(values.get(0));
            for (int i = 0; i < values.size(); i++) {
                baseData.add(values.get(i));
                double result=new BigDecimal(getExpect(baseData,1,zs)).setScale(2, RoundingMode.UP).doubleValue();
                anaData.add(result);

            }
            ajax.put("xs",zs);
            ajax.put("anaData",anaData);
        }
        else if(type.equals("鼠害")){
            List<CTaskSczj> list = cTaskSczjService.selectCTaskSczjAnalyze(cTaskSczj);

            List<String> years=new ArrayList<String>();
            List<Double> values=new ArrayList<Double>();
            list.forEach(pas->{
                years.add(pas.getYear());
                values.add(pas.getHarmTotalarea());
            });
            years.add(Integer.parseInt(years.get(years.size()-1))+1+"");
            ajax.put("years", years);
            ajax.put("values", values);
            List<Double> anaData=new ArrayList<Double>();
            List<Double> baseData = new ArrayList<Double>();
            anaData.add(values.get(0));
            for (int i = 0; i < values.size(); i++) {
                baseData.add(values.get(i));
                double result=new BigDecimal(getExpect(baseData,1,zs)).setScale(2, RoundingMode.UP).doubleValue();
                anaData.add(result);

            }
            ajax.put("xs",zs);
            ajax.put("anaData",anaData);
        }else{
            List<CTaskChfz> list = cTaskChfzService.selectCTaskChfzAnalyze(cTaskChfz);

            List<String> years=new ArrayList<String>();
            List<Double> values=new ArrayList<Double>();
            list.forEach(pas->{
                years.add(pas.getYear());
                values.add(pas.getHarmTotalarea());
            });
            years.add(Integer.parseInt(years.get(years.size()-1))+1+"");
            ajax.put("years", years);
            ajax.put("values", values);
            List<Double> anaData=new ArrayList<Double>();
            List<Double> baseData = new ArrayList<Double>();
            anaData.add(values.get(0));
            for (int i = 0; i < values.size(); i++) {
                baseData.add(values.get(i));
                double result=new BigDecimal(getExpect(baseData,1,zs)).setScale(2, RoundingMode.UP).doubleValue();
                anaData.add(result);
            }
            ajax.put("xs",zs);
            ajax.put("anaData",anaData);
        }

        return ajax;
    }

    public static void main(String[] args) throws ParseException {
        List<Double> data= new ArrayList<Double>();
        data.add(110784.0);
        data.add(109321.3);
        data.add(108846.6);
//        data.add(107954.4);
//        data.add(119243.7);
//        data.add(122148.5);
//        data.add(128585.1);
//        data.add(129115.1);
//        Double init=getExpect(data,1,0.2);
        Double result=null;
        for (int i=0;i<9;i++){

            result= new BigDecimal(getExpect(data,1,0.2)).setScale(2, RoundingMode.UP).doubleValue();
            data.add(result);
            System.out.println(result);
        }

        Period period = Period.between(LocalDate.parse("2003-01-01"),LocalDate.parse("2020-01-01"));
        System.out.println(period.getYears());

    }

    /**
     * 二次指数平滑法求预测值
     * @param list 基础数据集合
     * @param year 未来第几期
     * @param modulus 平滑系数
     * @return 预测值
     */
    private static Double getExpect(List<Double> list, int year, Double modulus ) {
//        if (list.size() < 3 || modulus <= 0 || modulus >= 1) {
//            return null;
//        }
        Double modulusLeft = 1 - modulus;
        Double lastIndex = list.get(0);
        Double lastSecIndex = list.get(0);
        for (Double data :list) {
            lastIndex = modulus * data + modulusLeft * lastIndex;
            lastSecIndex = modulus * lastIndex + modulusLeft * lastSecIndex;
        }
        Double a = 2 * lastIndex - lastSecIndex;
        Double b = (modulus / modulusLeft) * (lastIndex - lastSecIndex);
        return a + b * year;
    }


//    private static Double getExpectOne(double value, int year, Double modulus ) {
//        Double modulusLeft = 1 - modulus;
//        Double oneIndex = value;
//        Double twoIndex = value;
//        oneIndex = modulus * value + modulusLeft * oneIndex;
//        twoIndex = modulus * oneIndex + modulusLeft * twoIndex;
//        Double a = 2 * oneIndex - twoIndex;
//        Double b = (modulus / modulusLeft) * (oneIndex - twoIndex);
//        return a + b * year;
//    }


    @RequiresPermissions("analysis:trendForecast:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CTaskChfz cTaskChfz,CTaskSczj cTaskSczj,@RequestParam("type") String type){

        if(type.equals("虫害")){
            startPage();
            List<CTaskChfz> list = cTaskChfzService.selectCTaskChfzAnalyze(cTaskChfz);
            return getDataTable(list);
        }else{
            startPage();
            List<CTaskSczj> list = cTaskSczjService.selectCTaskSczjList(cTaskSczj);
            return getDataTable(list);

        }

    }


}
