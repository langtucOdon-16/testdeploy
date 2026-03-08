/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import jakarta.servlet.ServletContext;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author : GiangLT
 * @date : 7/2/2026
 * @description: Lớp tiện ích dùng để hỗ trợ gửi email trong hệ thống
 */
public class EmailUtil {
    private static Properties loadSmtpConfig(ServletContext context) throws Exception {
        String path = context.getInitParameter("smtpConfigFile");
        if (path == null) {
            throw new RuntimeException("smtpConfigFile is not defined in web.xml");
        }
        InputStream is = context.getResourceAsStream(path);
        if (is == null) {
            throw new RuntimeException("Cannot find SMTP congig file: " + path);
        }
        Properties props = new Properties();
        props.load(is);
        return props;
    }
    
    public static void sendEmail(ServletContext context,
                                 String toEmail,
                                 String subject,
                                 String content) {

        try {
            Properties cfg = loadSmtpConfig(context);

            String fromEmail = cfg.getProperty("smtp.user");
            String appPassword = cfg.getProperty("smtp.password");

            Properties props = new Properties();
            props.put("mail.smtp.host", cfg.getProperty("smtp.host"));
            props.put("mail.smtp.port", cfg.getProperty("smtp.port"));
            props.put("mail.smtp.auth", cfg.getProperty("smtp.auth"));
            props.put("mail.smtp.starttls.enable", cfg.getProperty("smtp.starttls.enable"));

            Session session = Session.getInstance(props,
                    new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromEmail, appPassword);
                }
            });

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(
                    fromEmail,
                    cfg.getProperty("smtp.fromName"),
                    "UTF-8"
            ));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(toEmail)
            );
            message.setSubject(subject, "UTF-8");
            message.setContent(content, "text/html; charset=UTF-8");

            Transport.send(message);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
