package com.sanqing.controller;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.sanqing.entity.JsonResult;
import com.sanqing.entity.Subscribe;
import com.sanqing.service.SubscribeService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by admin on 2017/7/12.
 */
@Controller
public class SubscribeController {
    /**
     * 预约提交
     *
     * @param subscribe
     * @return
     */
    @RequestMapping(value = "subscribe/submitSubscribe", method = RequestMethod.POST)
    public @ResponseBody
    JsonResult submitSubscribe(@RequestBody Subscribe subscribe) {
        JsonResult jsonResult = null;

        //必要参数判空

        //插入订单生成时间

        //保存订单
        try {
            SubscribeService.service.insertSelective(subscribe);
            jsonResult = new JsonResult("", "预约成功", "1");
        } catch (DataAccessException e) {
            final Throwable cause = e.getCause();
            if (cause instanceof MySQLIntegrityConstraintViolationException) {
                jsonResult = new JsonResult("", "数据库异常", "2");
            }
        }
        return jsonResult;
    }

    /**
     * 预约详情
     *
     * @param orderId
     * @return
     */
    @RequestMapping(value = "subscribe/querySubscribe", method = RequestMethod.POST)
    public @ResponseBody
    JsonResult querySubscribe(@RequestParam("orderId") int orderId) {
        JsonResult jsonResult = null;
        Subscribe order = null;
        try {
            order = SubscribeService.service.selectByPrimaryKey(orderId);
            jsonResult = new JsonResult(order, "查询成功", "0");
        } catch (DataAccessException e) {
            final Throwable cause = e.getCause();
            if (cause instanceof MySQLIntegrityConstraintViolationException) {
                jsonResult = new JsonResult(order, "数据库异常", "2");
            }
        }
        return jsonResult;
    }

    /**
     * 签到
     *
     * @param orderId
     * @return
     */
    @RequestMapping(value = "subscribe/signInSubscribe", method = RequestMethod.POST)
    public @ResponseBody
    JsonResult cancelSubscribe(@RequestParam("orderId") int orderId) {
        JsonResult jsonResult = null;

        //查询出选择订单
        Subscribe subscribe = null;
        try {
            subscribe = SubscribeService.service.selectByPrimaryKey(orderId);
        } catch (DataAccessException e) {
            final Throwable cause = e.getCause();
            if (cause instanceof MySQLIntegrityConstraintViolationException) {
                jsonResult = new JsonResult("", "数据库异常", "2");
                return jsonResult;
            }
        }

        if (null != subscribe && subscribe.getSignIn() != null && subscribe.getSignIn() != "") {
            subscribe.setSignIn("0");//签到
            try {
                SubscribeService.service.updateByPrimaryKeySelective(subscribe);
                jsonResult = new JsonResult("", "取消订单成功", "1");
            } catch (DataAccessException e) {
                final Throwable cause = e.getCause();
                if (cause instanceof MySQLIntegrityConstraintViolationException) {
                    jsonResult = new JsonResult("", "数据库异常", "2");
                }
            }

        } else {
            jsonResult = new JsonResult("", "数据库异常", "2");
        }
        return jsonResult;
    }


    /**
     * 修改订单状态：预约、取消、完成
     *
     * @param orderId
     * @return
     */
    @RequestMapping(value = "subscribe/updateSubscribe", method = RequestMethod.POST)
    public @ResponseBody
    JsonResult cancelSubscribe(@RequestParam("orderId") int orderId, @RequestParam("status") String status) {
        JsonResult jsonResult = null;

        //查询出选择订单
        Subscribe subscribe = null;
        try {
            subscribe = SubscribeService.service.selectByPrimaryKey(orderId);
        } catch (DataAccessException e) {
            final Throwable cause = e.getCause();
            if (cause instanceof MySQLIntegrityConstraintViolationException) {
                jsonResult = new JsonResult("", "数据库异常", "2");
                return jsonResult;
            }
        }

        //取消预约判断

        if (null != subscribe && subscribe.getSignIn() != null && subscribe.getStatus() != "") {
            subscribe.setStatus(status);
            try {
                SubscribeService.service.updateByPrimaryKeySelective(subscribe);
                jsonResult = new JsonResult("", "修改订单状态成功", "1");
            } catch (DataAccessException e) {
                final Throwable cause = e.getCause();
                if (cause instanceof MySQLIntegrityConstraintViolationException) {
                    jsonResult = new JsonResult("", "数据库异常", "2");
                }
            }

        } else {
            jsonResult = new JsonResult("", "该订单不存在", "2");
        }
        return jsonResult;
    }
}

