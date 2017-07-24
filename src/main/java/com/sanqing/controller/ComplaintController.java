package com.sanqing.controller;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.sanqing.entity.Complaint;
import com.sanqing.entity.JsonResult;
import com.sanqing.service.ComplaintService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by admin on 2017/7/22.
 */
@Controller
public class ComplaintController {
    @RequestMapping(value = "complaint/addComplaint")
    public @ResponseBody
    JsonResult addComplaint(@RequestBody Complaint complaint){
        JsonResult jsonResult = null;

        try{
            ComplaintService.service.insertSelective(complaint);
        }catch(DataAccessException e) {
            final Throwable cause = e.getCause();
            if (cause instanceof MySQLIntegrityConstraintViolationException) {
                jsonResult = new JsonResult("", "数据库异常", "2");
                return jsonResult;
            }
        }

        jsonResult = new JsonResult(complaint, "新增投诉信息成功", "0");

        return jsonResult;
    }

}
