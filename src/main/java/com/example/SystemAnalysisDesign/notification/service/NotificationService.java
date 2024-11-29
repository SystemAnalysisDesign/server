package com.example.SystemAnalysisDesign.notification.service;

import com.example.SystemAnalysisDesign.common.dto.reqeust.result.ListResult;
import com.example.SystemAnalysisDesign.common.service.ResponseService;
import com.example.SystemAnalysisDesign.notification.controller.dto.NotificationResponse;
import com.example.SystemAnalysisDesign.notification.domain.Notification;
import com.example.SystemAnalysisDesign.notification.respository.NotificationRepository;
import com.example.SystemAnalysisDesign.post.domain.Post;
import com.example.SystemAnalysisDesign.postKeyword.domain.PostKeyword;
import com.example.SystemAnalysisDesign.user.domain.User;
import com.example.SystemAnalysisDesign.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    @Transactional
    public ListResult<NotificationResponse> create(Post post) {

        List<PostKeyword> postKeywords = post.getPostKeywords();
        List<User> users = userRepository.findUsersByKeywords(postKeywords);

        List<NotificationResponse> notificationResponses = new ArrayList<>();

        for (User user : users) {
            Notification notification = Notification.builder()
                    .user(user)
                    .post(post)
                    .content("새로운 모집글이 등록되었습니다!")
                    .build();
            notificationRepository.save(notification);

            notificationResponses.add(NotificationResponse.from(notification));
        }

        return ResponseService.getListResult(notificationResponses);
    }
}
