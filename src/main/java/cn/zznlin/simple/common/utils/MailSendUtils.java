package cn.zznlin.simple.common.utils;

import cn.zznlin.simple.common.init.SystemPropertyInit;
import org.springframework.mail.MailMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 *	内部测试专用邮件系统
 */
public class MailSendUtils {
	private static final String MAILHOST = "smtp.163.com";
	private static final String USERNAME = SystemPropertyInit.getInstance().getProperty("error.mailname");
	private static final String PASSWORD = SystemPropertyInit.getInstance().getProperty("error.password");
	private static final String MAILFROM = "@163.com";
	
	/**
	 * 内部测试专用邮件系统
	 * 
	 * @param toEmail
	 * @param mailSubject
	 * @param mailBody
	 */
	public static void sendMail(String toEmail, String mailSubject,
			String mailBody){    
        
        //创建邮件发送类 JavaMailSender    
        //用于发送基本的文本邮件信息（不能包括附件，及图片）    
        JavaMailSender sender = new JavaMailSenderImpl();
            
        //设置邮件服务主机    
        ((JavaMailSenderImpl)sender).setHost(MAILHOST);    
        //发送者邮箱的用户名    
        ((JavaMailSenderImpl)sender).setUsername(USERNAME);
        //发送者邮箱的密码    
        ((JavaMailSenderImpl)sender).setPassword(PASSWORD);
            
        //配置文件，用于实例化java.mail.session    
        Properties pro = System.getProperties();    
            
        //登录SMTP服务器,需要获得授权，网易163邮箱新近注册的邮箱均不能授权。    
        //测试 sohu 的邮箱可以获得授权    
        pro.put("mail.smtp.auth", "true");    
        pro.put("mail.smtp.socketFactory.port", "25");    
        pro.put("mail.smtp.socketFactory.fallback", "false");    
            
        //通过文件获取信息    
        ((JavaMailSenderImpl)sender).setJavaMailProperties(pro);

        //创建基本邮件信息    
        MailMessage mailMessage = new SimpleMailMessage();    
            
        //发送者地址，必须填写正确的邮件格式，否者会发送失败    
        mailMessage.setFrom(USERNAME+MAILFROM);
        //邮件主题    
        mailMessage.setSubject(mailSubject);    
        //邮件内容，简单的邮件信息只能添加文本信息    
        mailMessage.setText(mailBody);    
        //邮件接收者的邮箱地址    
        mailMessage.setTo(toEmail);    
            
        //发送邮件，参数可以是数组    
        sender.send((SimpleMailMessage)mailMessage);    
    }
	
	/**
	 * 异步发送邮件
	 * 
	 * @param subject
	 * @param mail
	 * @param content
	 */
	public static synchronized void sendSyncErrorMail(final String mail,
			final String subject,
			final String content) {
		new Thread() {
			public void run() {
				sendMail(mail, subject, content);
			}
		}.start();
	}
}
