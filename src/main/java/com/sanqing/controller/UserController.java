package com.sanqing.controller;

import com.bcloud.msg.http.HttpSender;
import com.sanqing.entity.JsonResult;
import com.sanqing.entity.SmsMessage;
import com.sanqing.entity.User;
import com.sanqing.kit.CommonUtil;
import com.sanqing.service.CountService;
import com.sanqing.service.UserService;
import com.sms.webservice.client.SmsWebClient;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.*;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by admin on 2017/6/27.
 */
@Controller
//@SessionAttributes({"code", "User", "ChangPasswordCode"})
public class UserController {

    private static Logger log = Logger.getLogger(UserController.class);
    SmsMessage smsMessage = new SmsMessage();

    @RequestMapping(value = "user/login", method = POST)
    public @ResponseBody
    Map login(@RequestParam("mobile") String mobile, @RequestParam("password") String password, Model model, HttpSession session) {
        Map map = new HashMap();
        JsonResult jsonResult = new JsonResult();
        List<User> list = UserService.service.selectUserByMobile(mobile);
        if (null == list || list.isEmpty()) {
            jsonResult.setData("");
            jsonResult.setResultCode("1");
            jsonResult.setResultMessage("用户名不存在");
            map.put("user", jsonResult);

        } else {
            if (list.get(0).getPassword().equals(password)) {
                jsonResult.setData(list.get(0));
                jsonResult.setResultCode("0");
                jsonResult.setResultMessage("登录成功");
//                model.addAttribute("User", list.get(0));
                session.setAttribute("User", list.get(0));
                String accessToken = UUID.randomUUID().toString();
                session.setAttribute("accessToken", accessToken);

                map.put("user", jsonResult);
                map.put("accessToken", accessToken);
            } else {
                jsonResult.setData("");
                jsonResult.setResultCode("2");
                jsonResult.setResultMessage("密码错误");
                map.put("user", jsonResult);
            }
        }
        return map;
    }

    @RequestMapping(value = "user/register", method = RequestMethod.POST)
    public @ResponseBody
    JsonResult register(@RequestParam("mobile") String mobile,
                        @RequestParam("code") String code,
                        @RequestParam("password") String password,
                        Model model, HttpServletRequest request) {
        JsonResult jsonResult = new JsonResult();

        //手机和验证码不能为空
        if (null == mobile || null == password) {
            jsonResult.setData("");
            jsonResult.setResultCode("2");
            jsonResult.setResultMessage("手机号或密码为空");
            return jsonResult;
        }

        if (null == code) {
            jsonResult.setData("");
            jsonResult.setResultCode("3");
            jsonResult.setResultMessage("验证为空");
            return jsonResult;
        }

        //检查手机号码是否已被注册
        List<User> userList = UserService.service.selectUserByMobile(mobile);
        if (null != userList && userList.size() > 0) {
            jsonResult.setData("");
            jsonResult.setResultCode("1");
            jsonResult.setResultMessage("该手机号码已被注册");
            return jsonResult;
        }

        /** 获取session中存放的手机短信验证码 */
        String sessionCode = (String) request.getSession().getAttribute("code");

        //校验验证码是否正确
        if (!checkCode(mobile, code, request, sessionCode)) {
            jsonResult.setData("");
            jsonResult.setResultCode("3");
            jsonResult.setResultMessage("验证码不正确");
            return jsonResult;
        }

        User user = new User();
        user.setMobile(mobile);
        user.setPassword(password);
        int flag = UserService.service.insertUser(user);
        if (1 == flag) {
            jsonResult.setData("");
            jsonResult.setResultCode("0");
            jsonResult.setResultMessage("注册成功");
        } else {
            jsonResult.setData("");
            jsonResult.setResultCode("4");
            jsonResult.setResultMessage("注册失败");
        }
        return jsonResult;
    }

    //检查校验码是否正确
    private boolean checkCode(String mobile, String code, HttpServletRequest request, String sessionCode) {

        if (null != sessionCode) {
            if (sessionCode != code && !sessionCode.equals(code)) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }


    @RequestMapping(value = "user/UserAction_selectUser", method = RequestMethod.POST)
    public @ResponseBody
    JsonResult selectUserByMobile(@RequestParam("userId") int userId) {
        log.info("enter selectUserByMobile;userId=" + userId);
        User user = UserService.service.selectByPrimaryKey(userId);
        log.info("exit selectByPrimaryKey;User=" + user);
        if (null != user) {
            return new JsonResult(user, "查询用户信息成功", "0");
        } else {
            return new JsonResult("", "未查询到用户信息", "1");
        }
    }


    //生成验证码
    @RequestMapping(value = "user/UserAction_sms", method = POST)
    public @ResponseBody
    JsonResult userActionSms(@RequestParam("mobile") String mobile, Model model, HttpServletRequest request, HttpSession session) throws IOException {
        Enumeration<String> requestHeaderNames = request.getHeaderNames();
        while (requestHeaderNames.hasMoreElements()) {
            String name = requestHeaderNames.nextElement();
            String value = request.getHeader(name);
            log.info("enter userActionSms;head name：" + name + ";;head value:" + value);
        }
        log.info("enter userActionSms;mobile：" + mobile);

        JsonResult jsonResult;

        //生成验证码
//        String code = (int) ((Math.random() * 9 + 1) * 100000) + "";
        String code = "888168";

        //code存session
        if (null != code) {
            session.setAttribute("code", code);
        }

        // 短信内容+随机生成的6位短信验证码
//        String content = "注册验证码为:" + code;
        String content = "感谢您的注册示远科技会员，验证码888168，请及时输入验证码进行登陆。";

        /** 发送短信之前先统计一个已经发送的短信条数 */
        List<SmsMessage> allRecord = CountService.service.findAllRecord(mobile);
        int messageCount = allRecord.size();
        log.info("已发短信条数为：" + messageCount);
        if (messageCount < 5) {
            String uri = "http://send.18sms.com/msg/HttpBatchSendSM";//应用地址
            String account = "czq15606502317";//账号
            String pswd = "@sykj2017";//密码
            String mobiles = mobile;//手机号码，多个号码使用","分割
            boolean needstatus = true;//是否需要状态报告，需要true，不需要false
            String product = "";//产品ID(不用填写)
            String extno = "";//扩展码(请登陆网站用户中心——>服务管理找到签名对应的extno并填写，线下用户请为空)
            String returnString = null;
            try {
                returnString = HttpSender.batchSend(uri, account, pswd, mobiles, content, needstatus, product, extno);
                System.out.println(returnString);
                //TODO 处理返回值,参见HTTP协议文档
            } catch (Exception e) {
                //TODO 处理异常
                e.printStackTrace();
            }
            if (null == returnString) {
                jsonResult = new JsonResult("", "失败", "1");
            } else {
                jsonResult = new JsonResult("", "成功", "0");
                smsMessage.setMobile(mobile);// 手机号码
                smsMessage.setContent(content);// 短信验证码
                smsMessage.setDateline(String.valueOf(CommonUtil.getNowDate()));// 短信发送时间
                if (smsMessage != null) {
                    CountService.service.saveEntity(smsMessage);
                    log.info("短信验证码发送记录保存成功!");
                }
            }
        } else {
            jsonResult = new JsonResult("", "一个手机号码最多发送5条短信验证码", "2");
            log.info("该手机号码今天发送验证码过多");
        }

        log.info("exit userActionSms;jsonResult = " + jsonResult);
        log.info("exit userActionSms;jsonResult.getResultCode: = " + jsonResult.getResultCode());
        log.info("exit userActionSms;jsonResult.getResultMessage: = " + jsonResult.getResultMessage());
//        log.info("exit userActionSms;jsonResult.getData: = " + jsonResult.getData().toString());
        return jsonResult;
    }

    public static boolean initClient() {
        /**
         * 判断客户端是否已经初始化
         */
        String url = "url";
        String userName = "userName";
        String passWord = "passWord";
        if (!SmsWebClient.enable()) {
            int ret = 0;
            try {
                ret = SmsWebClient.init(url, userName, passWord);
                if (ret == -1 || !SmsWebClient.enable()) {
                    log.info("短信平台接口初始化失败！");
                    return false;
                }
                log.info("短信平台接口初始化成功！" + ret + "-----");
            } catch (Exception ex) {
                ex.printStackTrace();
                log.info("短信平台接口初始化过程中异常！");
            }
        }
        return true;
    }

    public static boolean sendMessage(java.lang.String mobilephone,
                                      java.lang.String content, int operId, java.lang.String tosend_time,
                                      int sms_id, short backlist_filter, short fbdword_filter,
                                      short priority, java.lang.String valid_time) {
        // 单个手机号码发送
       /* try {
            SmsReturnObj retObj = SmsWebClient.webSendMessage(mobilephone,
                    content, operId, tosend_time, sms_id, backlist_filter,
                    fbdword_filter, priority, valid_time);
            if (retObj.getReturnCode() != 1) {
                log.info("短信发送失败，原因为：" + retObj.getReturnMsg());
                return false;
            } else {
                log.info("短信发送成功！返回结果为：" + retObj.getReturnMsg());
                return true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            log.info("短信发送过程发生异常!");
        }*/
        return true;
    }

    @RequestMapping(value = "user/TestFindCond", method = POST)
    public String TestFindCode(@RequestParam("jbPhone") String mobile, @RequestParam("code") String code, Model model) {

        List<SmsMessage> list = CountService.service.findAllRecord(mobile);
        return "loginError";
    }

    @RequestMapping(value = "user/TestSaveEntity", method = POST)
    public String SaveEntity(@RequestParam("mobile") String mobile, @RequestParam("code") String code, Model model) {
        SmsMessage smsMessage = new SmsMessage();
        smsMessage.setMobile(mobile);
        smsMessage.setContent(code);
        CountService.service.saveEntity(smsMessage);
        return "loginSuccess";
    }


    @RequestMapping(value = "user/TestQueryCode", method = POST)
    public String testQueryCode(@RequestParam("mobile") String mobile, Model model) {
        List<SmsMessage> list = CountService.service.findAllRecord(mobile);

        int count = list.size();
        model.addAttribute("count", count);
        System.out.println(count);
        return "loginSuccess";
    }

    //新增昵称
    @RequestMapping(value = "user/newNickName", method = POST)
    public @ResponseBody
    JsonResult newNickName(@RequestParam("nickName") String nickName, @RequestParam("accessToken") String accessToken,HttpSession session) {

        JsonResult jsonResult;
        String accessTokenSes = (String)session.getAttribute("accessToken");
        if (accessTokenSes != null && !accessTokenSes.equals(accessToken)) {
            jsonResult = new JsonResult("", "请登录", "2");
            return jsonResult;
        }
        User user1 = (User) session.getAttribute("User");

        User user = new User();
        user.setUserId(user1.getUserId());
        user.setNickname(nickName);

        int flag = UserService.service.updateByPrimaryKeySelective(user);

        if (1 == flag) {
            jsonResult = new JsonResult(user, "修改昵称成功", "0");
        } else {
            jsonResult = new JsonResult("", "修改昵称失败", "1");
        }
        return jsonResult;
    }


    //重置密码 生成验证码
    @RequestMapping(value = "user/ChangePassword_sms", method = POST)
    public @ResponseBody
    JsonResult changPasswordUms(@RequestParam("mobile") String mobile, Model model, HttpSession session) throws IOException {
        log.info("enter changPasswordUms;mobile：" + mobile);

        JsonResult jsonResult = new JsonResult();
        List<User> list = UserService.service.selectUserByMobile(mobile);
        log.info("exit selectUserByMobile;List<User>=" + list);
        if (null == list || list.isEmpty()) {
            jsonResult = new JsonResult("", "当前手机未注册", "3");
            return jsonResult;
        }

        //生成验证码
        String ChangPasswordCode = (int) ((Math.random() * 9 + 1) * 100000) + "";

        //ChangPasswordCode存session
        if (null != ChangPasswordCode) {
//            model.addAttribute("ChangPasswordCode", ChangPasswordCode);
            session.setAttribute("ChangPasswordCode", ChangPasswordCode);
        }
//        if(!initClient()) {
//            return null;
//        }

        // 短信内容+随机生成的6位短信验证码
        String content = "修改密码验证码为:" + ChangPasswordCode;
        // 操作用户的ID
//        Integer operId = Integer.parseInt(Env.getInstance().getProperty("operId"));
        Integer operId = 2;
        // 定时发送的的发送时间(缺省为空，如果即时发送，填空)
        String tosend_time = "";
        // 应用系统的短信ID，用户查询该短信的状态报告(缺省为0，即不需查询短信的状态报告)
        int sms_id = 0;
        // 黑名单过滤(0：不需要黑名单过滤，1：需要黑名单过滤，缺省为0)
        short backlist_filter = 0;
        // 禁止语过滤(0：不需要禁止语过滤，1：需要禁止语过滤，缺省为0)
        short fbdword_filter = 0;
        // 优先级(值越大优先级越高，0：普通，1,：优先，2：最高，缺省为0)
        short priority = 0;
        // 短信有效时间(格式为：YYYY-MM-DD HH:mm:ss目前为空)
        String valid_time = "";
        /** 发送短信之前先统计一个已经发送的短信条数 */
        //可以改为当天发送短信条数
        List<SmsMessage> allRecord = CountService.service.findAllRecord(mobile);

        int messageCount = allRecord.size();
        log.info("已发短信条数为：" + messageCount);
        if (messageCount < 5) {
            /** 单个手机号发送短信 */
            if (!sendMessage(mobile, content, operId, tosend_time, sms_id,
                    backlist_filter, fbdword_filter, priority, valid_time)) {
                jsonResult = new JsonResult("", "失败", "1");
            } else {
                jsonResult = new JsonResult("", "成功", "0");

                /** 发送一条短信，记录一条短信记录，方便之后的统计短信发送次数 */
                smsMessage.setMobile(mobile);// 手机号码
                smsMessage.setContent(content);// 短信验证码
                smsMessage.setDateline(String.valueOf(CommonUtil.getNowDate()));// 短信发送时间
                if (smsMessage != null) {
                    CountService.service.saveEntity(smsMessage);
                    log.info("短信验证码发送记录保存成功!");
                }
            }
        } else {
            jsonResult = new JsonResult("", "一个手机号码最多发送5条短信验证码", "2");
            log.info("该手机号码今天发送验证码过多");
        }

        log.info("exit userActionSms;jsonResult = " + jsonResult);
        return jsonResult;
    }

    //重置密码
    @RequestMapping(value = "user/resetPassword", method = RequestMethod.POST)
    public @ResponseBody
    JsonResult reetPassword(@RequestParam("mobile") String mobile,
                            @RequestParam("code") String code, @RequestParam("password") String password,
                            Model model, HttpServletRequest request) {
        JsonResult jsonResult = new JsonResult();

        //手机和验证码不能为空
        if (null == mobile || null == password) {
            jsonResult.setData("");
            jsonResult.setResultCode("2");
            jsonResult.setResultMessage("手机号或验证为空");
            return jsonResult;
        }

        if (null == code) {
            jsonResult.setData("");
            jsonResult.setResultCode("4");
            jsonResult.setResultMessage("密码为空");
            return jsonResult;
        }

        //检查手机号码是否存在
        List<User> userList = UserService.service.selectUserByMobile(mobile);
        if (null == userList || userList.size() == 0) {
            jsonResult.setData("");
            jsonResult.setResultCode("1");
            jsonResult.setResultMessage("该手机号码不存在");
            return jsonResult;
        }

        /** 获取session中存放的手机短信验证码 */
        String sessionCode = (String) request.getSession().getAttribute("ChangPasswordCode");

        //校验验证码是否正确
        if (!checkCode(mobile, code, request, sessionCode)) {
            jsonResult.setData("");
            jsonResult.setResultCode("3");
            jsonResult.setResultMessage("验证码不正确");
            return jsonResult;
        }

        User user = new User();
        user.setUserId(userList.get(0).getUserId());
        user.setPassword(password);
        int flag = UserService.service.updateByPrimaryKeySelective(user);
        if (1 == flag) {
            jsonResult.setData("");
            jsonResult.setResultCode("0");
            jsonResult.setResultMessage("修改密码成功");
        } else {
            jsonResult.setData("");
            jsonResult.setResultCode("4");
            jsonResult.setResultMessage("修改密码失败");
        }
        return jsonResult;
    }

    //上传用户头像
    @RequestMapping(value = "user/uploadPhoto", method = POST)
    public @ResponseBody
    JsonResult uploadPhoto(MultipartFile multipartFile, HttpServletRequest request, HttpSession session) {
        JsonResult jsonResult;
        // 判断用户是否登录
        User user = (User) session.getAttribute("User");
        if (user == null) {
            jsonResult = new JsonResult("", "用户未登录", "1");
//            return jsonResult;
        }
        if (multipartFile != null) {

            // 判断上传的文件是否为空
            String path = null;// 文件路径
            String type = null;// 文件类型
            String fileName = multipartFile.getOriginalFilename();// 文件原名称
            System.out.println("上传的文件原名称:" + fileName);
            // 判断文件类型
            type = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()) : null;
            if (type != null) {

                // 判断文件类型是否为空
                if ("GIF".equals(type.toUpperCase()) || "PNG".equals(type.toUpperCase()) || "JPG".equals(type.toUpperCase())) {

                    // 项目在容器中实际发布运行的根路径
                    String realPath = request.getSession().getServletContext().getRealPath("/");

                    // 自定义的文件名称
                    String trueFileName = String.valueOf(System.currentTimeMillis()) + fileName;

                    // 设置存放图片文件的路径
//                    path = realPath +/*System.getProperty("file.separator")+*/trueFileName;
                    path = realPath + "images" + System.getProperty("file.separator") + trueFileName;
                    System.out.println("存放图片文件的路径:" + path);

                    // 转存文件到指定的路径
                    try {
                        multipartFile.transferTo(new File(path));
                    } catch (IOException e) {
                        e.printStackTrace();
                        jsonResult = new JsonResult("", "上传文件失败", "0");
                        return jsonResult;
                    }
                    //保存图片路径到数据库
                    int flag = UserService.service.insterUserPhoto(user, path);

                    if (1 == flag) {
                        System.out.println("文件成功上传到指定目录下");
                        jsonResult = new JsonResult("", "文件成功上传到指定目录下", "0");
                    }else {
                        jsonResult = new JsonResult("", "上传文件失败", "0");
                    }
                } else {
                    System.out.println("不是我们想要的文件类型,请按要求重新上传");
                    jsonResult = new JsonResult("", "不是我们想要的文件类型,请按要求重新上传jpg/png/gif", "2");
                }
            } else {
                System.out.println("文件类型为空");
                jsonResult = new JsonResult("", "文件类型为空", "3");
            }
        } else {
            System.out.println("没有找到相对应的文件");
            jsonResult = new JsonResult("", "没有找到相对应的文件", "4");
        }
        return jsonResult;
    }

    //读取用户头像
    @RequestMapping(value = "user/readPhoto", method = POST)
    public @ResponseBody
    JsonResult readPhoto(HttpServletRequest request, HttpSession session) throws IOException {
        JsonResult jsonResult;
        // 判断用户是否登录
        User user = (User) session.getAttribute("User");
        if (user == null) {
            jsonResult = new JsonResult("", "用户未登录", "1");
//            return jsonResult;
        }
        //使用File封装
        String path = user.getPhoto();
        jsonResult = new JsonResult(path, "读取图片成功", "1");
        return jsonResult;
    }
}
