package cn.com.jgre.util;

import cn.com.jgre.entity.Messageinfo;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

/**
 * Created by EPC341 on 2018/8/6.
 */
public class MailUtil {
    public static final String SMTPSERVER = "smtp.qq.com";
    public static final String SMTPPORT = "465";
    public static final String ACCOUT = "408584487@qq.com";
    public static final String TOACCOUT = "info@yostarlighting.com"; //"info@yostarlighting.com"
    public static final String PWD = "wwz13670057520";

    public static void sendEmail(Messageinfo messageInfo) throws Exception {
        /* 创建邮件配置 */
        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp"); // 使用的协议（JavaMail规范要求）
        props.setProperty("mail.smtp.host", SMTPSERVER); // 发件人的邮箱的 SMTP 服务器地址
        props.setProperty("mail.smtp.port", SMTPPORT);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.auth", "true"); // 需要请求认证
        props.setProperty("mail.smtp.ssl.enable", "true");// 开启ssl


        // 根据邮件配置创建会话，注意session别导错包
        Session session = Session.getDefaultInstance(props);
        // 开启debug模式，可以看到更多详细的输入日志
        session.setDebug(true);
        //创建邮件
        MimeMessage message = createEmail(session, messageInfo);
        //获取传输通道
        Transport transport = session.getTransport();
        transport.connect(SMTPSERVER, ACCOUT, PWD);
        //连接，并发送邮件
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }

    public static MimeMessage createEmail(Session session, Messageinfo messageInfo) throws Exception {
        // 根据会话创建邮件
        MimeMessage msg = new MimeMessage(session);
        // address邮件地址, personal邮件昵称, charset编码方式
        InternetAddress fromAddress = new InternetAddress(ACCOUT, "System admin", "utf-8");
        // 设置发送邮件方
        msg.setFrom(fromAddress);
        InternetAddress receiveAddress = new InternetAddress(TOACCOUT, "", "utf-8");
        // 设置邮件接收方
        msg.setRecipient(Message.RecipientType.TO, receiveAddress);
        // 设置邮件标题
        msg.setSubject("Yostar Lighting 客户留言", "utf-8");
        String phone = StringUtil.isEmpty(messageInfo.getMessage_person_tel()) ? "" : messageInfo.getMessage_person_tel();
        String contry = StringUtil.isEmpty(messageInfo.getMessage_person_address()) ? "" : messageInfo.getMessage_person_address();
        String companyName = StringUtil.isEmpty(messageInfo.getMessage_person_company()) ? "" : messageInfo.getMessage_person_company();
        String mailText = "    Dear boss, 以下是客户留言内容请查阅。\r\n\r\n";
        mailText += "        E-Mail：  " + messageInfo.getMessage_person_email() + "\r\n";
        mailText += "        Name：  " + messageInfo.getMessage_person() + "\r\n";
        mailText += "        Phone：  " + phone + "\r\n";
        mailText += "        Contry：  " + contry + "\r\n";
        mailText += "        Company name：  " + companyName + "\r\n";
        mailText += "        Subject：  " + messageInfo.getMessage_title() + "\r\n";
        mailText += "        Message：  " + messageInfo.getMessage_content() + "\r\n";
        msg.setText(mailText);
        // 设置显示的发件时间
        msg.setSentDate(new Date());
        // 保存设置
        msg.saveChanges();
        return msg;
    }

    public static void main(String[] args) {
        Messageinfo messageinfo = new Messageinfo();
        messageinfo.setMessage_content("message");
        messageinfo.setMessage_person("windy");
        messageinfo.setMessage_person_email("2343432@qq.com");
        try {
            sendEmail(messageinfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
