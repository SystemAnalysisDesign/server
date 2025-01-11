//package com.example.SystemAnalysisDesign.notification.service;
//
//import com.example.SystemAnalysisDesign.common.dto.reqeust.result.ListResult;
//import com.example.SystemAnalysisDesign.common.service.MailService;
//import com.example.SystemAnalysisDesign.common.service.ResponseService;
//import com.example.SystemAnalysisDesign.notification.controller.dto.NotificationResponse;
//import com.example.SystemAnalysisDesign.notification.domain.Notification;
//import com.example.SystemAnalysisDesign.notification.respository.NotificationRepository;
//import com.example.SystemAnalysisDesign.post.domain.Post;
//import com.example.SystemAnalysisDesign.postKeyword.domain.PostKeyword;
//import com.example.SystemAnalysisDesign.user.domain.User;
//import com.example.SystemAnalysisDesign.user.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//@Transactional(readOnly = true)
//public class NotificationService {
//
//    private final NotificationRepository notificationRepository;
//    private final UserRepository userRepository;
//    private final MailService mailService;
//
//    @Transactional
//    public ListResult<NotificationResponse> create(Post post) {
//
//        List<PostKeyword> postKeywords = post.getPostKeywords();
//        List<User> users = userRepository.findUsersByKeywords(postKeywords);
//
//        List<NotificationResponse> notificationResponses = new ArrayList<>();
//
//        for (User user : users) {
//            // Notification 저장
//            Notification notification = Notification.builder()
//                    .user(user)
//                    .post(post)
//                    .content("새로운 모집글이 등록되었습니다!")
//                    .build();
//            notificationRepository.save(notification);
//
//            // 이메일 전송
//            String emailSubject = "[새 글 알림] 키워드 관련 글이 등록되었습니다!";
//            String emailContent = String.format(
//                    "<h1>안녕하세요, %s님!</h1><p>새로운 글이 등록되었습니다:</p><p><b>%s</b></p><p>내용: %s</p>",
//                    user.getEmail(), post.getTitle(), post.getContent());
//            mailService.sendNotificationEmail(user.getEmail(), emailSubject, post);
//
//            notificationResponses.add(NotificationResponse.from(notification));
//        }
//
//        return ResponseService.getListResult(notificationResponses);
//    }
//}
