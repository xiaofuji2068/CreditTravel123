package com.sanqing.controller;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.sanqing.entity.Comment;
import com.sanqing.entity.JsonResult;
import com.sanqing.service.CommentService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by admin on 2017/7/24.
 */
@Controller
public class CommentController {
    /**
     * 添加评论
     *
     * @param comment
     * @return
     */
    @RequestMapping(value = "comment/addComment", method = RequestMethod.POST)
    public @ResponseBody
    JsonResult addComment(@RequestBody Comment comment) {
        JsonResult jsonResult;
        try {
            CommentService.service.insertSelective(comment);
        } catch (DataAccessException e) {
            final Throwable cause = e.getCause();
            if (cause instanceof MySQLIntegrityConstraintViolationException) {
                jsonResult = new JsonResult("", "数据库异常", "1");
                return jsonResult;
            }
        }
        jsonResult = new JsonResult(comment, "新增评论信息成功", "0");
        return jsonResult;
    }

    /**
     * 查询评论信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "comment/queryComment", method = RequestMethod.POST)
    public @ResponseBody
    JsonResult queryComment(@RequestParam("id") int id) {
        JsonResult jsonResult;
        Object comment = null;
        try {
            comment = CommentService.service.queryComment(id);
        } catch (DataAccessException e) {
            final Throwable cause = e.getCause();
            if (cause instanceof MySQLIntegrityConstraintViolationException) {
                jsonResult = new JsonResult("", "数据库异常", "1");
                return jsonResult;
            }
        }
        jsonResult = new JsonResult(comment, "查询评论信息成功", "0");
        return jsonResult;
    }

    /**
     * 查询个人评论信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "comment/queryCommentByUserId", method = RequestMethod.POST)
    public @ResponseBody
    JsonResult queryCommentByUserId(@RequestParam("id") int id) {
        JsonResult jsonResult;
        Object comment = null;
        try {
            comment = CommentService.service.queryCommentByUserId(id);
        } catch (DataAccessException e) {
            final Throwable cause = e.getCause();
            if (cause instanceof MySQLIntegrityConstraintViolationException) {
                jsonResult = new JsonResult("", "数据库异常", "1");
                return jsonResult;
            }
        }
        jsonResult = new JsonResult(comment, "查询个人评论成功", "0");
        return jsonResult;
    }

    /**
     * 查询商户评论信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "comment/queryCommentByMerchantId", method = RequestMethod.POST)
    public @ResponseBody
    JsonResult queryCommentByMerchantId(@RequestParam("id") int id) {
        JsonResult jsonResult;
        Object comment = null;
        try {
            comment = CommentService.service.queryCommentByMerchantId(id);
        } catch (DataAccessException e) {
            final Throwable cause = e.getCause();
            if (cause instanceof MySQLIntegrityConstraintViolationException) {
                jsonResult = new JsonResult("", "数据库异常", "1");
                return jsonResult;
            }
        }
        jsonResult = new JsonResult(comment, "查询商户评论成功", "0");
        return jsonResult;
    }
}
//Github测试提交