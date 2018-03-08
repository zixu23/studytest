package tools.mail;

import org.apache.commons.lang.RandomStringUtils;

import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
/**
 * 邮件发送操作类
 *
 * @author zhangdi
 *
 */

public class MailOperation {

/**
         * 发送邮件
         * @param user 发件人邮箱
         * @param password 授权码（注意不是邮箱登录密码）
         * @param host
         * @param from 发件人
         * @param to 接收者邮箱
         * @param subject 邮件主题
         * @param content 邮件内容
         * @return success 发送成功 failure 发送失败
         * @throws Exception*/


        public String sendMail(String user, String password, String host,
                String from, String to, String subject, String content)
                throws Exception {
            if (to != null){
                Properties props = System.getProperties();
                props.put("mail.smtp.host", host);
                props.put("mail.smtp.auth", "true");
                MailAuthenticator auth = new MailAuthenticator();
                MailAuthenticator.USERNAME = user;
                MailAuthenticator.PASSWORD = password;
                Session session = Session.getInstance(props, auth);
                session.setDebug(true);
                try {
                    MimeMessage message = new MimeMessage(session);
                    message.setFrom(new InternetAddress(from));
                    if (!to.trim().equals(""))
                        message.addRecipient(Message.RecipientType.TO,
                                new InternetAddress(to.trim()));
                    message.setSubject(subject);
                    MimeBodyPart mbp1 = new MimeBodyPart(); // 正文
                    mbp1.setContent(content, "text/html;charset=utf-8");
                    Multipart mp = new MimeMultipart(); // 整个邮件：正文+附件
                    mp.addBodyPart(mbp1);
                    // mp.addBodyPart(mbp2);
                    message.setContent(mp);
                    message.setSentDate(new Date());
                    message.saveChanges();
                    Transport trans = session.getTransport("smtp");
                    trans.send(message);
                    System.out.println(message.toString());
                } catch (Exception e){
                    e.printStackTrace();
                    return "failure";
                }
                return "success";
            }else{
                return "failure";
            }
        }

    public static void main(String[] args) {

        MailOperation operation = new MailOperation();
        String user = "sunmm@xinguangnet.com";
        String password = "HK930323smm";
        String host = "smtp.exmail.qq.com";
        String from = "sunmm@xinguangnet.com";
        String to = "qingjian323@163.com";// 收件人
        String subject = "输入邮件主题";
        //邮箱内容
        StringBuffer sb = new StringBuffer();
        //String yzm = RandomUtil.getRandomString(6);
        String yzm = RandomStringUtils.random(6);
        sb.append("11");
        try {
            String res = operation.sendMail(user, password, host, from, to,
                    subject, sb.toString());
            System.out.println(res);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
