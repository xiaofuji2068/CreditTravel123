package com.sanqing.controller;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.sanqing.entity.JsonResult;
import com.sanqing.service.LabelService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LabelController {
    /**
     * 查询评价标签
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "label/queryCommentLabel", method = RequestMethod.POST)
    public @ResponseBody
    JsonResult queryCommentLabel(@RequestParam("id") int id) {
        JsonResult jsonResult;
        Object label = null;
        try {
            label = LabelService.service.queryCommentLabel(id);
        } catch (DataAccessException e) {
            final Throwable cause = e.getCause();
            if (cause instanceof MySQLIntegrityConstraintViolationException) {
                jsonResult = new JsonResult("", "数据库异常", "1");
                return jsonResult;
            }
        }
        jsonResult = new JsonResult(label, "查询评价标签成功", "0");
        return jsonResult;
    }

    /**
     * 查询投诉标签
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "label/queryComplaintLabel", method = RequestMethod.POST)
    public @ResponseBody
    JsonResult queryComplaintLabel(@RequestParam("id") int id) {
        JsonResult jsonResult;
        Object label = null;
        try {
            label = LabelService.service.queryCompliantLabel(id);
        } catch (DataAccessException e) {
            final Throwable cause = e.getCause();
            if (cause instanceof MySQLIntegrityConstraintViolationException) {
                jsonResult = new JsonResult("", "数据库异常", "1");
                return jsonResult;
            }
        }
        jsonResult = new JsonResult(label, "查询投诉标签成功", "0");
        return jsonResult;
    }

    /**
     * 查询服务项目
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "label/queryServiceItems", method = RequestMethod.POST)
    public @ResponseBody
    JsonResult queryServiceItems(@RequestParam("id") int id) {
        JsonResult jsonResult;
        Object label = null;
        try {
            label = LabelService.service.queryServiceItems(id);
        } catch (DataAccessException e) {
            final Throwable cause = e.getCause();
            if (cause instanceof MySQLIntegrityConstraintViolationException) {
                jsonResult = new JsonResult("", "数据库异常", "1");
                return jsonResult;
            }
        }
        jsonResult = new JsonResult(label, "查询服务项目成功", "0");
        return jsonResult;
    }
}
