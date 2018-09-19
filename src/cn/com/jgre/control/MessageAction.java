package cn.com.jgre.control;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import cn.com.jgre.util.MailUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;

import cn.com.jgre.entity.Messageinfo;
import cn.com.jgre.service.MessageService;
import cn.com.jgre.util.DateUtil;

/**
 * <p>类描述</p>
 * <p>
 * 类说明
 *
 * @author jgre
 * @version 1.0
 *          <p>
 *          创建时间 2014-11-19 下午05:56:35
 */
@Controller
@RequestMapping("/")
public class MessageAction {

    @Resource
    MessageService messageService;

    /*
     * 提交留言
     */
    @RequestMapping(value = "sendMsg.do", method = RequestMethod.POST)
    public String sendMsg(Messageinfo message, Model model) {
        message.setMessage_id(UUID.randomUUID().toString());
        message.setMessage_time(DateUtil.getCurrentTime());
        message.setIsLook("0");//未读标志
        int ret = messageService.addMessageinfo(message);
        if (ret < 0) {
            model.addAttribute("errorMsg", "error");
        } else {
            try {
                MailUtil.sendEmail(message); // 发送邮件
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "common/jsp/json";
    }


    /*
     * 获取留言
     */
    @RequestMapping(value = "getMessages.do", method = RequestMethod.POST)
    public String getMessages(Messageinfo message, Model model, int offset, int limit) {
        List<Messageinfo> mes = messageService.getMessages(message, offset, limit);
        int totalProperty = messageService.getTotalMessageinfo(message);
        model.addAttribute("jsonString", JSON.toJSONString(mes));
        model.addAttribute("totalProperty", totalProperty);
        return "common/jsp/json";
    }


    /*
     * 删除
     */
    @RequestMapping(value = "deleteMessage.do", method = RequestMethod.POST)
    public String deleteArticle(@RequestParam String deleteId, Model model) {
        if (deleteId != null) {
            String errorMsg = "";
            String[] ids = deleteId.split("\\$");
            for (String id : ids) {
                int ret = messageService.deleteMessage(id);
                if (ret < 0) {
                    errorMsg = errorMsg + id;
                    model.addAttribute("errorMsg", errorMsg);
                }
            }
        }
        return "common/jsp/json";
    }

    /*
     * 根据id获取
     */
    @RequestMapping(value = "getMsgByid.do", method = RequestMethod.POST)
    public String getMsgByid(@RequestParam String messageid, Model model) {
        Messageinfo m = messageService.getMessageinfo(messageid);
        if (m != null) {
            if ("0".equals(m.getIsLook()) || m.getIsLook() == null) {
                m.setIsLook("1");
                messageService.updateMessageinfo(m);
            }
        }
        model.addAttribute("jsonString", JSON.toJSONString(m));
        return "common/jsp/pojo";
    }
}
