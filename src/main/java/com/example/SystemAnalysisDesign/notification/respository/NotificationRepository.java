package com.example.SystemAnalysisDesign.notification.respository;

import com.example.SystemAnalysisDesign.notification.domain.Notification;

import java.util.List;

public interface NotificationRepository {

    Notification save(Notification notification);

    List<Notification> findAllByUserId(Long userId);
}
