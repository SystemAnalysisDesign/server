package com.example.SystemAnalysisDesign.common.service;

import com.example.SystemAnalysisDesign.post.domain.Post;
import com.example.SystemAnalysisDesign.user.domain.User;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MailService {

    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    @Value("${MAIL_SENDER}")
    private String mailSenderAddress;

    @Async("taskExecutor")
    public void sendNotificationEmail(String to, String subject, Post post) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            Context context = new Context();
            context.setVariable("email", to);
            context.setVariable("title", post.getTitle());
            context.setVariable("content", post.getContent());
            String html = templateEngine.process("notification-email", context);

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(html, true);
            helper.setFrom(mailSenderAddress);

            mailSender.send(message);
            log.info("이메일 전송 성공: {}", to);
        } catch (MessagingException e) {
            log.error("이메일 전송 실패: {}", to, e);
        }
    }

    @Async("taskExecutor")
    public void sendNotificationsToUsers(List<User> users, Post post) {
        for (User user : users) {
            String emailSubject = "[새 글 알림] 키워드 관련 글이 등록되었습니다!";
            sendNotificationEmail(user.getEmail(), emailSubject, post);
        }
    }
}
