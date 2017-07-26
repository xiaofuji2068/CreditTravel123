package com.sanqing.controller;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.sanqing.entity.Complaint;
import com.sanqing.entity.JsonResult;
import com.sanqing.service.ComplaintService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by admin on 2017/7/22.
 */
@Controller
public class ComplaintController {
    /**
     * 添加投诉信息
     *
     * @param complaint
     * @return
     */
    @RequestMapping(value = "complaint/addComplaint", method = RequestMethod.POST)
    public @ResponseBody
    JsonResult addComplaint(@RequestBody Complaint complaint) {
        JsonResult jsonResult;
        try {
            ComplaintService.service.insertSelective(complaint);
        } catch (DataAccessException e) {
            final Throwable cause = e.getCause();
            if (cause instanceof MySQLIntegrityConstraintViolationException) {
                jsonResult = new JsonResult("", "数据库异常", "2");
                return jsonResult;
            }
        }
        jsonResult = new JsonResult(complaint, "新增投诉信息成功", "0");
        return jsonResult;
    }

    /**
     * 查询投诉信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "complaint/queryComplaint", method = RequestMethod.POST)
    public @ResponseBody
    JsonResult queryComplaint(@RequestParam("id") int id) {
        JsonResult jsonResult;
        Object complaint = null;
        try {
            ComplaintService.service.queryComplaint(id);
        } catch (DataAccessException e) {
            final Throwable cause = e.getCause();
            if (cause instanceof MySQLIntegrityConstraintViolationException) {
                jsonResult = new JsonResult("", "数据库异常", "2");
                return jsonResult;
            }
        }
        jsonResult = new JsonResult(complaint, "查询投诉信息成功", "0");
        return jsonResult;
    }
}