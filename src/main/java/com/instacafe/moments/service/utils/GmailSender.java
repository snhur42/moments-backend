package com.instacafe.moments.service.utils;

import com.instacafe.moments.model.user.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Service("gmailSender")
public class GmailSender {
    private final JavaMailSender javaMailSender;

    @Autowired
    public GmailSender(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmailToClientAboutRegistration(AppUser client) {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(client.getEmail());

        msg.setSubject("Реєстрація в системі Moments Instacafe пройшла успішно");
        msg.setText("<div class=\"card\" style=\"width: 18rem;\">\n" +
                "  <img class=\"card-img-top\" src=\"...\" alt=\"Card image cap\">\n" +
                "  <div class=\"card-body\">\n" +
                "    <h5 class=\"card-title\">Card title</h5>\n" +
                "    <p class=\"card-text\">Some quick example text to build on the card title and make up the bulk of the card's content.</p>\n" +
                "  </div>\n" +
                "</div>");



        javaMailSender.send(msg);

    }

    public void sendNewPassword(AppUser appUser, String newPassword) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(appUser.getEmail());

        msg.setSubject("Новий пароль -  Moments Instacafe!");
        msg.setText(newPassword);

        javaMailSender.send(msg);
    }

//    void sendEmailWithAttachment() throws MessagingException, IOException {
//
//        MimeMessage msg = javaMailSender.createMimeMessage();
//
//        // true = multipart message
//        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
//        helper.setTo("snhur42@gmail.com");
//
//        helper.setSubject("Testing from Spring Boot");
//
//        // default = text/plain
//        //helper.setText("Check attachment for image!");
//
//        // true = text/html
//        helper.setText("<h1>Check attachment for image!</h1>", true);
//
//        helper.addAttachment("my_photo.png", new ClassPathResource("android.png"));
//
//        javaMailSender.send(msg);
//
//    }


}
