package com.nd.controller;

import com.nd.entity.MonthRecord;
import com.nd.entity.ObjectRecord;
import com.nd.entity.Record;
import com.nd.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sun.security.pkcs11.Secmod;

import java.sql.SQLException;
import java.util.List;

/*
 * Created with IntelliJ IDEA.
 * ClassName: RecordController
 * User: 123
 * Date: 2022/7/17
 * Time: 9:17
 * Description:销售记录控制层
 */
@Controller
@RequestMapping("/record/")
public class RecordController {
    @Autowired//控制层调用业务层
    private RecordService recordService;
    @RequestMapping("query.do")
    public String query(){
        return "query";
    }
    //展示销售情况
    @RequestMapping("view.do")
    public String view(Model model,
            @RequestParam(name = "pName",defaultValue = "")String pName){
        List<Record> record1 = recordService.getRecord(pName);
        model.addAttribute("records",record1);
        return "view";
    }
    //展示全年营收情况
    @RequestMapping("viewallyear.do")
    public  String viewallyear(Model model) throws SQLException, ClassNotFoundException {
        List<MonthRecord> record2=recordService.getMonth();
        List<ObjectRecord> record3=recordService.getObject();
        model.addAttribute("months",record2);
        model.addAttribute("objects",record3);
        return "viewallyear";
    }
    @RequestMapping("viewmonth.do")
    public String viewmonth(Model model,
                       @RequestParam(name = "pmonth",defaultValue = "")Integer pmonth){
        List<Record> record1 = recordService.getpermonth(pmonth);
        model.addAttribute("permonths",record1);
        return "viewmonth";
    }
}
