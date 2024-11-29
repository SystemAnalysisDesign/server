package com.example.SystemAnalysisDesign.notification.respository;

import com.example.SystemAnalysisDesign.notification.domain.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class NotificationRepositoryImpl implements NotificationRepository {

    private final NotificationJpaRepository notificationJpaRepository;
    @Override
    public Notification save(Notification notification) {
        return notificationJpaRepository.save(notification);
    }

    @Override
    public List<Notification> findAllByUserId(Long userId) {
        return notificationJpaRepository.findAllByUser_Id(userId);
    }
}
