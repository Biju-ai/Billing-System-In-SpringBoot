package inventroy.Billing.system.Billing.System.services.impl;

import inventroy.Billing.system.Billing.System.dto.request.Email.SendEmailRequest;
import inventroy.Billing.system.Billing.System.util.RequestApi;
import inventroy.Billing.system.Billing.System.util.ResponceApi;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class Mail {
    private final JavaMailSender mailSender;

    public Mail(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
//
//    public RequestApi<?> sendMail(String to, String subject, String text) {
//        try {
//            SimpleMailMessage mailMessage = new SimpleMailMessage();
//            mailMessage.setFrom("shresthabiju6@gmail.com");
//            mailMessage.setTo(to);
//            mailMessage.setSubject(subject);
//            mailMessage.setText(text);
//            mailSender.send(mailMessage);
//            return ResponceApi.success(1, "Mail sent successfully");
//        } catch (Exception e) {
//
//            return ResponceApi.error(0, "Mail sent failed");
//        }
//    }

    public RequestApi<?> sendHtmlMail(SendEmailRequest sendEmailRequest) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            helper.setFrom("shresthabiju6@gmail.com");
            helper.setTo(sendEmailRequest.getReciverEmail());
            helper.setSubject(sendEmailRequest.getSubject());
            helper.setText(sendEmailRequest.getMessage(), true);
            mailSender.send(mimeMessage);
            return ResponceApi.success(1, "Mail sent successfully");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
