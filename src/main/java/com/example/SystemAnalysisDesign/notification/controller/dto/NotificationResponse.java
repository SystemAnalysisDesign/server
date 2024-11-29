package com.example.SystemAnalysisDesign.notification.controller.dto;

import com.example.SystemAnalysisDesign.notification.domain.Notification;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class NotificationResponse {

    private Long id;
    private String userEmail;
    private String postTitle;
    private String content;
    private LocalDateTime createdAt;

    public static NotificationResponse from(Notification notification) {
        return NotificationResponse.builder()
                .id(notification.getId())
                .userEmail(notification.getUser().getEmail())
                .postTitle(notification.getPost().getTitle())
                .content(notification.getContent())
                .createdAt(LocalDateTime.now())
                .build();
    }
}
